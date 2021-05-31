
package schemas.objects;

import java.io.Serializable;
import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class CreatedAt implements Serializable
{

    private final static long serialVersionUID = 1110246033511544363L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CreatedAt.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof CreatedAt) == false) {
            return false;
        }
        CreatedAt rhs = ((CreatedAt) other);
        return true;
    }

}
