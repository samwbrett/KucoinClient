
package schemas.responses;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class PostBulkOrdersResponse implements Serializable
{

    @SerializedName("data")
    @Expose
    private Data__2 data;
    private final static long serialVersionUID = -8687902654974430999L;

    public Data__2 getData() {
        return data;
    }

    public PostBulkOrdersResponse withData(Data__2 data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PostBulkOrdersResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PostBulkOrdersResponse) == false) {
            return false;
        }
        PostBulkOrdersResponse rhs = ((PostBulkOrdersResponse) other);
        return ((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)));
    }

}
