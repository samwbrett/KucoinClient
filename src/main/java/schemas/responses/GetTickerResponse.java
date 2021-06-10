
package schemas.responses;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import schemas.objects.SequencedSymbolTicker;

@Generated("jsonschema2pojo")
public class GetTickerResponse implements Serializable
{

    @SerializedName("code")
    @Expose
    private Long code;
    @SerializedName("data")
    @Expose
    private SequencedSymbolTicker data;
    private final static long serialVersionUID = 2053585659473988774L;

    public Long getCode() {
        return code;
    }

    public GetTickerResponse withCode(Long code) {
        this.code = code;
        return this;
    }

    public SequencedSymbolTicker getData() {
        return data;
    }

    public GetTickerResponse withData(SequencedSymbolTicker data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GetTickerResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null)?"<null>":this.code));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
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
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        result = ((result* 31)+((this.code == null)? 0 :this.code.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GetTickerResponse) == false) {
            return false;
        }
        GetTickerResponse rhs = ((GetTickerResponse) other);
        return (((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)))&&((this.code == rhs.code)||((this.code!= null)&&this.code.equals(rhs.code))));
    }

}
