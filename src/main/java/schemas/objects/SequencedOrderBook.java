
package schemas.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class SequencedOrderBook implements Serializable
{

    @SerializedName("time")
    @Expose
    private LocalDateTime time;
    @SerializedName("sequence")
    @Expose
    private Long sequence;
    @SerializedName("bids")
    @Expose
    private List<PriceSize> bids = new ArrayList<PriceSize>();
    @SerializedName("asks")
    @Expose
    private List<PriceSize> asks = new ArrayList<PriceSize>();
    private final static long serialVersionUID = -5565223741481187809L;

    public LocalDateTime getTime() {
        return time;
    }

    public SequencedOrderBook withTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public Long getSequence() {
        return sequence;
    }

    public SequencedOrderBook withSequence(Long sequence) {
        this.sequence = sequence;
        return this;
    }

    public List<PriceSize> getBids() {
        return bids;
    }

    public SequencedOrderBook withBids(List<PriceSize> bids) {
        this.bids = bids;
        return this;
    }

    public List<PriceSize> getAsks() {
        return asks;
    }

    public SequencedOrderBook withAsks(List<PriceSize> asks) {
        this.asks = asks;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SequencedOrderBook.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("time");
        sb.append('=');
        sb.append(((this.time == null)?"<null>":this.time));
        sb.append(',');
        sb.append("sequence");
        sb.append('=');
        sb.append(((this.sequence == null)?"<null>":this.sequence));
        sb.append(',');
        sb.append("bids");
        sb.append('=');
        sb.append(((this.bids == null)?"<null>":this.bids));
        sb.append(',');
        sb.append("asks");
        sb.append('=');
        sb.append(((this.asks == null)?"<null>":this.asks));
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
        result = ((result* 31)+((this.bids == null)? 0 :this.bids.hashCode()));
        result = ((result* 31)+((this.sequence == null)? 0 :this.sequence.hashCode()));
        result = ((result* 31)+((this.time == null)? 0 :this.time.hashCode()));
        result = ((result* 31)+((this.asks == null)? 0 :this.asks.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SequencedOrderBook) == false) {
            return false;
        }
        SequencedOrderBook rhs = ((SequencedOrderBook) other);
        return (((((this.bids == rhs.bids)||((this.bids!= null)&&this.bids.equals(rhs.bids)))&&((this.sequence == rhs.sequence)||((this.sequence!= null)&&this.sequence.equals(rhs.sequence))))&&((this.time == rhs.time)||((this.time!= null)&&this.time.equals(rhs.time))))&&((this.asks == rhs.asks)||((this.asks!= null)&&this.asks.equals(rhs.asks))));
    }

}
