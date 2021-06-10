
package schemas.responses;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import schemas.objects.BulkOrderList;

@Generated("jsonschema2pojo")
public class PostBulkOrdersResponse implements Serializable
{

    @SerializedName("code")
    @Expose
    private Long code;
    @SerializedName("data")
    @Expose
    private BulkOrderList data;
    private final static long serialVersionUID = 2531946565362129084L;

    public Long getCode() {
        return code;
    }

    public PostBulkOrdersResponse withCode(Long code) {
        this.code = code;
        return this;
    }

    public BulkOrderList getData() {
        return data;
    }

    public PostBulkOrdersResponse withData(BulkOrderList data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PostBulkOrdersResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof PostBulkOrdersResponse) == false) {
            return false;
        }
        PostBulkOrdersResponse rhs = ((PostBulkOrdersResponse) other);
        return (((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)))&&((this.code == rhs.code)||((this.code!= null)&&this.code.equals(rhs.code))));
    }

}
