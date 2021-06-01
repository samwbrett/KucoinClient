
package schemas.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class RecentOrderOrder implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("opType")
    @Expose
    private RecentOrderOrder.OpType opType;
    @SerializedName("type")
    @Expose
    private RecentOrderOrder.Type type;
    @SerializedName("side")
    @Expose
    private RecentOrderOrder.Side side;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("size")
    @Expose
    private Double size;
    @SerializedName("funds")
    @Expose
    private Double funds;
    @SerializedName("dealFunds")
    @Expose
    private Double dealFunds;
    @SerializedName("dealSize")
    @Expose
    private Double dealSize;
    @SerializedName("fee")
    @Expose
    private Double fee;
    @SerializedName("feeCurrency")
    @Expose
    private String feeCurrency;
    @SerializedName("stp")
    @Expose
    private RecentOrderOrder.Stp stp;
    @SerializedName("stop")
    @Expose
    private String stop;
    @SerializedName("stopTriggered")
    @Expose
    private Boolean stopTriggered;
    @SerializedName("stopPrice")
    @Expose
    private String stopPrice;
    @SerializedName("timeInForce")
    @Expose
    private RecentOrderOrder.TimeInForce timeInForce;
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
    @SerializedName("cancelAfter")
    @Expose
    private Long cancelAfter;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("clientOid")
    @Expose
    private String clientOid;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("cancelExist")
    @Expose
    private Boolean cancelExist;
    @SerializedName("createdAt")
    @Expose
    private LocalDateTime createdAt;
    @SerializedName("tradeType")
    @Expose
    private RecentOrderOrder.TradeType tradeType;
    private final static long serialVersionUID = 7045642601621562410L;

    public String getId() {
        return id;
    }

    public RecentOrderOrder withId(String id) {
        this.id = id;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public RecentOrderOrder withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public RecentOrderOrder.OpType getOpType() {
        return opType;
    }

    public RecentOrderOrder withOpType(RecentOrderOrder.OpType opType) {
        this.opType = opType;
        return this;
    }

    public RecentOrderOrder.Type getType() {
        return type;
    }

    public RecentOrderOrder withType(RecentOrderOrder.Type type) {
        this.type = type;
        return this;
    }

    public RecentOrderOrder.Side getSide() {
        return side;
    }

    public RecentOrderOrder withSide(RecentOrderOrder.Side side) {
        this.side = side;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public RecentOrderOrder withPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public RecentOrderOrder withSize(Double size) {
        this.size = size;
        return this;
    }

    public Double getFunds() {
        return funds;
    }

    public RecentOrderOrder withFunds(Double funds) {
        this.funds = funds;
        return this;
    }

    public Double getDealFunds() {
        return dealFunds;
    }

    public RecentOrderOrder withDealFunds(Double dealFunds) {
        this.dealFunds = dealFunds;
        return this;
    }

    public Double getDealSize() {
        return dealSize;
    }

    public RecentOrderOrder withDealSize(Double dealSize) {
        this.dealSize = dealSize;
        return this;
    }

    public Double getFee() {
        return fee;
    }

    public RecentOrderOrder withFee(Double fee) {
        this.fee = fee;
        return this;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public RecentOrderOrder withFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
        return this;
    }

    public RecentOrderOrder.Stp getStp() {
        return stp;
    }

    public RecentOrderOrder withStp(RecentOrderOrder.Stp stp) {
        this.stp = stp;
        return this;
    }

    public String getStop() {
        return stop;
    }

    public RecentOrderOrder withStop(String stop) {
        this.stop = stop;
        return this;
    }

    public Boolean getStopTriggered() {
        return stopTriggered;
    }

    public RecentOrderOrder withStopTriggered(Boolean stopTriggered) {
        this.stopTriggered = stopTriggered;
        return this;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public RecentOrderOrder withStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
        return this;
    }

    public RecentOrderOrder.TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public RecentOrderOrder withTimeInForce(RecentOrderOrder.TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
        return this;
    }

    public Boolean getPostOnly() {
        return postOnly;
    }

    public RecentOrderOrder withPostOnly(Boolean postOnly) {
        this.postOnly = postOnly;
        return this;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public RecentOrderOrder withHidden(Boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public Boolean getIceberg() {
        return iceberg;
    }

    public RecentOrderOrder withIceberg(Boolean iceberg) {
        this.iceberg = iceberg;
        return this;
    }

    public Double getVisibleSize() {
        return visibleSize;
    }

    public RecentOrderOrder withVisibleSize(Double visibleSize) {
        this.visibleSize = visibleSize;
        return this;
    }

    public Long getCancelAfter() {
        return cancelAfter;
    }

    public RecentOrderOrder withCancelAfter(Long cancelAfter) {
        this.cancelAfter = cancelAfter;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public RecentOrderOrder withChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getClientOid() {
        return clientOid;
    }

    public RecentOrderOrder withClientOid(String clientOid) {
        this.clientOid = clientOid;
        return this;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public RecentOrderOrder withIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Boolean getCancelExist() {
        return cancelExist;
    }

    public RecentOrderOrder withCancelExist(Boolean cancelExist) {
        this.cancelExist = cancelExist;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public RecentOrderOrder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public RecentOrderOrder.TradeType getTradeType() {
        return tradeType;
    }

    public RecentOrderOrder withTradeType(RecentOrderOrder.TradeType tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RecentOrderOrder.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("opType");
        sb.append('=');
        sb.append(((this.opType == null)?"<null>":this.opType));
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
        sb.append("dealFunds");
        sb.append('=');
        sb.append(((this.dealFunds == null)?"<null>":this.dealFunds));
        sb.append(',');
        sb.append("dealSize");
        sb.append('=');
        sb.append(((this.dealSize == null)?"<null>":this.dealSize));
        sb.append(',');
        sb.append("fee");
        sb.append('=');
        sb.append(((this.fee == null)?"<null>":this.fee));
        sb.append(',');
        sb.append("feeCurrency");
        sb.append('=');
        sb.append(((this.feeCurrency == null)?"<null>":this.feeCurrency));
        sb.append(',');
        sb.append("stp");
        sb.append('=');
        sb.append(((this.stp == null)?"<null>":this.stp));
        sb.append(',');
        sb.append("stop");
        sb.append('=');
        sb.append(((this.stop == null)?"<null>":this.stop));
        sb.append(',');
        sb.append("stopTriggered");
        sb.append('=');
        sb.append(((this.stopTriggered == null)?"<null>":this.stopTriggered));
        sb.append(',');
        sb.append("stopPrice");
        sb.append('=');
        sb.append(((this.stopPrice == null)?"<null>":this.stopPrice));
        sb.append(',');
        sb.append("timeInForce");
        sb.append('=');
        sb.append(((this.timeInForce == null)?"<null>":this.timeInForce));
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
        sb.append("cancelAfter");
        sb.append('=');
        sb.append(((this.cancelAfter == null)?"<null>":this.cancelAfter));
        sb.append(',');
        sb.append("channel");
        sb.append('=');
        sb.append(((this.channel == null)?"<null>":this.channel));
        sb.append(',');
        sb.append("clientOid");
        sb.append('=');
        sb.append(((this.clientOid == null)?"<null>":this.clientOid));
        sb.append(',');
        sb.append("isActive");
        sb.append('=');
        sb.append(((this.isActive == null)?"<null>":this.isActive));
        sb.append(',');
        sb.append("cancelExist");
        sb.append('=');
        sb.append(((this.cancelExist == null)?"<null>":this.cancelExist));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("tradeType");
        sb.append('=');
        sb.append(((this.tradeType == null)?"<null>":this.tradeType));
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
        result = ((result* 31)+((this.hidden == null)? 0 :this.hidden.hashCode()));
        result = ((result* 31)+((this.opType == null)? 0 :this.opType.hashCode()));
        result = ((result* 31)+((this.fee == null)? 0 :this.fee.hashCode()));
        result = ((result* 31)+((this.channel == null)? 0 :this.channel.hashCode()));
        result = ((result* 31)+((this.feeCurrency == null)? 0 :this.feeCurrency.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.isActive == null)? 0 :this.isActive.hashCode()));
        result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
        result = ((result* 31)+((this.visibleSize == null)? 0 :this.visibleSize.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.iceberg == null)? 0 :this.iceberg.hashCode()));
        result = ((result* 31)+((this.stopTriggered == null)? 0 :this.stopTriggered.hashCode()));
        result = ((result* 31)+((this.funds == null)? 0 :this.funds.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.timeInForce == null)? 0 :this.timeInForce.hashCode()));
        result = ((result* 31)+((this.tradeType == null)? 0 :this.tradeType.hashCode()));
        result = ((result* 31)+((this.side == null)? 0 :this.side.hashCode()));
        result = ((result* 31)+((this.dealSize == null)? 0 :this.dealSize.hashCode()));
        result = ((result* 31)+((this.cancelAfter == null)? 0 :this.cancelAfter.hashCode()));
        result = ((result* 31)+((this.dealFunds == null)? 0 :this.dealFunds.hashCode()));
        result = ((result* 31)+((this.stp == null)? 0 :this.stp.hashCode()));
        result = ((result* 31)+((this.postOnly == null)? 0 :this.postOnly.hashCode()));
        result = ((result* 31)+((this.stopPrice == null)? 0 :this.stopPrice.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.stop == null)? 0 :this.stop.hashCode()));
        result = ((result* 31)+((this.cancelExist == null)? 0 :this.cancelExist.hashCode()));
        result = ((result* 31)+((this.clientOid == null)? 0 :this.clientOid.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RecentOrderOrder) == false) {
            return false;
        }
        RecentOrderOrder rhs = ((RecentOrderOrder) other);
        return (((((((((((((((((((((((((((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.hidden == rhs.hidden)||((this.hidden!= null)&&this.hidden.equals(rhs.hidden))))&&((this.opType == rhs.opType)||((this.opType!= null)&&this.opType.equals(rhs.opType))))&&((this.fee == rhs.fee)||((this.fee!= null)&&this.fee.equals(rhs.fee))))&&((this.channel == rhs.channel)||((this.channel!= null)&&this.channel.equals(rhs.channel))))&&((this.feeCurrency == rhs.feeCurrency)||((this.feeCurrency!= null)&&this.feeCurrency.equals(rhs.feeCurrency))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.isActive == rhs.isActive)||((this.isActive!= null)&&this.isActive.equals(rhs.isActive))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.visibleSize == rhs.visibleSize)||((this.visibleSize!= null)&&this.visibleSize.equals(rhs.visibleSize))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.iceberg == rhs.iceberg)||((this.iceberg!= null)&&this.iceberg.equals(rhs.iceberg))))&&((this.stopTriggered == rhs.stopTriggered)||((this.stopTriggered!= null)&&this.stopTriggered.equals(rhs.stopTriggered))))&&((this.funds == rhs.funds)||((this.funds!= null)&&this.funds.equals(rhs.funds))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.timeInForce == rhs.timeInForce)||((this.timeInForce!= null)&&this.timeInForce.equals(rhs.timeInForce))))&&((this.tradeType == rhs.tradeType)||((this.tradeType!= null)&&this.tradeType.equals(rhs.tradeType))))&&((this.side == rhs.side)||((this.side!= null)&&this.side.equals(rhs.side))))&&((this.dealSize == rhs.dealSize)||((this.dealSize!= null)&&this.dealSize.equals(rhs.dealSize))))&&((this.cancelAfter == rhs.cancelAfter)||((this.cancelAfter!= null)&&this.cancelAfter.equals(rhs.cancelAfter))))&&((this.dealFunds == rhs.dealFunds)||((this.dealFunds!= null)&&this.dealFunds.equals(rhs.dealFunds))))&&((this.stp == rhs.stp)||((this.stp!= null)&&this.stp.equals(rhs.stp))))&&((this.postOnly == rhs.postOnly)||((this.postOnly!= null)&&this.postOnly.equals(rhs.postOnly))))&&((this.stopPrice == rhs.stopPrice)||((this.stopPrice!= null)&&this.stopPrice.equals(rhs.stopPrice))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.stop == rhs.stop)||((this.stop!= null)&&this.stop.equals(rhs.stop))))&&((this.cancelExist == rhs.cancelExist)||((this.cancelExist!= null)&&this.cancelExist.equals(rhs.cancelExist))))&&((this.clientOid == rhs.clientOid)||((this.clientOid!= null)&&this.clientOid.equals(rhs.clientOid))));
    }

    @Generated("jsonschema2pojo")
    public enum OpType {

        @SerializedName("DEAL")
        DEAL("DEAL");
        private final String value;
        private final static Map<String, RecentOrderOrder.OpType> CONSTANTS = new HashMap<String, RecentOrderOrder.OpType>();

        static {
            for (RecentOrderOrder.OpType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        OpType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static RecentOrderOrder.OpType fromValue(String value) {
            RecentOrderOrder.OpType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum Side {

        @SerializedName("buy")
        BUY("buy"),
        @SerializedName("sell")
        SELL("sell");
        private final String value;
        private final static Map<String, RecentOrderOrder.Side> CONSTANTS = new HashMap<String, RecentOrderOrder.Side>();

        static {
            for (RecentOrderOrder.Side c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Side(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static RecentOrderOrder.Side fromValue(String value) {
            RecentOrderOrder.Side constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum Stp {

        @SerializedName("CN")
        CN("CN"),
        @SerializedName("CO")
        CO("CO"),
        @SerializedName("CB")
        CB("CB"),
        @SerializedName("DC")
        DC("DC");
        private final String value;
        private final static Map<String, RecentOrderOrder.Stp> CONSTANTS = new HashMap<String, RecentOrderOrder.Stp>();

        static {
            for (RecentOrderOrder.Stp c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Stp(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static RecentOrderOrder.Stp fromValue(String value) {
            RecentOrderOrder.Stp constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum TimeInForce {

        @SerializedName("GTC")
        GTC("GTC"),
        @SerializedName("GTT")
        GTT("GTT"),
        @SerializedName("IOC")
        IOC("IOC"),
        @SerializedName("FOK")
        FOK("FOK");
        private final String value;
        private final static Map<String, RecentOrderOrder.TimeInForce> CONSTANTS = new HashMap<String, RecentOrderOrder.TimeInForce>();

        static {
            for (RecentOrderOrder.TimeInForce c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        TimeInForce(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static RecentOrderOrder.TimeInForce fromValue(String value) {
            RecentOrderOrder.TimeInForce constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum TradeType {

        @SerializedName("TRADE")
        TRADE("TRADE"),
        @SerializedName("MARGIN_TRADE")
        MARGIN_TRADE("MARGIN_TRADE");
        private final String value;
        private final static Map<String, RecentOrderOrder.TradeType> CONSTANTS = new HashMap<String, RecentOrderOrder.TradeType>();

        static {
            for (RecentOrderOrder.TradeType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        TradeType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static RecentOrderOrder.TradeType fromValue(String value) {
            RecentOrderOrder.TradeType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("jsonschema2pojo")
    public enum Type {

        @SerializedName("limit")
        LIMIT("limit"),
        @SerializedName("market")
        MARKET("market"),
        @SerializedName("limit_stop")
        LIMIT_STOP("limit_stop"),
        @SerializedName("market_stop")
        MARKET_STOP("market_stop");
        private final String value;
        private final static Map<String, RecentOrderOrder.Type> CONSTANTS = new HashMap<String, RecentOrderOrder.Type>();

        static {
            for (RecentOrderOrder.Type c: values()) {
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

        public static RecentOrderOrder.Type fromValue(String value) {
            RecentOrderOrder.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
