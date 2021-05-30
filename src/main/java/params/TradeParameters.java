package params;

import enums.CoinCurrency;
import enums.Side;
import enums.TimeInForce;
import enums.Type;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TradeParameters implements Parameters {

    // General parameters
    private final String clientOid;
    private final Side side;
    private final CoinCurrency counterCoin;
    private final CoinCurrency baseCoin;
    private Double maxPrice;
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

    private TradeParameters(TradeParameters params) {
        this.clientOid = params.clientOid;
        this.side = params.side;
        this.counterCoin = params.counterCoin;
        this.baseCoin = params.baseCoin;
        this.maxPrice = params.maxPrice;
        this.type = params.type;
        this.remark = params.remark;

        this.price = params.price;
        this.size = params.size;
        this.timeInForce = params.timeInForce;
        this.cancelAfterSeconds = params.cancelAfterSeconds;
        this.postOnly = params.postOnly;
        this.funds = params.funds;
    }

    public Map<String, Object> asMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("clientOid", getClientOid());
        map.put("side", getSideString());
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
            map.put("funds", getFundsString());
        }

        return map;
    }

    public String toString() {
        return asMap().toString();
    }

    public CoinCurrency getBaseCoin() {
        return baseCoin;
    }

    public CoinCurrency getCounterCoin() {
        return counterCoin;
    }

    public Side getSide() {
        return side;
    }

    public Double getFunds() {
        return funds;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public String getClientOid() {
        return clientOid;
    }

    private String getSideString() {
        return side.name();
    }

    private String getSymbol() {
        return CoinCurrency.getSymbol(baseCoin, counterCoin);
    }

    private String getType() {
        return type == null ? null : type.name();
    }

    private String getRemark() {
        return remark;
    }

    private static final NumberFormat NUMBER_FORMATTER = NumberFormat.getNumberInstance();
    static {
        NUMBER_FORMATTER.setMaximumFractionDigits(9);
        NUMBER_FORMATTER.setGroupingUsed(false);
    }
    private String getPrice() {
        return NUMBER_FORMATTER.format(price);
    }

    private String getSize() {
        return NUMBER_FORMATTER.format(size);
    }

    private String getFundsString() {
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

    public static TradeParamsBuilder newBuilder(TradeParameters startFrom) {
        return new TradeParamsBuilder(startFrom);
    }

    public static class TradeParamsBuilder {

        private final TradeParameters tradeParameters;

        private TradeParamsBuilder(Side side, CoinCurrency baseCoin, CoinCurrency counterCoin) {
            this.tradeParameters = new TradeParameters(side, baseCoin, counterCoin);
        }

        private TradeParamsBuilder(TradeParameters params) {
            this.tradeParameters = new TradeParameters(params);
        }

        public TradeParameters build() {
            return tradeParameters;
        }

        public TradeParamsBuilder withType(Type type) {
            tradeParameters.type = type;
            return this;
        }

        public TradeParamsBuilder withRemark(String remark) {
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

        public TradeParamsBuilder withMaxPrice(double maxPrice) {
            tradeParameters.maxPrice = maxPrice;
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
