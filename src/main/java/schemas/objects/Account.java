
package schemas.objects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Account implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("type")
    @Expose
    private Account.Type type;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("available")
    @Expose
    private Double available;
    @SerializedName("holds")
    @Expose
    private Double holds;
    private final static long serialVersionUID = -6668859080038250740L;

    public String getId() {
        return id;
    }

    public Account withId(String id) {
        this.id = id;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Account withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Account.Type getType() {
        return type;
    }

    public Account withType(Account.Type type) {
        this.type = type;
        return this;
    }

    public Double getBalance() {
        return balance;
    }

    public Account withBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public Double getAvailable() {
        return available;
    }

    public Account withAvailable(Double available) {
        this.available = available;
        return this;
    }

    public Double getHolds() {
        return holds;
    }

    public Account withHolds(Double holds) {
        this.holds = holds;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Account.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("balance");
        sb.append('=');
        sb.append(((this.balance == null)?"<null>":this.balance));
        sb.append(',');
        sb.append("available");
        sb.append('=');
        sb.append(((this.available == null)?"<null>":this.available));
        sb.append(',');
        sb.append("holds");
        sb.append('=');
        sb.append(((this.holds == null)?"<null>":this.holds));
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
        result = ((result* 31)+((this.balance == null)? 0 :this.balance.hashCode()));
        result = ((result* 31)+((this.available == null)? 0 :this.available.hashCode()));
        result = ((result* 31)+((this.holds == null)? 0 :this.holds.hashCode()));
        result = ((result* 31)+((this.currency == null)? 0 :this.currency.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Account) == false) {
            return false;
        }
        Account rhs = ((Account) other);
        return (((((((this.balance == rhs.balance)||((this.balance!= null)&&this.balance.equals(rhs.balance)))&&((this.available == rhs.available)||((this.available!= null)&&this.available.equals(rhs.available))))&&((this.holds == rhs.holds)||((this.holds!= null)&&this.holds.equals(rhs.holds))))&&((this.currency == rhs.currency)||((this.currency!= null)&&this.currency.equals(rhs.currency))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))));
    }

    @Generated("jsonschema2pojo")
    public enum Type {

        @SerializedName("main")
        MAIN("main"),
        @SerializedName("trade")
        TRADE("trade"),
        @SerializedName("margin")
        MARGIN("margin"),
        @SerializedName("pool")
        POOL("pool");
        private final String value;
        private final static Map<String, Account.Type> CONSTANTS = new HashMap<String, Account.Type>();

        static {
            for (Account.Type c: values()) {
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

        public static Account.Type fromValue(String value) {
            Account.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
