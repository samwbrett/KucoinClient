
package schemas.websockets;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class WebsocketMessage implements Serializable
{

    @SerializedName("type")
    @Expose
    private WebsocketMessage.Type type;
    private final static long serialVersionUID = -2490105479442892212L;

    public WebsocketMessage.Type getType() {
        return type;
    }

    public WebsocketMessage withType(WebsocketMessage.Type type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(WebsocketMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
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
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WebsocketMessage) == false) {
            return false;
        }
        WebsocketMessage rhs = ((WebsocketMessage) other);
        return ((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type)));
    }

    @Generated("jsonschema2pojo")
    public enum Type {

        @SerializedName("message")
        MESSAGE("message"),
        @SerializedName("ack")
        ACK("ack"),
        @SerializedName("welcome")
        WELCOME("welcome");
        private final String value;
        private final static Map<String, WebsocketMessage.Type> CONSTANTS = new HashMap<String, WebsocketMessage.Type>();

        static {
            for (WebsocketMessage.Type c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static WebsocketMessage.Type fromValue(String value) {
            WebsocketMessage.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
