package params;

import coins.CoinCurrency;
import exceptions.ParameterException;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TradeParameters implements Parameters {

    public enum Side {
        buy,
        sell
    }

    public enum Type {
        limit,
        market
    }

    public enum TimeInForce {
        GTC,
        GTT,
        IOC,
        FOK
    }

    // General parameters
    private final String clientOid;
    private final Side side;
    private final CoinCurrency counterCoin;
    private final CoinCurrency baseCoin;
    private Type type;
    private String remark;

    // Limit parameters
    private Double price;
    private Double size;
    private TimeInForce timeInForce;
    private Long cancelAfterSeconds;
    private Boolean postOnly;

    // Market parameters
    // private Double size; Used in both places
    private Double funds;

    private TradeParameters(Side side, CoinCurrency baseCoin, CoinCurrency counterCoin) {
        this.clientOid = UUID.randomUUID().toString();
        this.side = side;
        this.baseCoin = baseCoin;
        this.counterCoin = counterCoin;
    }

    public Map<String, Object> asMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("clientOid", getClientOid());
        map.put("side", getSide());
        map.put("symbol", getSymbol());
        map.put("type", getType());
        if (remark != null) {
            map.put("remark", getRemark());
        }

        if (price != null) {
            map.put("price", getPrice());
        }
        if (timeInForce != null) {
            map.put("timeInForce", getTimeInForce());
        }
        if (cancelAfterSeconds != null) {
            map.put("cancelAfterSeconds", getCancelAfterSeconds());
        }
        if (postOnly != null) {
            map.put("postOnly", isPostOnly());
        }
        if (size != null) {
            map.put("size", getSize());
        }
        if (funds != null) {
            map.put("funds", getFunds());
        }

        return map;
    }

    public String toString() {
        return asMap().toString();
    }

    private String getClientOid() {
        return clientOid;
    }

    private String getSide() {
        return side.name();
    }

    private String getSymbol() {
        return baseCoin.name() + "-" + counterCoin.name();
    }

    private String getType() {
        return type.name();
    }

    private String getRemark() {
        return remark;
    }

    private static final NumberFormat NUMBER_FORMATTER = NumberFormat.getNumberInstance();
    static {
        NUMBER_FORMATTER.setMaximumFractionDigits(9);
    }
    private String getPrice() {
        return NUMBER_FORMATTER.format(price);
    }

    private String getSize() {
        return NUMBER_FORMATTER.format(size);
    }

    private String getFunds() {
        return NUMBER_FORMATTER.format(funds);
    }

    private String getTimeInForce() {
        return timeInForce.name();
    }

    private long getCancelAfterSeconds() {
        return cancelAfterSeconds;
    }

    private boolean isPostOnly() {
        return postOnly;
    }

    public static TradeParamsBuilder newBuilder(Side side, CoinCurrency baseCoin, CoinCurrency counterCoin) {
        return new TradeParamsBuilder(side, baseCoin, counterCoin);
    }

    public static class TradeParamsBuilder {

        private final TradeParameters tradeParameters;

        private TradeParamsBuilder(Side side, CoinCurrency baseCoin, CoinCurrency counterCoin) {
            this.tradeParameters = new TradeParameters(side, baseCoin, counterCoin);
        }

        public TradeParameters build() throws ParameterException {
            if (tradeParameters.type == Type.limit
                    && (tradeParameters.price == null || tradeParameters.size == null)) {

                throw new ParameterException("Limit orders require both price and size");
            } else if (tradeParameters.type == Type.market
                    && ((tradeParameters.size != null ? 1 : 0) + (tradeParameters.funds != null ? 1 : 0)) != 1) {

                throw new ParameterException("Market orders require one of either size or funds");
            }
            return tradeParameters;
        }

        public TradeParamsBuilder withType(Type type) {
            tradeParameters.type = type;
            return this;
        }

        public TradeParamsBuilder withRemark(String remark) throws ParameterException {
            if (remark.length() > 100) {
                throw new ParameterException("Remark cannot exceed 100 characters");
            }
            tradeParameters.remark = remark;
            return this;
        }

        public TradeParamsBuilder withPrice(double price) {
            /*
            TODO: Some checks using the coins
            The price must be specified in priceIncrement symbol units.
            The priceIncrement is the smallest unit of price.
            For the BTC-USDT symbol, the priceIncrement is 0.00001000.
            Prices less than 0.00001000 will not be accepted.
            The price for the placed order should be multiple numbers of priceIncrement, or the system would report an error when you place the order.
            Not required for market orders.
             */
            tradeParameters.price = price;
            return this;
        }

        public TradeParamsBuilder withSize(double size) {
            /*
            TODO: Some checks using the coins
            The size must be greater than the baseMinSize for the symbol and no larger than the baseMaxSize.
            The size must be specified in baseIncrement symbol units.
            Size indicates the amount of BTC (or base currency) to buy or sell.
             */
            tradeParameters.size = size;
            return this;
        }

        public TradeParamsBuilder withTimeInForce(TimeInForce timeInForce) {
            tradeParameters.timeInForce = timeInForce;
            return this;
        }

        public TradeParamsBuilder withCancelAfterSeconds(long seconds) {
            tradeParameters.cancelAfterSeconds = seconds;
            return this;
        }

        public TradeParamsBuilder withPostOnly(boolean isPostOnly) {
            tradeParameters.postOnly = isPostOnly;
            return this;
        }

        public TradeParamsBuilder withFunds(double funds) {
            tradeParameters.funds = funds;
            return this;
        }

    }
}
