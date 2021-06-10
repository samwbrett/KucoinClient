
package schemas.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class OrderBook implements Serializable
{

    @SerializedName("bids")
    @Expose
    private List<PriceSize> bids = new ArrayList<PriceSize>();
    @SerializedName("asks")
    @Expose
    private List<PriceSize> asks = new ArrayList<PriceSize>();
    @SerializedName("timestamp")
    @Expose
    private LocalDateTime timestamp;
    private final static long serialVersionUID = -593646245300492669L;

    public List<PriceSize> getBids() {
        return bids;
    }

    public OrderBook withBids(List<PriceSize> bids) {
        this.bids = bids;
        return this;
    }

    public List<PriceSize> getAsks() {
        return asks;
    }

    public OrderBook withAsks(List<PriceSize> asks) {
        this.asks = asks;
        return this;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public OrderBook withTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OrderBook.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bids");
        sb.append('=');
        sb.append(((this.bids == null)?"<null>":this.bids));
        sb.append(',');
        sb.append("asks");
        sb.append('=');
        sb.append(((this.asks == null)?"<null>":this.asks));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
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
        result = ((result* 31)+((this.asks == null)? 0 :this.asks.hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderBook) == false) {
            return false;
        }
        OrderBook rhs = ((OrderBook) other);
        return ((((this.bids == rhs.bids)||((this.bids!= null)&&this.bids.equals(rhs.bids)))&&((this.asks == rhs.asks)||((this.asks!= null)&&this.asks.equals(rhs.asks))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
    }

}
