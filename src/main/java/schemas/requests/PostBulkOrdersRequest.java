
package schemas.requests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import schemas.objects.BulkOrder;

@Generated("jsonschema2pojo")
public class PostBulkOrdersRequest implements Serializable
{

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("orderList")
    @Expose
    private List<BulkOrder> orderList = new ArrayList<BulkOrder>();
    private final static long serialVersionUID = -7506267021880873756L;

    public String getSymbol() {
        return symbol;
    }

    public PostBulkOrdersRequest withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public List<BulkOrder> getOrderList() {
        return orderList;
    }

    public PostBulkOrdersRequest withOrderList(List<BulkOrder> orderList) {
        this.orderList = orderList;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PostBulkOrdersRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("orderList");
        sb.append('=');
        sb.append(((this.orderList == null)?"<null>":this.orderList));
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
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.orderList == null)? 0 :this.orderList.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PostBulkOrdersRequest) == false) {
            return false;
        }
        PostBulkOrdersRequest rhs = ((PostBulkOrdersRequest) other);
        return (((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.orderList == rhs.orderList)||((this.orderList!= null)&&this.orderList.equals(rhs.orderList))));
    }

}
