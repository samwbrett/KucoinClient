
package schemas.objects;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class BulkOrderOrder implements Serializable
{

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("side")
    @Expose
    private String side;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("funds")
    @Expose
    private Object funds;
    @SerializedName("stp")
    @Expose
    private String stp;
    @SerializedName("stop")
    @Expose
    private String stop;
    @SerializedName("stopPrice")
    @Expose
    private String stopPrice;
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
    private String visibleSize;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("failMsg")
    @Expose
    private String failMsg;
    @SerializedName("clientOid")
    @Expose
    private String clientOid;
    private final static long serialVersionUID = -4268415127792627009L;

    public String getSymbol() {
        return symbol;
    }

    public BulkOrderOrder withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getType() {
        return type;
    }

    public BulkOrderOrder withType(String type) {
        this.type = type;
        return this;
    }

    public String getSide() {
        return side;
    }

    public BulkOrderOrder withSide(String side) {
        this.side = side;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public BulkOrderOrder withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getSize() {
        return size;
    }

    public BulkOrderOrder withSize(String size) {
        this.size = size;
        return this;
    }

    public Object getFunds() {
        return funds;
    }

    public BulkOrderOrder withFunds(Object funds) {
        this.funds = funds;
        return this;
    }

    public String getStp() {
        return stp;
    }

    public BulkOrderOrder withStp(String stp) {
        this.stp = stp;
        return this;
    }

    public String getStop() {
        return stop;
    }

    public BulkOrderOrder withStop(String stop) {
        this.stop = stop;
        return this;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public BulkOrderOrder withStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
        return this;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public BulkOrderOrder withTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
        return this;
    }

    public Long getCancelAfter() {
        return cancelAfter;
    }

    public BulkOrderOrder withCancelAfter(Long cancelAfter) {
        this.cancelAfter = cancelAfter;
        return this;
    }

    public Boolean getPostOnly() {
        return postOnly;
    }

    public BulkOrderOrder withPostOnly(Boolean postOnly) {
        this.postOnly = postOnly;
        return this;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public BulkOrderOrder withHidden(Boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public Boolean getIceberg() {
        return iceberg;
    }

    public BulkOrderOrder withIceberg(Boolean iceberg) {
        this.iceberg = iceberg;
        return this;
    }

    public String getVisibleSize() {
        return visibleSize;
    }

    public BulkOrderOrder withVisibleSize(String visibleSize) {
        this.visibleSize = visibleSize;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public BulkOrderOrder withChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public Object getId() {
        return id;
    }

    public BulkOrderOrder withId(Object id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public BulkOrderOrder withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getFailMsg() {
        return failMsg;
    }

    public BulkOrderOrder withFailMsg(String failMsg) {
        this.failMsg = failMsg;
        return this;
    }

    public String getClientOid() {
        return clientOid;
    }

    public BulkOrderOrder withClientOid(String clientOid) {
        this.clientOid = clientOid;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BulkOrderOrder.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("side");
        sb.append('=');
        sb.append(((this.side == null)?"<null>":this.side));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("size");
        sb.append('=');
        sb.append(((this.size == null)?"<null>":this.size));
        sb.append(',');
        sb.append("funds");
        sb.append('=');
        sb.append(((this.funds == null)?"<null>":this.funds));
        sb.append(',');
        sb.append("stp");
        sb.append('=');
        sb.append(((this.stp == null)?"<null>":this.stp));
        sb.append(',');
        sb.append("stop");
        sb.append('=');
        sb.append(((this.stop == null)?"<null>":this.stop));
        sb.append(',');
        sb.append("stopPrice");
        sb.append('=');
        sb.append(((this.stopPrice == null)?"<null>":this.stopPrice));
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
        sb.append("channel");
        sb.append('=');
        sb.append(((this.channel == null)?"<null>":this.channel));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("failMsg");
        sb.append('=');
        sb.append(((this.failMsg == null)?"<null>":this.failMsg));
        sb.append(',');
        sb.append("clientOid");
        sb.append('=');
        sb.append(((this.clientOid == null)?"<null>":this.clientOid));
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
        result = ((result* 31)+((this.channel == null)? 0 :this.channel.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.stp == null)? 0 :this.stp.hashCode()));
        result = ((result* 31)+((this.postOnly == null)? 0 :this.postOnly.hashCode()));
        result = ((result* 31)+((this.stopPrice == null)? 0 :this.stopPrice.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.stop == null)? 0 :this.stop.hashCode()));
        result = ((result* 31)+((this.visibleSize == null)? 0 :this.visibleSize.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.iceberg == null)? 0 :this.iceberg.hashCode()));
        result = ((result* 31)+((this.failMsg == null)? 0 :this.failMsg.hashCode()));
        result = ((result* 31)+((this.funds == null)? 0 :this.funds.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.timeInForce == null)? 0 :this.timeInForce.hashCode()));
        result = ((result* 31)+((this.clientOid == null)? 0 :this.clientOid.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BulkOrderOrder) == false) {
            return false;
        }
        BulkOrderOrder rhs = ((BulkOrderOrder) other);
        return (((((((((((((((((((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.side == rhs.side)||((this.side!= null)&&this.side.equals(rhs.side))))&&((this.hidden == rhs.hidden)||((this.hidden!= null)&&this.hidden.equals(rhs.hidden))))&&((this.cancelAfter == rhs.cancelAfter)||((this.cancelAfter!= null)&&this.cancelAfter.equals(rhs.cancelAfter))))&&((this.channel == rhs.channel)||((this.channel!= null)&&this.channel.equals(rhs.channel))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.stp == rhs.stp)||((this.stp!= null)&&this.stp.equals(rhs.stp))))&&((this.postOnly == rhs.postOnly)||((this.postOnly!= null)&&this.postOnly.equals(rhs.postOnly))))&&((this.stopPrice == rhs.stopPrice)||((this.stopPrice!= null)&&this.stopPrice.equals(rhs.stopPrice))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.stop == rhs.stop)||((this.stop!= null)&&this.stop.equals(rhs.stop))))&&((this.visibleSize == rhs.visibleSize)||((this.visibleSize!= null)&&this.visibleSize.equals(rhs.visibleSize))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.iceberg == rhs.iceberg)||((this.iceberg!= null)&&this.iceberg.equals(rhs.iceberg))))&&((this.failMsg == rhs.failMsg)||((this.failMsg!= null)&&this.failMsg.equals(rhs.failMsg))))&&((this.funds == rhs.funds)||((this.funds!= null)&&this.funds.equals(rhs.funds))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.timeInForce == rhs.timeInForce)||((this.timeInForce!= null)&&this.timeInForce.equals(rhs.timeInForce))))&&((this.clientOid == rhs.clientOid)||((this.clientOid!= null)&&this.clientOid.equals(rhs.clientOid))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
