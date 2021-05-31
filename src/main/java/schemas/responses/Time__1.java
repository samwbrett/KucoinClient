
package schemas.responses;

import java.io.Serializable;
import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Time__1 implements Serializable
{

    private final static long serialVersionUID = -3732867389990691398L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Time__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof Time__1) == false) {
            return false;
        }
        Time__1 rhs = ((Time__1) other);
        return true;
    }

}
