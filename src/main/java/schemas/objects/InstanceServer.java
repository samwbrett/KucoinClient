
package schemas.objects;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class InstanceServer implements Serializable
{

    @SerializedName("endpoint")
    @Expose
    private String endpoint;
    @SerializedName("protocol")
    @Expose
    private String protocol;
    @SerializedName("encrypt")
    @Expose
    private Boolean encrypt;
    @SerializedName("pingInterval")
    @Expose
    private Long pingInterval;
    @SerializedName("pingTimeout")
    @Expose
    private Long pingTimeout;
    private final static long serialVersionUID = -574992866887612777L;

    public String getEndpoint() {
        return endpoint;
    }

    public InstanceServer withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public String getProtocol() {
        return protocol;
    }

    public InstanceServer withProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public Boolean getEncrypt() {
        return encrypt;
    }

    public InstanceServer withEncrypt(Boolean encrypt) {
        this.encrypt = encrypt;
        return this;
    }

    public Long getPingInterval() {
        return pingInterval;
    }

    public InstanceServer withPingInterval(Long pingInterval) {
        this.pingInterval = pingInterval;
        return this;
    }

    public Long getPingTimeout() {
        return pingTimeout;
    }

    public InstanceServer withPingTimeout(Long pingTimeout) {
        this.pingTimeout = pingTimeout;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(InstanceServer.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("endpoint");
        sb.append('=');
        sb.append(((this.endpoint == null)?"<null>":this.endpoint));
        sb.append(',');
        sb.append("protocol");
        sb.append('=');
        sb.append(((this.protocol == null)?"<null>":this.protocol));
        sb.append(',');
        sb.append("encrypt");
        sb.append('=');
        sb.append(((this.encrypt == null)?"<null>":this.encrypt));
        sb.append(',');
        sb.append("pingInterval");
        sb.append('=');
        sb.append(((this.pingInterval == null)?"<null>":this.pingInterval));
        sb.append(',');
        sb.append("pingTimeout");
        sb.append('=');
        sb.append(((this.pingTimeout == null)?"<null>":this.pingTimeout));
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
        result = ((result* 31)+((this.pingInterval == null)? 0 :this.pingInterval.hashCode()));
        result = ((result* 31)+((this.pingTimeout == null)? 0 :this.pingTimeout.hashCode()));
        result = ((result* 31)+((this.endpoint == null)? 0 :this.endpoint.hashCode()));
        result = ((result* 31)+((this.protocol == null)? 0 :this.protocol.hashCode()));
        result = ((result* 31)+((this.encrypt == null)? 0 :this.encrypt.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InstanceServer) == false) {
            return false;
        }
        InstanceServer rhs = ((InstanceServer) other);
        return ((((((this.pingInterval == rhs.pingInterval)||((this.pingInterval!= null)&&this.pingInterval.equals(rhs.pingInterval)))&&((this.pingTimeout == rhs.pingTimeout)||((this.pingTimeout!= null)&&this.pingTimeout.equals(rhs.pingTimeout))))&&((this.endpoint == rhs.endpoint)||((this.endpoint!= null)&&this.endpoint.equals(rhs.endpoint))))&&((this.protocol == rhs.protocol)||((this.protocol!= null)&&this.protocol.equals(rhs.protocol))))&&((this.encrypt == rhs.encrypt)||((this.encrypt!= null)&&this.encrypt.equals(rhs.encrypt))));
    }

}
