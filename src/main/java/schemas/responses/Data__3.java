
package schemas.responses;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Data__3 implements Serializable
{

    @SerializedName("orderId")
    @Expose
    private String orderId;
    private final static long serialVersionUID = 8674162208226418592L;

    public String getOrderId() {
        return orderId;
    }

    public Data__3 withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data__3 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderId");
        sb.append('=');
        sb.append(((this.orderId == null)?"<null>":this.orderId));
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
        result = ((result* 31)+((this.orderId == null)? 0 :this.orderId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data__3) == false) {
            return false;
        }
        Data__3 rhs = ((Data__3) other);
        return ((this.orderId == rhs.orderId)||((this.orderId!= null)&&this.orderId.equals(rhs.orderId)));
    }

}
