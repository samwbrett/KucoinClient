
package schemas.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Data implements Serializable
{

    @SerializedName("time")
    @Expose
    private Long time;
    @SerializedName("sequence")
    @Expose
    private String sequence;
    @SerializedName("bids")
    @Expose
    private List<List<Object>> bids = new ArrayList<List<Object>>();
    @SerializedName("asks")
    @Expose
    private List<List<Object>> asks = new ArrayList<List<Object>>();
    private final static long serialVersionUID = 1572289557765916690L;

    public Long getTime() {
        return time;
    }

    public Data withTime(Long time) {
        this.time = time;
        return this;
    }

    public String getSequence() {
        return sequence;
    }

    public Data withSequence(String sequence) {
        this.sequence = sequence;
        return this;
    }

    public List<List<Object>> getBids() {
        return bids;
    }

    public Data withBids(List<List<Object>> bids) {
        this.bids = bids;
        return this;
    }

    public List<List<Object>> getAsks() {
        return asks;
    }

    public Data withAsks(List<List<Object>> asks) {
        this.asks = asks;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof Data) == false) {
            return false;
        }
        Data rhs = ((Data) other);
        return (((((this.bids == rhs.bids)||((this.bids!= null)&&this.bids.equals(rhs.bids)))&&((this.sequence == rhs.sequence)||((this.sequence!= null)&&this.sequence.equals(rhs.sequence))))&&((this.time == rhs.time)||((this.time!= null)&&this.time.equals(rhs.time))))&&((this.asks == rhs.asks)||((this.asks!= null)&&this.asks.equals(rhs.asks))));
    }

}
