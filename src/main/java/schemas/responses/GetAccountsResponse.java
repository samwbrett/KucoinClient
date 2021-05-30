
package schemas.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import schemas.objects.Account;

@Generated("jsonschema2pojo")
public class GetAccountsResponse implements Serializable
{

    @SerializedName("data")
    @Expose
    private List<Account> data = new ArrayList<Account>();
    private final static long serialVersionUID = -3142041519181154744L;

    public List<Account> getData() {
        return data;
    }

    public GetAccountsResponse withData(List<Account> data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GetAccountsResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof GetAccountsResponse) == false) {
            return false;
        }
        GetAccountsResponse rhs = ((GetAccountsResponse) other);
        return ((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)));
    }

}
