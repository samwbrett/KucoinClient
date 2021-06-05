
package schemas.parameters;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ListOrdersParameters implements Serializable
{

    @SerializedName("status")
    @Expose
    private ListOrdersParameters.Status status;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("side")
    @Expose
    private ListOrdersParameters.Side side;
    @SerializedName("type")
    @Expose
    private ListOrdersParameters.Type type;
    @SerializedName("tradeType")
    @Expose
    private ListOrdersParameters.TradeType tradeType;
    @SerializedName("startAt")
    @Expose
    private LocalDateTime startAt;
    @SerializedName("endAt")
    @Expose
    private LocalDateTime endAt;
    private final static long serialVersionUID = 5278490396838889117L;

    public ListOrdersParameters.Status getStatus() {
        return status;
    }

    public ListOrdersParameters withStatus(ListOrdersParameters.Status status) {
        this.status = status;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public ListOrdersParameters withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public ListOrdersParameters.Side getSide() {
        return side;
    }

    public ListOrdersParameters withSide(ListOrdersParameters.Side side) {
        this.side = side;
        return this;
    }

    public ListOrdersParameters.Type getType() {
        return type;
    }

    public ListOrdersParameters withType(ListOrdersParameters.Type type) {
        this.type = type;
        return this;
    }

    public ListOrdersParameters.TradeType getTradeType() {
        return tradeType;
    }

    public ListOrdersParameters withTradeType(ListOrdersParameters.TradeType tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public ListOrdersParameters withStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
        return this;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public ListOrdersParameters withEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ListOrdersParameters.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("side");
        sb.append('=');
        sb.append(((this.side == null)?"<null>":this.side));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("tradeType");
        sb.append('=');
        sb.append(((this.tradeType == null)?"<null>":this.tradeType));
        sb.append(',');
        sb.append("startAt");
        sb.append('=');
        sb.append(((this.startAt == null)?"<null>":this.startAt));
        sb.append(',');
        sb.append("endAt");
        sb.append('=');
        sb.append(((this.endAt == null)?"<null>":this.endAt));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.side == null)? 0 :this.side.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.endAt == null)? 0 :this.endAt.hashCode()));
        result = ((result* 31)+((this.tradeType == null)? 0 :this.tradeType.hashCode()));
        result = ((result* 31)+((this.startAt == null)? 0 :this.startAt.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ListOrdersParameters) == false) {
            return false;
        }
        ListOrdersParameters rhs = ((ListOrdersParameters) other);
        return ((((((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.side == rhs.side)||((this.side!= null)&&this.side.equals(rhs.side))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.endAt == rhs.endAt)||((this.endAt!= null)&&this.endAt.equals(rhs.endAt))))&&((this.tradeType == rhs.tradeType)||((this.tradeType!= null)&&this.tradeType.equals(rhs.tradeType))))&&((this.startAt == rhs.startAt)||((this.startAt!= null)&&this.startAt.equals(rhs.startAt))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

    @Generated("jsonschema2pojo")
    public enum Side {

        @SerializedName("buy")
        BUY("buy"),
        @SerializedName("sell")
        SELL("sell");
        private final String value;
        private final static Map<String, ListOrdersParameters.Side> CONSTANTS = new HashMap<String, ListOrdersParameters.Side>();

        static {
            for (ListOrdersParameters.Side c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Side(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static ListOrdersParameters.Side fromValue(String value) {
            ListOrdersParameters.Side constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum Status {

        @SerializedName("active")
        ACTIVE("active"),
        @SerializedName("done")
        DONE("done");
        private final String value;
        private final static Map<String, ListOrdersParameters.Status> CONSTANTS = new HashMap<String, ListOrdersParameters.Status>();

        static {
            for (ListOrdersParameters.Status c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Status(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static ListOrdersParameters.Status fromValue(String value) {
            ListOrdersParameters.Status constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum TradeType {

        @SerializedName("TRADE")
        TRADE("TRADE"),
        @SerializedName("MARGIN_TRADE")
        MARGIN_TRADE("MARGIN_TRADE");
        private final String value;
        private final static Map<String, ListOrdersParameters.TradeType> CONSTANTS = new HashMap<String, ListOrdersParameters.TradeType>();

        static {
            for (ListOrdersParameters.TradeType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        TradeType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static ListOrdersParameters.TradeType fromValue(String value) {
            ListOrdersParameters.TradeType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum Type {

        @SerializedName("limit")
        LIMIT("limit"),
        @SerializedName("market")
        MARKET("market"),
        @SerializedName("limit_stop")
        LIMIT_STOP("limit_stop"),
        @SerializedName("market_stop")
        MARKET_STOP("market_stop");
        private final String value;
        private final static Map<String, ListOrdersParameters.Type> CONSTANTS = new HashMap<String, ListOrdersParameters.Type>();

        static {
            for (ListOrdersParameters.Type c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static ListOrdersParameters.Type fromValue(String value) {
            ListOrdersParameters.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
