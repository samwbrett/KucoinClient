
package schemas.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class MatchExecution implements Serializable
{

    @SerializedName("sequence")
    @Expose
    private Long sequence;
    @SerializedName("type")
    @Expose
    private MatchExecution.Type type;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("side")
    @Expose
    private MatchExecution.Side side;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("size")
    @Expose
    private Double size;
    @SerializedName("tradeId")
    @Expose
    private String tradeId;
    @SerializedName("takerOrderId")
    @Expose
    private String takerOrderId;
    @SerializedName("makerOrderId")
    @Expose
    private String makerOrderId;
    @SerializedName("time")
    @Expose
    private LocalDateTime time;
    private final static long serialVersionUID = -491284948643086552L;

    public Long getSequence() {
        return sequence;
    }

    public MatchExecution withSequence(Long sequence) {
        this.sequence = sequence;
        return this;
    }

    public MatchExecution.Type getType() {
        return type;
    }

    public MatchExecution withType(MatchExecution.Type type) {
        this.type = type;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public MatchExecution withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public MatchExecution.Side getSide() {
        return side;
    }

    public MatchExecution withSide(MatchExecution.Side side) {
        this.side = side;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public MatchExecution withPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public MatchExecution withSize(Double size) {
        this.size = size;
        return this;
    }

    public String getTradeId() {
        return tradeId;
    }

    public MatchExecution withTradeId(String tradeId) {
        this.tradeId = tradeId;
        return this;
    }

    public String getTakerOrderId() {
        return takerOrderId;
    }

    public MatchExecution withTakerOrderId(String takerOrderId) {
        this.takerOrderId = takerOrderId;
        return this;
    }

    public String getMakerOrderId() {
        return makerOrderId;
    }

    public MatchExecution withMakerOrderId(String makerOrderId) {
        this.makerOrderId = makerOrderId;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public MatchExecution withTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MatchExecution.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sequence");
        sb.append('=');
        sb.append(((this.sequence == null)?"<null>":this.sequence));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("side");
        sb.append('=');
        sb.append(((this.side == null)?"<null>":this.side));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("size");
        sb.append('=');
        sb.append(((this.size == null)?"<null>":this.size));
        sb.append(',');
        sb.append("tradeId");
        sb.append('=');
        sb.append(((this.tradeId == null)?"<null>":this.tradeId));
        sb.append(',');
        sb.append("takerOrderId");
        sb.append('=');
        sb.append(((this.takerOrderId == null)?"<null>":this.takerOrderId));
        sb.append(',');
        sb.append("makerOrderId");
        sb.append('=');
        sb.append(((this.makerOrderId == null)?"<null>":this.makerOrderId));
        sb.append(',');
        sb.append("time");
        sb.append('=');
        sb.append(((this.time == null)?"<null>":this.time));
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
        result = ((result* 31)+((this.sequence == null)? 0 :this.sequence.hashCode()));
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.side == null)? 0 :this.side.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.takerOrderId == null)? 0 :this.takerOrderId.hashCode()));
        result = ((result* 31)+((this.time == null)? 0 :this.time.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.makerOrderId == null)? 0 :this.makerOrderId.hashCode()));
        result = ((result* 31)+((this.tradeId == null)? 0 :this.tradeId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MatchExecution) == false) {
            return false;
        }
        MatchExecution rhs = ((MatchExecution) other);
        return (((((((((((this.sequence == rhs.sequence)||((this.sequence!= null)&&this.sequence.equals(rhs.sequence)))&&((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol))))&&((this.side == rhs.side)||((this.side!= null)&&this.side.equals(rhs.side))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.takerOrderId == rhs.takerOrderId)||((this.takerOrderId!= null)&&this.takerOrderId.equals(rhs.takerOrderId))))&&((this.time == rhs.time)||((this.time!= null)&&this.time.equals(rhs.time))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.makerOrderId == rhs.makerOrderId)||((this.makerOrderId!= null)&&this.makerOrderId.equals(rhs.makerOrderId))))&&((this.tradeId == rhs.tradeId)||((this.tradeId!= null)&&this.tradeId.equals(rhs.tradeId))));
    }

    @Generated("jsonschema2pojo")
    public enum Side {

        @SerializedName("buy")
        BUY("buy"),
        @SerializedName("sell")
        SELL("sell");
        private final String value;
        private final static Map<String, MatchExecution.Side> CONSTANTS = new HashMap<String, MatchExecution.Side>();

        static {
            for (MatchExecution.Side c: values()) {
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

        public static MatchExecution.Side fromValue(String value) {
            MatchExecution.Side constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum Type {

        @SerializedName("match")
        MATCH("match");
        private final String value;
        private final static Map<String, MatchExecution.Type> CONSTANTS = new HashMap<String, MatchExecution.Type>();

        static {
            for (MatchExecution.Type c: values()) {
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

        public static MatchExecution.Type fromValue(String value) {
            MatchExecution.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
