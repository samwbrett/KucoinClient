
package schemas.requests;

import java.io.Serializable;
import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class EndAt implements Serializable
{

    private final static long serialVersionUID = -356627437333009915L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EndAt.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof EndAt) == false) {
            return false;
        }
        EndAt rhs = ((EndAt) other);
        return true;
    }

}
