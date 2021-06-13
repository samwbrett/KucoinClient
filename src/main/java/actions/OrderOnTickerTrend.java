package actions;

import client.KucoinClientV2;
import client.KucoinWebsocketV2;
import exceptions.WebsocketException;
import logging.Logging;
import org.apache.commons.math3.stat.regression.RegressionResults;
import schemas.objects.MatchExecution;
import schemas.objects.Order;
import schemas.objects.SymbolInfo;
import schemas.parameters.ListOrdersParameters;
import schemas.requests.PostOrderRequest;
import schemas.websockets.MatchExecutionMessage;
import stats.RollingRegression;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderOnTickerTrend extends KucoinTradeAction<Collection<Order>> {

    private static final Logger LOGGER = Logging.handledLogger(OrderOnTickerTrend.class);

    private static final int DEFAULT_MIN_MATCHES = 5;
    private static final double DEFAULT_REQUIRED_PERCENT = 0.01;

    private final PostOrderRequest postOrder;
    private int matchCount;
    private double requiredPercent;

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

    @Override
    public Collection<Order> attempt(SymbolInfo symbolInfo) {

        KucoinClientV2 client = getClient();
        String symbol = symbolInfo.getSymbol();
        RollingRegression regression = new RollingRegression(this.matchCount);

        try {

            AtomicReference<Collection<Order>> responseReference = new AtomicReference<>(null);
            AtomicReference<KucoinWebsocketV2<MatchExecutionMessage>> historyReference = new AtomicReference<>(null);
            AtomicBoolean finished = new AtomicBoolean(false);

            KucoinWebsocketV2<MatchExecutionMessage> history = KucoinWebsocketV2.matchExecution(client, symbol, messageResponse -> {
                if (finished.get()) {
                    return;
                }

                MatchExecution match = messageResponse.getData();
                regression.addDataPoint(match.getPrice());
                if (regression.isFull()) {

                    RegressionResults results = regression.getResults();
                    double rate = results.getParameterEstimate(1) /
                            (regression.getRegression().predict(regression.getFirstX()) + regression.getRegression().predict(regression.getLastX())) * 2;
                    if (postOrder.getSide() == PostOrderRequest.Side.SELL) {
                        rate *= -1;
                    }
                    if (rate >= requiredPercent && finished.compareAndSet(false, true)) {

                        double price = regression.getRegression().predict(regression.getNextX());

                        MatchBookOrder matchBook = new MatchBookOrder(client, symbol,
                                ListOrdersParameters.Side.fromValue(postOrder.getSide().name().toLowerCase()), postOrder.getFunds() / price);

                        WithSymbolTradeAction repeating = WithSymbolTradeAction.getTimedRepeatingAction(client, 600);
                        repeating.setPreSymbolSeconds(0);
                        Collection<Order> orders = null;
                        try {
                            orders = repeating.executeAction(matchBook).get();
                        } catch (InterruptedException | ExecutionException e) {
                            LOGGER.log(Level.WARNING, e.getMessage(), e);
                        }

                        if (orders != null && !orders.isEmpty()) {
                            responseReference.set(orders);
                            historyReference.get().close();
                        } else {
                            finished.set(false);
                        }
                    }
                }
            });
            historyReference.set(history);

            while (responseReference.get() == null) {
                Thread.sleep(100);
            }

            return responseReference.get();

        } catch (WebsocketException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

}
