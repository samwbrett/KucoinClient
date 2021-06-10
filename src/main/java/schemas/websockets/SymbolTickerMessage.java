
package schemas.websockets;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import schemas.objects.SymbolTicker;

@Generated("jsonschema2pojo")
public class SymbolTickerMessage implements Serializable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("data")
    @Expose
    private SymbolTicker data;
    private final static long serialVersionUID = -6173842539700450392L;

    public String getType() {
        return type;
    }

    public SymbolTickerMessage withType(String type) {
        this.type = type;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public SymbolTickerMessage withTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public SymbolTickerMessage withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public SymbolTicker getData() {
        return data;
    }

    public SymbolTickerMessage withData(SymbolTicker data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SymbolTickerMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("topic");
        sb.append('=');
        sb.append(((this.topic == null)?"<null>":this.topic));
        sb.append(',');
        sb.append("subject");
        sb.append('=');
        sb.append(((this.subject == null)?"<null>":this.subject));
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
        result = ((result* 31)+((this.topic == null)? 0 :this.topic.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        result = ((result* 31)+((this.subject == null)? 0 :this.subject.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SymbolTickerMessage) == false) {
            return false;
        }
        SymbolTickerMessage rhs = ((SymbolTickerMessage) other);
        return (((((this.topic == rhs.topic)||((this.topic!= null)&&this.topic.equals(rhs.topic)))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))))&&((this.subject == rhs.subject)||((this.subject!= null)&&this.subject.equals(rhs.subject))));
    }

}
