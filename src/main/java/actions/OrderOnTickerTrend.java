package actions;

import client.KucoinClientV2;
import client.KucoinWebsocketV2;
import exceptions.WebsocketException;
import logging.Logging;
import org.apache.commons.math3.stat.regression.RegressionResults;
import schemas.objects.MatchExecution;
import schemas.objects.SymbolInfo;
import schemas.parameters.ListOrdersParameters;
import schemas.requests.PostOrderRequest;
import schemas.websockets.MatchExecutionMessage;
import stats.RollingRegression;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderOnTickerTrend extends KucoinTradeAction<Double> {

    private static final Logger LOGGER = Logging.handledLogger(OrderOnTickerTrend.class);

    private static final int DEFAULT_MIN_MATCHES = 5;
    private static final double DEFAULT_REQUIRED_PERCENT = 0.01;

    private final PostOrderRequest postOrder;
    private int matchCount;
    private double requiredPercent;
    private int orderTimeoutSeconds;

    public OrderOnTickerTrend(KucoinClientV2 client, PostOrderRequest postOrder) {
        super(client, postOrder.getSymbol());
        this.postOrder = postOrder;
        this.matchCount = DEFAULT_MIN_MATCHES;
        this.requiredPercent = DEFAULT_REQUIRED_PERCENT;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    public void setRequiredPercent(double requiredPercent) {
        this.requiredPercent = requiredPercent;
    }

    public void setOrderTimeoutSeconds(int orderTimeoutSeconds) {
        this.orderTimeoutSeconds = orderTimeoutSeconds;
    }

    @Override
    public Double attempt(SymbolInfo symbolInfo) {

        KucoinClientV2 client = getClient();
        String symbol = symbolInfo.getSymbol();
        RollingRegression regression = new RollingRegression(this.matchCount);

        try {

            AtomicReference<Double> responseReference = new AtomicReference<>(postOrder.getFunds());
            AtomicReference<KucoinWebsocketV2<MatchExecutionMessage>> historyReference = new AtomicReference<>(null);
            AtomicBoolean ordering = new AtomicBoolean(false);
            CountDownLatch latch = new CountDownLatch(1);

            KucoinWebsocketV2<MatchExecutionMessage> history = KucoinWebsocketV2.matchExecution(client, symbol, messageResponse -> {

                MatchExecution match = messageResponse.getData();
                regression.addDataPoint(match.getPrice());
                if (!ordering.get() && regression.isFull()) {

                    RegressionResults results = regression.getResults();
                    double rate = results.getParameterEstimate(1) /
                            (regression.getRegression().predict(regression.getFirstX()) + regression.getRegression().predict(regression.getLastX())) * 2;
                    if (postOrder.getSide() == PostOrderRequest.Side.SELL) {
                        rate *= -1;
                    }
                    if (rate >= requiredPercent && ordering.compareAndSet(false, true)) {

                        double price = regression.getRegression().predict(regression.getNextX());
                        double size = postOrder.getSize() != null ? postOrder.getSize() : postOrder.getFunds() / price;
                        MatchBookOrder matchBook = new MatchBookOrder(client, symbol, ListOrdersParameters.Side.fromValue(postOrder.getSide().name().toLowerCase()), size);

                        WithSymbolTradeAction repeating = WithSymbolTradeAction.getTimedAction(client);
                        repeating.setPreSymbolSeconds(0);
                        double sizeLeft = 0;
                        try {
                            sizeLeft = repeating.executeAction(matchBook).get();
                        } catch (InterruptedException | ExecutionException e) {
                            LOGGER.log(Level.WARNING, e.getMessage(), e);
                        }

                        if (sizeLeft <= 0) {
                            responseReference.set(sizeLeft);
                            latch.countDown();
                            historyReference.get().close();
                        } else {
                            if (postOrder.getSize() != null) {
                                postOrder.withSize(sizeLeft);
                            } else {
                                postOrder.withFunds(sizeLeft * price);
                            }
                            ordering.set(false);
                        }
                    }
                }
            });
            historyReference.set(history);
            latch.await();
            return responseReference.get();

        } catch (WebsocketException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

}
