package actions;

import client.KucoinClientV2;
import client.KucoinClientV2Response;
import exceptions.RequestException;
import schemas.objects.*;
import schemas.parameters.ListOrdersParameters;
import schemas.requests.PostBulkOrdersRequest;
import schemas.responses.*;
import utils.KucoinUtils;
import utils.NumberUtils;

import java.util.*;

/**
 * Attempts to buy or sell at the best orders on the book up to a threshold of historical and listed prices.
 * May place multiple orders if the size of a given order on the book is too small.
 * Intended behavior is to return null on an unsuccessful attempt
 */
public class MatchBookOrder extends KucoinTradeAction<Collection<RecentOrderOrder>> {

    private static final double DEFAULT_MAX_PERCENT_DIFF_MAX = 100;
    private static final int DEFAULT_THRESHOLD_HISTORIES = 0;

    private final ListOrdersParameters.Side tradeSide;
    private double sizeLeft;
    private double maxPercentDiffMax; // 1 is 100% percent
    private int thresholdHistories;
    private boolean postOnly;

    private final Map<String, RecentOrderOrder> executedOrders = new HashMap<>(); // Order id to order response

    public MatchBookOrder(KucoinClientV2 client, String symbol, ListOrdersParameters.Side tradeSide, double size) {
        super(client, symbol);
        this.tradeSide = tradeSide;
        this.sizeLeft = size;
        this.maxPercentDiffMax = DEFAULT_MAX_PERCENT_DIFF_MAX;
        this.thresholdHistories = DEFAULT_THRESHOLD_HISTORIES;
        this.postOnly = false;
    }

    public void setMaxPercentDiffMax(double maxPercentDiffMax) {
        this.maxPercentDiffMax = maxPercentDiffMax;
    }

