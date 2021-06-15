package actions;

import client.KucoinClientV2;
import client.KucoinClientV2Response;
import client.KucoinWebsocketV2;
import exceptions.RequestException;
import exceptions.WebsocketException;
import schemas.objects.BulkOrder;
import schemas.objects.OrderChange;
import schemas.objects.PriceSize;
import schemas.objects.SymbolInfo;
import schemas.parameters.ListOrdersParameters;
import schemas.requests.PostBulkOrdersRequest;
import schemas.responses.PostBulkOrdersResponse;
import schemas.websockets.BestOrdersMessage;
import schemas.websockets.OrderChangeMessage;
import utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Attempts to buy or sell at the best orders on the book up to a threshold of historical and listed prices.
 * May place multiple orders if the size of a given order on the book is too small.
 * Intended behavior is to return null on an unsuccessful attempt
 */
public class MatchBookOrder extends KucoinTradeAction<Double> {

    private final ListOrdersParameters.Side tradeSide;

    private double sizeLeft;
    private boolean postOnly;

    public MatchBookOrder(KucoinClientV2 client, String symbol, ListOrdersParameters.Side tradeSide, double size) {
        super(client, symbol);
        this.tradeSide = tradeSide;
        this.sizeLeft = size;
        this.postOnly = false;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    @Override
    public Double attempt(SymbolInfo symbolInfo) {

        try {
            String symbol = symbolInfo.getSymbol();
            addLiveInfo(symbol);
            double baseIncrement = symbolInfo.getBaseIncrement();
            int baseIncrementPlaces = NumberUtils.getPrecision(baseIncrement);

            // Read the order book
            CountDownLatch orderLatch = new CountDownLatch(1);
            Object orderLock = new Object();
            List<PriceSize> priceSizes = new ArrayList<>();
            AtomicLong orderSizeAvailableIncrement = new AtomicLong((long)(sizeLeft / baseIncrement));
            AtomicReference<KucoinWebsocketV2<BestOrdersMessage>> orderSocket = new AtomicReference<>(null);
            KucoinWebsocketV2<BestOrdersMessage> bestFive = KucoinWebsocketV2.bestFiveOrders(getClient(), symbol, messageResponse -> {
                List<PriceSize> reverseOrders = tradeSide == ListOrdersParameters.Side.BUY ? messageResponse.getData().getAsks() : messageResponse.getData().getBids();
                boolean finished = false;
                for (PriceSize order : reverseOrders) {
                    double orderPrice = order.getPrice();
                    synchronized (orderLock) {
                        if (orderSizeAvailableIncrement.get() <= 0) {
                            finished = true;
                            break;
                        }

                        double orderSizeIncrement = Math.min(orderSizeAvailableIncrement.get(), order.getSize() / baseIncrement);
                        double orderSize = orderSizeIncrement * baseIncrement;

                        orderSizeAvailableIncrement.set((long) (orderSizeAvailableIncrement.get() - orderSizeIncrement));
                        priceSizes.add(new PriceSize().withPrice(orderPrice).withSize(orderSize));
                    }
                }
                if (finished) {
                    orderLatch.countDown();
                    orderSocket.get().close();
                }
            });
            orderSocket.set(bestFive);

            // Order ids for later
            Set<String> clientOids = ConcurrentHashMap.newKeySet();

            // Prepare for the finished orders
            AtomicBoolean ordersSetup = new AtomicBoolean(false);
            CountDownLatch finishedOrderLatch = new CountDownLatch(1);
            Object finishedOrderLock = new Object();
            AtomicReference<KucoinWebsocketV2<OrderChangeMessage>> finishedOrderSocket = new AtomicReference<>(null);
            KucoinWebsocketV2<OrderChangeMessage> symbolChanges = KucoinWebsocketV2.orderChange(getClient(), messageResponse -> {

                if (!ordersSetup.get()) {
                    return;
                }

                OrderChange change = messageResponse.getData();
                if (change.getSymbol().equals(symbol)) {

                    String clientOid = change.getClientOid();
                    if (clientOids.contains(clientOid) && change.getStatus() == OrderChange.Status.DONE) {
                        synchronized (finishedOrderLock) {
                            sizeLeft = NumberUtils.toPrecision(sizeLeft - change.getFilledSize(), baseIncrementPlaces);
                        }
                        clientOids.remove(clientOid);
                        if (clientOids.isEmpty() && ordersSetup.compareAndSet(true, false)) {
                            finishedOrderLatch.countDown();
                            finishedOrderSocket.get().close();
                        }
                    }
                }
            });
            finishedOrderSocket.set(symbolChanges);

            // Setup what the orders should be
            orderLatch.await();
            List<BulkOrder> toTradeBulkOrders = new ArrayList<>(priceSizes.size());
            for (int i = 0; i != priceSizes.size(); i++) {

                PriceSize priceSize = priceSizes.get(i);
                double price = priceSize.getPrice();
                double size = priceSize.getSize();
                String clientOid = UUID.randomUUID().toString();
                clientOids.add(clientOid);

                toTradeBulkOrders.add(
                        new BulkOrder()
                                .withClientOid(clientOid)
                                .withSide(BulkOrder.Side.fromValue(tradeSide.value()))
                                .withSymbol(symbol)
                                .withType(BulkOrder.Type.LIMIT)
                                .withTimeInForce(BulkOrder.TimeInForce.GTT)
                                .withCancelAfter(1L)
                                .withPrice(price)
                                .withSize(size)
                                .withPostOnly(postOnly));
            }

            // Attempt to place the orders
            ordersSetup.set(true);
            List<KucoinClientV2Response<PostBulkOrdersResponse>> bulkOrdersResponses = new ArrayList<>(toTradeBulkOrders.size());
            for (int i = 0; i < toTradeBulkOrders.size(); i += 5) {
                bulkOrdersResponses.add(getClient().postBulkOrders(new PostBulkOrdersRequest()
                        .withSymbol(symbol)
                        .withOrderList(toTradeBulkOrders.subList(i, Math.min(i + 5, toTradeBulkOrders.size())))));
            }
            addLiveInfo(toTradeBulkOrders.toString());
            addLiveInfo(bulkOrdersResponses.toString());

            finishedOrderLatch.await();
            return sizeLeft;
        } catch (RequestException | WebsocketException | InterruptedException e) {
            addLiveInfo(e.toString());
            return null;
        }
    }

}
