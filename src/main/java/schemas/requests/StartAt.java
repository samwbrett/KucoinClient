
package schemas.requests;

import java.io.Serializable;
import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class StartAt implements Serializable
{

    private final static long serialVersionUID = 3735494083121919752L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StartAt.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StartAt) == false) {
            return false;
        }
        StartAt rhs = ((StartAt) other);
        return true;
    }

}
