package responses;

import enums.Side;

import java.time.Instant;
import java.util.*;

public class HistoryResponse extends KucoinResponse {

    public HistoryResponse(KucoinResponse response) {
        super(response);
    }

    public List<OrderHistory> getOrderHistories() {
        List<Map<String, Object>> data = (List<Map<String, Object>>) getResponseMap().get("data");
        if (data == null) {
            return Collections.emptyList();
        }

        List<OrderHistory> orderHistories = new ArrayList<>(data.size());
        for (Map<String, Object> datum : data) {

            String sequenceString = (String) datum.get("sequence");
            String priceString = (String) datum.get("price");
            String sizeString = (String) datum.get("size");
            String sideString = (String) datum.get("side");
            Double time = (Double) datum.get("time");

            if (sequenceString == null || priceString == null || sizeString == null || sideString == null || time == null) {
                continue;
            }

            long sequence = Long.parseLong(sequenceString);
            double price = Double.parseDouble(priceString);
            double size = Double.parseDouble(sizeString);
            Side side = Side.getSide(sideString);

            orderHistories.add(new OrderHistory(
                    sequence,
                    price,
                    size,
                    side,
                    time
            ));
        }
        return orderHistories;
    }

    public class OrderHistory {

        private final long sequence;
        private final double price;
        private final double size;
        private final Side side;
        private final Instant time;

        private OrderHistory(long sequence, double price, double size, Side side, double time) {
            this.sequence = sequence;
            this.price = price;
            this.size = size;
            this.side = side;
            this.time = Instant.ofEpochMilli(((Double)time).longValue());
        }

        public long getSequence() {
            return sequence;
        }

        public double getPrice() {
            return price;
        }

        public double getSize() {
            return size;
        }

        public Side getSide() {
            return side;
        }

        public Instant getTime() {
            return time;
        }

        public String toString() {
            return price + " " + side + " with quantity " + size + " at " + time + " (" + sequence + ")";
        }
    }

}
