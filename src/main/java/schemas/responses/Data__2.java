
package schemas.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import schemas.objects.BulkOrderOrder;

@Generated("jsonschema2pojo")
public class Data__2 implements Serializable
{

    @SerializedName("data")
    @Expose
    private List<BulkOrderOrder> data = new ArrayList<BulkOrderOrder>();
    private final static long serialVersionUID = 2214335744845202884L;

    public List<BulkOrderOrder> getData() {
        return data;
    }

    public Data__2 withData(List<BulkOrderOrder> data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data__2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof Data__2) == false) {
            return false;
        }
        Data__2 rhs = ((Data__2) other);
        return ((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)));
    }

}
