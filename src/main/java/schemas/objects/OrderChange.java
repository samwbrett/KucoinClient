
package schemas.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class OrderChange implements Serializable
{

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("orderType")
    @Expose
    private OrderChange.OrderType orderType;
    @SerializedName("side")
    @Expose
    private OrderChange.Side side;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("type")
    @Expose
    private OrderChange.Type type;
    @SerializedName("orderTime")
    @Expose
    private LocalDateTime orderTime;
    @SerializedName("size")
    @Expose
    private Double size;
    @SerializedName("filledSize")
    @Expose
    private Double filledSize;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("matchSize")
    @Expose
    private Double matchSize;
    @SerializedName("matchPrice")
    @Expose
    private Double matchPrice;
    @SerializedName("clientOid")
    @Expose
    private String clientOid;
    @SerializedName("remainSize")
    @Expose
    private Double remainSize;
    @SerializedName("status")
    @Expose
    private OrderChange.Status status;
    @SerializedName("ts")
    @Expose
    private LocalDateTime ts;
    private final static long serialVersionUID = -6410563474362713287L;

    public String getSymbol() {
        return symbol;
    }

    public OrderChange withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public OrderChange.OrderType getOrderType() {
        return orderType;
    }

    public OrderChange withOrderType(OrderChange.OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public OrderChange.Side getSide() {
        return side;
    }

    public OrderChange withSide(OrderChange.Side side) {
        this.side = side;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderChange withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderChange.Type getType() {
        return type;
    }

    public OrderChange withType(OrderChange.Type type) {
        this.type = type;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderChange withOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public OrderChange withSize(Double size) {
        this.size = size;
        return this;
    }

    public Double getFilledSize() {
        return filledSize;
    }

    public OrderChange withFilledSize(Double filledSize) {
        this.filledSize = filledSize;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OrderChange withPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getMatchSize() {
        return matchSize;
    }

    public OrderChange withMatchSize(Double matchSize) {
        this.matchSize = matchSize;
        return this;
    }

    public Double getMatchPrice() {
        return matchPrice;
    }

    public OrderChange withMatchPrice(Double matchPrice) {
        this.matchPrice = matchPrice;
        return this;
    }

    public String getClientOid() {
        return clientOid;
    }

    public OrderChange withClientOid(String clientOid) {
        this.clientOid = clientOid;
        return this;
    }

    public Double getRemainSize() {
        return remainSize;
    }

    public OrderChange withRemainSize(Double remainSize) {
        this.remainSize = remainSize;
        return this;
    }

    public OrderChange.Status getStatus() {
        return status;
    }

    public OrderChange withStatus(OrderChange.Status status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getTs() {
        return ts;
    }

    public OrderChange withTs(LocalDateTime ts) {
        this.ts = ts;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OrderChange.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("orderType");
        sb.append('=');
        sb.append(((this.orderType == null)?"<null>":this.orderType));
        sb.append(',');
        sb.append("side");
        sb.append('=');
        sb.append(((this.side == null)?"<null>":this.side));
        sb.append(',');
        sb.append("orderId");
        sb.append('=');
        sb.append(((this.orderId == null)?"<null>":this.orderId));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("orderTime");
        sb.append('=');
        sb.append(((this.orderTime == null)?"<null>":this.orderTime));
        sb.append(',');
        sb.append("size");
        sb.append('=');
        sb.append(((this.size == null)?"<null>":this.size));
        sb.append(',');
        sb.append("filledSize");
        sb.append('=');
        sb.append(((this.filledSize == null)?"<null>":this.filledSize));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("matchSize");
        sb.append('=');
        sb.append(((this.matchSize == null)?"<null>":this.matchSize));
        sb.append(',');
        sb.append("matchPrice");
        sb.append('=');
        sb.append(((this.matchPrice == null)?"<null>":this.matchPrice));
        sb.append(',');
        sb.append("clientOid");
        sb.append('=');
        sb.append(((this.clientOid == null)?"<null>":this.clientOid));
        sb.append(',');
        sb.append("remainSize");
        sb.append('=');
        sb.append(((this.remainSize == null)?"<null>":this.remainSize));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("ts");
        sb.append('=');
        sb.append(((this.ts == null)?"<null>":this.ts));
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
        result = ((result* 31)+((this.orderType == null)? 0 :this.orderType.hashCode()));
        result = ((result* 31)+((this.side == null)? 0 :this.side.hashCode()));
        result = ((result* 31)+((this.orderId == null)? 0 :this.orderId.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.orderTime == null)? 0 :this.orderTime.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.filledSize == null)? 0 :this.filledSize.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.matchPrice == null)? 0 :this.matchPrice.hashCode()));
        result = ((result* 31)+((this.matchSize == null)? 0 :this.matchSize.hashCode()));
        result = ((result* 31)+((this.clientOid == null)? 0 :this.clientOid.hashCode()));
        result = ((result* 31)+((this.remainSize == null)? 0 :this.remainSize.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        result = ((result* 31)+((this.ts == null)? 0 :this.ts.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderChange) == false) {
            return false;
        }
        OrderChange rhs = ((OrderChange) other);
        return ((((((((((((((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.orderType == rhs.orderType)||((this.orderType!= null)&&this.orderType.equals(rhs.orderType))))&&((this.side == rhs.side)||((this.side!= null)&&this.side.equals(rhs.side))))&&((this.orderId == rhs.orderId)||((this.orderId!= null)&&this.orderId.equals(rhs.orderId))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.orderTime == rhs.orderTime)||((this.orderTime!= null)&&this.orderTime.equals(rhs.orderTime))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.filledSize == rhs.filledSize)||((this.filledSize!= null)&&this.filledSize.equals(rhs.filledSize))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.matchPrice == rhs.matchPrice)||((this.matchPrice!= null)&&this.matchPrice.equals(rhs.matchPrice))))&&((this.matchSize == rhs.matchSize)||((this.matchSize!= null)&&this.matchSize.equals(rhs.matchSize))))&&((this.clientOid == rhs.clientOid)||((this.clientOid!= null)&&this.clientOid.equals(rhs.clientOid))))&&((this.remainSize == rhs.remainSize)||((this.remainSize!= null)&&this.remainSize.equals(rhs.remainSize))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))))&&((this.ts == rhs.ts)||((this.ts!= null)&&this.ts.equals(rhs.ts))));
    }

    @Generated("jsonschema2pojo")
    public enum OrderType {

        @SerializedName("limit")
        LIMIT("limit"),
        @SerializedName("market")
        MARKET("market"),
        @SerializedName("limit_stop")
        LIMIT_STOP("limit_stop"),
        @SerializedName("market_stop")
        MARKET_STOP("market_stop");
        private final String value;
        private final static Map<String, OrderChange.OrderType> CONSTANTS = new HashMap<String, OrderChange.OrderType>();

        static {
            for (OrderChange.OrderType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        OrderType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static OrderChange.OrderType fromValue(String value) {
            OrderChange.OrderType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum Side {

        @SerializedName("buy")
        BUY("buy"),
        @SerializedName("sell")
        SELL("sell");
        private final String value;
        private final static Map<String, OrderChange.Side> CONSTANTS = new HashMap<String, OrderChange.Side>();

        static {
            for (OrderChange.Side c: values()) {
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

        public static OrderChange.Side fromValue(String value) {
            OrderChange.Side constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum Status {

        @SerializedName("open")
        OPEN("open"),
        @SerializedName("match")
        MATCH("match"),
        @SerializedName("done")
        DONE("done");
        private final String value;
        private final static Map<String, OrderChange.Status> CONSTANTS = new HashMap<String, OrderChange.Status>();

        static {
            for (OrderChange.Status c: values()) {
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

        public static OrderChange.Status fromValue(String value) {
            OrderChange.Status constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum Type {

        @SerializedName("open")
        OPEN("open"),
        @SerializedName("match")
        MATCH("match"),
        @SerializedName("filled")
        FILLED("filled"),
        @SerializedName("canceled")
        CANCELED("canceled"),
        @SerializedName("update")
        UPDATE("update");
        private final String value;
        private final static Map<String, OrderChange.Type> CONSTANTS = new HashMap<String, OrderChange.Type>();

        static {
            for (OrderChange.Type c: values()) {
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

        public static OrderChange.Type fromValue(String value) {
            OrderChange.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
