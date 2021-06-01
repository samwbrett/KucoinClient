
package schemas.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class History implements Serializable
{

    @SerializedName("sequence")
    @Expose
    private Long sequence;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("size")
    @Expose
    private Double size;
    @SerializedName("side")
    @Expose
    private History.Side side;
    @SerializedName("time")
    @Expose
    private LocalDateTime time;
    private final static long serialVersionUID = -1854488396586880077L;

    public Long getSequence() {
        return sequence;
    }

    public History withSequence(Long sequence) {
        this.sequence = sequence;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public History withPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public History withSize(Double size) {
        this.size = size;
        return this;
    }

    public History.Side getSide() {
        return side;
    }

    public History withSide(History.Side side) {
        this.side = side;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public History withTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(History.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sequence");
        sb.append('=');
        sb.append(((this.sequence == null)?"<null>":this.sequence));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("size");
        sb.append('=');
        sb.append(((this.size == null)?"<null>":this.size));
        sb.append(',');
        sb.append("side");
        sb.append('=');
        sb.append(((this.side == null)?"<null>":this.side));
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
        result = ((result* 31)+((this.side == null)? 0 :this.side.hashCode()));
        result = ((result* 31)+((this.time == null)? 0 :this.time.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof History) == false) {
            return false;
        }
        History rhs = ((History) other);
        return ((((((this.sequence == rhs.sequence)||((this.sequence!= null)&&this.sequence.equals(rhs.sequence)))&&((this.side == rhs.side)||((this.side!= null)&&this.side.equals(rhs.side))))&&((this.time == rhs.time)||((this.time!= null)&&this.time.equals(rhs.time))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))));
    }

    @Generated("jsonschema2pojo")
    public enum Side {

        @SerializedName("buy")
        BUY("buy"),
        @SerializedName("sell")
        SELL("sell");
        private final String value;
        private final static Map<String, History.Side> CONSTANTS = new HashMap<String, History.Side>();

        static {
            for (History.Side c: values()) {
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

        public static History.Side fromValue(String value) {
            History.Side constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
