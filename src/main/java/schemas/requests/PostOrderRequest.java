
package schemas.requests;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class PostOrderRequest implements Serializable
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
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("stp")
    @Expose
    private String stp;
    @SerializedName("tradeType")
    @Expose
    private String tradeType;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("size")
    @Expose
    private Double size;
    @SerializedName("timeInForce")
    @Expose
    private String timeInForce;
    @SerializedName("cancelAfter")
    @Expose
    private Long cancelAfter;
    @SerializedName("postOnly")
    @Expose
    private Boolean postOnly;
    @SerializedName("hidden")
    @Expose
    private Boolean hidden;
    @SerializedName("iceberg")
    @Expose
    private Boolean iceberg;
    @SerializedName("visibleSize")
    @Expose
    private Double visibleSize;
    @SerializedName("funds")
    @Expose
    private Double funds;
    private final static long serialVersionUID = 6549506152366768906L;

    public String getClientOid() {
        return clientOid;
    }

    public PostOrderRequest withClientOid(String clientOid) {
        this.clientOid = clientOid;
        return this;
    }

    public Object getSide() {
        return side;
    }

    public PostOrderRequest withSide(Object side) {
        this.side = side;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public PostOrderRequest withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getType() {
        return type;
    }

    public PostOrderRequest withType(String type) {
        this.type = type;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public PostOrderRequest withRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getStp() {
        return stp;
    }

    public PostOrderRequest withStp(String stp) {
        this.stp = stp;
        return this;
    }

    public String getTradeType() {
        return tradeType;
    }

    public PostOrderRequest withTradeType(String tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public PostOrderRequest withPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public PostOrderRequest withSize(Double size) {
        this.size = size;
        return this;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public PostOrderRequest withTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
        return this;
    }

    public Long getCancelAfter() {
        return cancelAfter;
    }

    public PostOrderRequest withCancelAfter(Long cancelAfter) {
        this.cancelAfter = cancelAfter;
        return this;
    }

    public Boolean getPostOnly() {
        return postOnly;
    }

    public PostOrderRequest withPostOnly(Boolean postOnly) {
        this.postOnly = postOnly;
        return this;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public PostOrderRequest withHidden(Boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public Boolean getIceberg() {
        return iceberg;
    }

    public PostOrderRequest withIceberg(Boolean iceberg) {
        this.iceberg = iceberg;
        return this;
    }

    public Double getVisibleSize() {
        return visibleSize;
    }

    public PostOrderRequest withVisibleSize(Double visibleSize) {
        this.visibleSize = visibleSize;
        return this;
    }

    public Double getFunds() {
        return funds;
    }

    public PostOrderRequest withFunds(Double funds) {
        this.funds = funds;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PostOrderRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("remark");
        sb.append('=');
        sb.append(((this.remark == null)?"<null>":this.remark));
        sb.append(',');
        sb.append("stp");
        sb.append('=');
        sb.append(((this.stp == null)?"<null>":this.stp));
        sb.append(',');
        sb.append("tradeType");
        sb.append('=');
        sb.append(((this.tradeType == null)?"<null>":this.tradeType));
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
        sb.append("cancelAfter");
        sb.append('=');
        sb.append(((this.cancelAfter == null)?"<null>":this.cancelAfter));
        sb.append(',');
        sb.append("postOnly");
        sb.append('=');
        sb.append(((this.postOnly == null)?"<null>":this.postOnly));
        sb.append(',');
        sb.append("hidden");
        sb.append('=');
        sb.append(((this.hidden == null)?"<null>":this.hidden));
        sb.append(',');
        sb.append("iceberg");
        sb.append('=');
        sb.append(((this.iceberg == null)?"<null>":this.iceberg));
        sb.append(',');
        sb.append("visibleSize");
        sb.append('=');
        sb.append(((this.visibleSize == null)?"<null>":this.visibleSize));
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
        result = ((result* 31)+((this.hidden == null)? 0 :this.hidden.hashCode()));
        result = ((result* 31)+((this.cancelAfter == null)? 0 :this.cancelAfter.hashCode()));
        result = ((result* 31)+((this.remark == null)? 0 :this.remark.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.stp == null)? 0 :this.stp.hashCode()));
        result = ((result* 31)+((this.postOnly == null)? 0 :this.postOnly.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.visibleSize == null)? 0 :this.visibleSize.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.iceberg == null)? 0 :this.iceberg.hashCode()));
        result = ((result* 31)+((this.funds == null)? 0 :this.funds.hashCode()));
        result = ((result* 31)+((this.timeInForce == null)? 0 :this.timeInForce.hashCode()));
        result = ((result* 31)+((this.clientOid == null)? 0 :this.clientOid.hashCode()));
        result = ((result* 31)+((this.tradeType == null)? 0 :this.tradeType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PostOrderRequest) == false) {
            return false;
        }
        PostOrderRequest rhs = ((PostOrderRequest) other);
        return (((((((((((((((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.side == rhs.side)||((this.side!= null)&&this.side.equals(rhs.side))))&&((this.hidden == rhs.hidden)||((this.hidden!= null)&&this.hidden.equals(rhs.hidden))))&&((this.cancelAfter == rhs.cancelAfter)||((this.cancelAfter!= null)&&this.cancelAfter.equals(rhs.cancelAfter))))&&((this.remark == rhs.remark)||((this.remark!= null)&&this.remark.equals(rhs.remark))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.stp == rhs.stp)||((this.stp!= null)&&this.stp.equals(rhs.stp))))&&((this.postOnly == rhs.postOnly)||((this.postOnly!= null)&&this.postOnly.equals(rhs.postOnly))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.visibleSize == rhs.visibleSize)||((this.visibleSize!= null)&&this.visibleSize.equals(rhs.visibleSize))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.iceberg == rhs.iceberg)||((this.iceberg!= null)&&this.iceberg.equals(rhs.iceberg))))&&((this.funds == rhs.funds)||((this.funds!= null)&&this.funds.equals(rhs.funds))))&&((this.timeInForce == rhs.timeInForce)||((this.timeInForce!= null)&&this.timeInForce.equals(rhs.timeInForce))))&&((this.clientOid == rhs.clientOid)||((this.clientOid!= null)&&this.clientOid.equals(rhs.clientOid))))&&((this.tradeType == rhs.tradeType)||((this.tradeType!= null)&&this.tradeType.equals(rhs.tradeType))));
    }

}
