
package schemas;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class TradeParameters implements Serializable
{

    @SerializedName("clientOid")
    @Expose
    private String clientOid;
    @SerializedName("side")
    @Expose
    private Object side;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("maxPrice")
    @Expose
    private Double maxPrice;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("size")
    @Expose
    private Double size;
    @SerializedName("timeInForce")
    @Expose
    private String timeInForce;
    @SerializedName("cancelAfterSeconds")
    @Expose
    private Long cancelAfterSeconds;
    @SerializedName("postOnly")
    @Expose
    private Boolean postOnly;
    @SerializedName("funds")
    @Expose
    private Double funds;
    private final static long serialVersionUID = 4650993801345724405L;

    public String getClientOid() {
        return clientOid;
    }

    public TradeParameters withClientOid(String clientOid) {
        this.clientOid = clientOid;
        return this;
    }

    public Object getSide() {
        return side;
    }

    public TradeParameters withSide(Object side) {
        this.side = side;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public TradeParameters withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public TradeParameters withMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public String getType() {
        return type;
    }

    public TradeParameters withType(String type) {
        this.type = type;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public TradeParameters withRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public TradeParameters withPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public TradeParameters withSize(Double size) {
        this.size = size;
        return this;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public TradeParameters withTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
        return this;
    }

    public Long getCancelAfterSeconds() {
        return cancelAfterSeconds;
    }

    public TradeParameters withCancelAfterSeconds(Long cancelAfterSeconds) {
        this.cancelAfterSeconds = cancelAfterSeconds;
        return this;
    }

    public Boolean getPostOnly() {
        return postOnly;
    }

    public TradeParameters withPostOnly(Boolean postOnly) {
        this.postOnly = postOnly;
        return this;
    }

    public Double getFunds() {
        return funds;
    }

    public TradeParameters withFunds(Double funds) {
        this.funds = funds;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TradeParameters.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("clientOid");
        sb.append('=');
        sb.append(((this.clientOid == null)?"<null>":this.clientOid));
        sb.append(',');
        sb.append("side");
        sb.append('=');
        sb.append(((this.side == null)?"<null>":this.side));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("maxPrice");
        sb.append('=');
        sb.append(((this.maxPrice == null)?"<null>":this.maxPrice));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("remark");
        sb.append('=');
        sb.append(((this.remark == null)?"<null>":this.remark));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("size");
        sb.append('=');
        sb.append(((this.size == null)?"<null>":this.size));
        sb.append(',');
        sb.append("timeInForce");
        sb.append('=');
        sb.append(((this.timeInForce == null)?"<null>":this.timeInForce));
        sb.append(',');
        sb.append("cancelAfterSeconds");
        sb.append('=');
        sb.append(((this.cancelAfterSeconds == null)?"<null>":this.cancelAfterSeconds));
        sb.append(',');
        sb.append("postOnly");
        sb.append('=');
        sb.append(((this.postOnly == null)?"<null>":this.postOnly));
        sb.append(',');
        sb.append("funds");
        sb.append('=');
        sb.append(((this.funds == null)?"<null>":this.funds));
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
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.side == null)? 0 :this.side.hashCode()));
        result = ((result* 31)+((this.remark == null)? 0 :this.remark.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.postOnly == null)? 0 :this.postOnly.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.funds == null)? 0 :this.funds.hashCode()));
        result = ((result* 31)+((this.maxPrice == null)? 0 :this.maxPrice.hashCode()));
        result = ((result* 31)+((this.timeInForce == null)? 0 :this.timeInForce.hashCode()));
        result = ((result* 31)+((this.clientOid == null)? 0 :this.clientOid.hashCode()));
        result = ((result* 31)+((this.cancelAfterSeconds == null)? 0 :this.cancelAfterSeconds.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TradeParameters) == false) {
            return false;
        }
        TradeParameters rhs = ((TradeParameters) other);
        return (((((((((((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.side == rhs.side)||((this.side!= null)&&this.side.equals(rhs.side))))&&((this.remark == rhs.remark)||((this.remark!= null)&&this.remark.equals(rhs.remark))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.postOnly == rhs.postOnly)||((this.postOnly!= null)&&this.postOnly.equals(rhs.postOnly))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.funds == rhs.funds)||((this.funds!= null)&&this.funds.equals(rhs.funds))))&&((this.maxPrice == rhs.maxPrice)||((this.maxPrice!= null)&&this.maxPrice.equals(rhs.maxPrice))))&&((this.timeInForce == rhs.timeInForce)||((this.timeInForce!= null)&&this.timeInForce.equals(rhs.timeInForce))))&&((this.clientOid == rhs.clientOid)||((this.clientOid!= null)&&this.clientOid.equals(rhs.clientOid))))&&((this.cancelAfterSeconds == rhs.cancelAfterSeconds)||((this.cancelAfterSeconds!= null)&&this.cancelAfterSeconds.equals(rhs.cancelAfterSeconds))));
    }

}
