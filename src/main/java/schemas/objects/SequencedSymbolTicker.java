
package schemas.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class SequencedSymbolTicker implements Serializable
{

    @SerializedName("time")
    @Expose
    private LocalDateTime time;
    @SerializedName("sequence")
    @Expose
    private Long sequence;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("size")
    @Expose
    private Double size;
    @SerializedName("bestBid")
    @Expose
    private Double bestBid;
    @SerializedName("bestBidSize")
    @Expose
    private Double bestBidSize;
    @SerializedName("bestAsk")
    @Expose
    private Double bestAsk;
    @SerializedName("bestAskSize")
    @Expose
    private Double bestAskSize;
    private final static long serialVersionUID = 3039696180613745719L;

    public LocalDateTime getTime() {
        return time;
    }

    public SequencedSymbolTicker withTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public Long getSequence() {
        return sequence;
    }

    public SequencedSymbolTicker withSequence(Long sequence) {
        this.sequence = sequence;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public SequencedSymbolTicker withPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public SequencedSymbolTicker withSize(Double size) {
        this.size = size;
        return this;
    }

    public Double getBestBid() {
        return bestBid;
    }

    public SequencedSymbolTicker withBestBid(Double bestBid) {
        this.bestBid = bestBid;
        return this;
    }

    public Double getBestBidSize() {
        return bestBidSize;
    }

    public SequencedSymbolTicker withBestBidSize(Double bestBidSize) {
        this.bestBidSize = bestBidSize;
        return this;
    }

    public Double getBestAsk() {
        return bestAsk;
    }

    public SequencedSymbolTicker withBestAsk(Double bestAsk) {
        this.bestAsk = bestAsk;
        return this;
    }

    public Double getBestAskSize() {
        return bestAskSize;
    }

    public SequencedSymbolTicker withBestAskSize(Double bestAskSize) {
        this.bestAskSize = bestAskSize;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SequencedSymbolTicker.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("time");
        sb.append('=');
        sb.append(((this.time == null)?"<null>":this.time));
        sb.append(',');
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
        sb.append("bestBid");
        sb.append('=');
        sb.append(((this.bestBid == null)?"<null>":this.bestBid));
        sb.append(',');
        sb.append("bestBidSize");
        sb.append('=');
        sb.append(((this.bestBidSize == null)?"<null>":this.bestBidSize));
        sb.append(',');
        sb.append("bestAsk");
        sb.append('=');
        sb.append(((this.bestAsk == null)?"<null>":this.bestAsk));
        sb.append(',');
        sb.append("bestAskSize");
        sb.append('=');
        sb.append(((this.bestAskSize == null)?"<null>":this.bestAskSize));
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
        result = ((result* 31)+((this.bestAsk == null)? 0 :this.bestAsk.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.bestBidSize == null)? 0 :this.bestBidSize.hashCode()));
        result = ((result* 31)+((this.time == null)? 0 :this.time.hashCode()));
        result = ((result* 31)+((this.bestBid == null)? 0 :this.bestBid.hashCode()));
        result = ((result* 31)+((this.bestAskSize == null)? 0 :this.bestAskSize.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SequencedSymbolTicker) == false) {
            return false;
        }
        SequencedSymbolTicker rhs = ((SequencedSymbolTicker) other);
        return (((((((((this.sequence == rhs.sequence)||((this.sequence!= null)&&this.sequence.equals(rhs.sequence)))&&((this.bestAsk == rhs.bestAsk)||((this.bestAsk!= null)&&this.bestAsk.equals(rhs.bestAsk))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.bestBidSize == rhs.bestBidSize)||((this.bestBidSize!= null)&&this.bestBidSize.equals(rhs.bestBidSize))))&&((this.time == rhs.time)||((this.time!= null)&&this.time.equals(rhs.time))))&&((this.bestBid == rhs.bestBid)||((this.bestBid!= null)&&this.bestBid.equals(rhs.bestBid))))&&((this.bestAskSize == rhs.bestAskSize)||((this.bestAskSize!= null)&&this.bestAskSize.equals(rhs.bestAskSize))));
    }

}