    public void setThresholdHistories(int thresholdHistories) {
        this.thresholdHistories = thresholdHistories;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    @Override
    public Collection<RecentOrderOrder> attempt(SymbolInfo symbolInfo) {

        try {
            addLiveInfo(getSymbol());
            int baseIncrementPlaces = NumberUtils.getPrecision(symbolInfo.getBaseIncrement());

            // Get history and get highest non-outlier price
            KucoinClientV2Response<GetHistoryResponse> historyResponse = getClient().getHistoryResponse(getSymbol());
            addLiveInfo(historyResponse.toString());
            if (!historyResponse.isSuccess()) {
                return null;
            }

            List<History> histories = historyResponse.getResponseBody().getData();
            if (histories.size() < thresholdHistories) {
                addLiveInfo("Fewer than " + thresholdHistories + " historical orders");
                return null;
            }
            double avgHistoryPrice = KucoinUtils.getHistoryAverage(histories);

            // Read the order book
            KucoinClientV2Response<GetOrderBookResponse> orderBookResponse = getClient().getOrderBook(getSymbol());
            addLiveInfo(orderBookResponse.toString());
            if (!orderBookResponse.isSuccess()) {
                return null;
            }

            // Test the prices on the reverse side
            Data orderData = orderBookResponse.getResponseBody().getData();
            List<PriceSize> reverseOrders = tradeSide == ListOrdersParameters.Side.BUY ? orderData.getAsks() : orderData.getBids();
            if (reverseOrders.isEmpty()) {
                addLiveInfo("No orders on the reverse side");
                return null;
            }

            Double bestReverseSidePrice = reverseOrders.get(0).getPrice();
            double limitReverseSidePrice = tradeSide == ListOrdersParameters.Side.BUY ?
                    (1 + maxPercentDiffMax) * avgHistoryPrice :
                    (1 - maxPercentDiffMax) * avgHistoryPrice;
            if ((tradeSide == ListOrdersParameters.Side.BUY && bestReverseSidePrice > limitReverseSidePrice)
                    || (tradeSide == ListOrdersParameters.Side.SELL && bestReverseSidePrice < limitReverseSidePrice)) {

                addLiveInfo("Best reverse side price beyond " + (maxPercentDiffMax * 100) + "% of average historical");
                return null;
            }

            // Test the prices on the same side
            List<PriceSize> orders = tradeSide == ListOrdersParameters.Side.BUY ? orderData.getBids() : orderData.getAsks();
            if (orders.isEmpty() && maxPercentDiffMax != DEFAULT_MAX_PERCENT_DIFF_MAX) {
                addLiveInfo("No orders on the same side");
                return null;
            }
            double averageSidePrice = KucoinUtils.getPriceSizeAverage(orders);

            double limitSidePrice = tradeSide == ListOrdersParameters.Side.BUY ?
                    (1 + maxPercentDiffMax) * averageSidePrice :
                    (1 - maxPercentDiffMax) * averageSidePrice;
            if ((tradeSide == ListOrdersParameters.Side.BUY && bestReverseSidePrice > limitSidePrice)
                    || (tradeSide == ListOrdersParameters.Side.SELL && bestReverseSidePrice < limitSidePrice)) {

                addLiveInfo("Best same side price beyond " + (maxPercentDiffMax * 100) + "% of average same side orders");
                return null;
            }

            // Setup what the orders should be
            List<Double> prices = new ArrayList<>();
            List<Double> sizes = new ArrayList<>();
            double orderSizeAvailable = sizeLeft;
            for (PriceSize order : reverseOrders) {
                double orderPrice = order.getPrice();
                if (orderSizeAvailable <= 0
                        || (tradeSide == ListOrdersParameters.Side.BUY && orderPrice > limitReverseSidePrice && orderPrice > limitSidePrice)
                        || (tradeSide == ListOrdersParameters.Side.SELL && orderPrice < limitReverseSidePrice && orderPrice < limitSidePrice)) {
                    break;
                }

                prices.add(orderPrice);

                double orderSize = Math.min(orderSizeAvailable, order.getSize());
                orderSize = NumberUtils.toPrecision((int) (orderSize / symbolInfo.getBaseIncrement()) * symbolInfo.getBaseIncrement(), baseIncrementPlaces);

                sizes.add(orderSize);
                orderSizeAvailable = NumberUtils.toPrecision((int) ((orderSizeAvailable - orderSize) / symbolInfo.getBaseIncrement()) * symbolInfo.getBaseIncrement(), baseIncrementPlaces);
            }

            // Attempt to place the orders
            List<BulkOrderOrder> toTradeBulkOrders = new ArrayList<>(prices.size());
            for (int i = 0; i != prices.size(); i++) {

                double price = prices.get(i);
                double size = sizes.get(i);

                toTradeBulkOrders.add(
                        new BulkOrderOrder()
                                .withClientOid(UUID.randomUUID().toString())
                                .withSide(BulkOrderOrder.Side.fromValue(tradeSide.value()))
                                .withSymbol(getSymbol())
                                .withType(BulkOrderOrder.Type.LIMIT)
                                .withTimeInForce(BulkOrderOrder.TimeInForce.GTT)
                                .withCancelAfter(1L)
                                .withPrice(price)
                                .withSize(size)
                                .withPostOnly(postOnly));
            }
            addLiveInfo(toTradeBulkOrders.toString());

            List<KucoinClientV2Response<PostBulkOrdersResponse>> bulkOrdersResponses = new ArrayList<>(toTradeBulkOrders.size());
            for (int i = 0; i < toTradeBulkOrders.size(); i += 5) {
                bulkOrdersResponses.add(getClient().postBulkOrders(new PostBulkOrdersRequest()
                        .withSymbol(getSymbol())
                        .withOrderList(toTradeBulkOrders.subList(i, Math.min(i + 5, toTradeBulkOrders.size())))));
            }
            addLiveInfo(bulkOrdersResponses.toString());

            // Get the order information from finished orders
            boolean allFinished = false;
            while (!allFinished) {
                allFinished = true;
                for (KucoinClientV2Response<PostBulkOrdersResponse> bulkOrdersResponse : bulkOrdersResponses) {
                    if (bulkOrdersResponse.isSuccess()) {

                        for (BulkOrderOrder recentOrder : bulkOrdersResponse.getResponseBody().getData().getData()) {
                            if (recentOrder.getFailMsg() != null && !recentOrder.getFailMsg().isEmpty()) {
                                addLiveInfo(recentOrder.toString());
                                continue;
                            }

                            if (!executedOrders.containsKey(recentOrder.getId())) {

                                KucoinClientV2Response<GetOrderResponse> orderResponse = getClient().getOrder(recentOrder.getId());
                                RecentOrderOrder order = orderResponse.getResponseBody().getData();
                                if (order.getIsActive()) {
                                    allFinished = false;
                                } else {
                                    executedOrders.put(order.getId(), order);
                                    sizeLeft = NumberUtils.toPrecision((int) ((sizeLeft - order.getDealSize()) / symbolInfo.getBaseIncrement()) * symbolInfo.getBaseIncrement(), baseIncrementPlaces);
                                }
                            }
                        }
                    }
                }
            }

            return sizeLeft <= 0 ? executedOrders.values() : null;
        } catch (RequestException e) {
            addLiveInfo(e.getMessage());
            return null;
        }
    }

}
