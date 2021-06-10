
package schemas.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class WebsocketConnection implements Serializable
{

    @SerializedName("instanceServers")
    @Expose
    private List<InstanceServer> instanceServers = new ArrayList<InstanceServer>();
    @SerializedName("token")
    @Expose
    private String token;
    private final static long serialVersionUID = -1508502637256361172L;

    public List<InstanceServer> getInstanceServers() {
        return instanceServers;
    }

    public WebsocketConnection withInstanceServers(List<InstanceServer> instanceServers) {
        this.instanceServers = instanceServers;
        return this;
    }

    public String getToken() {
        return token;
    }

    public WebsocketConnection withToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(WebsocketConnection.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("instanceServers");
        sb.append('=');
        sb.append(((this.instanceServers == null)?"<null>":this.instanceServers));
        sb.append(',');
        sb.append("token");
        sb.append('=');
        sb.append(((this.token == null)?"<null>":this.token));
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
        result = ((result* 31)+((this.instanceServers == null)? 0 :this.instanceServers.hashCode()));
        result = ((result* 31)+((this.token == null)? 0 :this.token.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WebsocketConnection) == false) {
            return false;
        }
        WebsocketConnection rhs = ((WebsocketConnection) other);
        return (((this.instanceServers == rhs.instanceServers)||((this.instanceServers!= null)&&this.instanceServers.equals(rhs.instanceServers)))&&((this.token == rhs.token)||((this.token!= null)&&this.token.equals(rhs.token))));
    }

}
