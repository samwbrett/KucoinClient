package responses;

import enums.OrderType;
import enums.OrdersType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OrderBookResponse extends KucoinResponse {

    public OrderBookResponse(KucoinResponse response) {
        super(response);
    }

    public List<Order> getBidOrders() {
        return getOrderList(true);
    }

    public List<Order> getAskOrders() {
        return getOrderList(false);
    }

    private List<Order> getOrderList(boolean bidOrAsk) {
        Map<String, Object> data = (Map<String, Object>)getResponseMap().get("data");
        if (data == null) {
            return Collections.emptyList();
        }

        List<List<String>> bids = (List<List<String>>) data.get(bidOrAsk ? OrdersType.bids.name() : OrdersType.asks.name());
        if (bids == null) {
            return Collections.emptyList();
        }

        List<Order> orders = new ArrayList<>(bids.size());
        for (List<String> bid : bids) {
            if (bids.size() < 2) {
                continue;
            }
            orders.add(new Order(Double.parseDouble(bid.get(0)), Double.parseDouble(bid.get(1)), bidOrAsk ? OrderType.bid.name() : OrderType.ask.name()));
        }
        return orders;
    }

    public static class Order {

        private final double price;
        private final double quantity;
        private final String type;

        private Order(double price, double quantity, String type) {
            this.price = price;
            this.quantity = quantity;
            this.type = type;
        }

        public double getPrice() {
            return price;
        }

        public double getQuantity() {
            return quantity;
        }

        public String getType() {
            return type;
        }

        public String toString() {
            return price + " " + type + " with quantity " + quantity;
        }

    }

}
