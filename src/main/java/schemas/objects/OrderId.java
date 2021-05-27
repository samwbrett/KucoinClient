
package schemas.objects;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class OrderId implements Serializable
{

    @SerializedName("orderId")
    @Expose
    private String orderId;
    private final static long serialVersionUID = -27595807721958129L;

    public String getOrderId() {
        return orderId;
    }

    public OrderId withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OrderId.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof OrderId) == false) {
            return false;
        }
        OrderId rhs = ((OrderId) other);
        return ((this.orderId == rhs.orderId)||((this.orderId!= null)&&this.orderId.equals(rhs.orderId)));
    }

}
