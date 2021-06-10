
package schemas.responses;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import schemas.objects.OrderList;

@Generated("jsonschema2pojo")
public class ListOrdersResponse implements Serializable
{

    @SerializedName("code")
    @Expose
    private Long code;
    @SerializedName("data")
    @Expose
    private OrderList data;
    private final static long serialVersionUID = 8680859957419548940L;

    public Long getCode() {
        return code;
    }

    public ListOrdersResponse withCode(Long code) {
        this.code = code;
        return this;
    }

    public OrderList getData() {
        return data;
    }

    public ListOrdersResponse withData(OrderList data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ListOrdersResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof ListOrdersResponse) == false) {
            return false;
        }
        ListOrdersResponse rhs = ((ListOrdersResponse) other);
        return (((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)))&&((this.code == rhs.code)||((this.code!= null)&&this.code.equals(rhs.code))));
    }

}
