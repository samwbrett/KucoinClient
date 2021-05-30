
package schemas.objects;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class SymbolInfo implements Serializable
{

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("baseCurrency")
    @Expose
    private String baseCurrency;
    @SerializedName("quoteCurrency")
    @Expose
    private String quoteCurrency;
    @SerializedName("baseMinSize")
    @Expose
    private Double baseMinSize;
    @SerializedName("quoteMinSize")
    @Expose
    private Double quoteMinSize;
    @SerializedName("baseMaxSize")
    @Expose
    private Double baseMaxSize;
    @SerializedName("quoteMaxSize")
    @Expose
    private Double quoteMaxSize;
    @SerializedName("baseIncrement")
    @Expose
    private Double baseIncrement;
    @SerializedName("quoteIncrement")
    @Expose
    private Double quoteIncrement;
    @SerializedName("priceIncrement")
    @Expose
    private Double priceIncrement;
    @SerializedName("feeCurrency")
    @Expose
    private String feeCurrency;
    @SerializedName("enableTrading")
    @Expose
    private Boolean enableTrading;
    @SerializedName("isMarginEnabled")
    @Expose
    private Boolean isMarginEnabled;
    @SerializedName("priceLimitRate")
    @Expose
    private String priceLimitRate;
    private final static long serialVersionUID = -3929693407279496228L;

    public String getSymbol() {
        return symbol;
    }

    public SymbolInfo withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getName() {
        return name;
    }

    public SymbolInfo withName(String name) {
        this.name = name;
        return this;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public SymbolInfo withBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
        return this;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public SymbolInfo withQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
        return this;
    }

    public Double getBaseMinSize() {
        return baseMinSize;
    }

    public SymbolInfo withBaseMinSize(Double baseMinSize) {
        this.baseMinSize = baseMinSize;
        return this;
    }

    public Double getQuoteMinSize() {
        return quoteMinSize;
    }

    public SymbolInfo withQuoteMinSize(Double quoteMinSize) {
        this.quoteMinSize = quoteMinSize;
        return this;
    }

    public Double getBaseMaxSize() {
        return baseMaxSize;
    }

    public SymbolInfo withBaseMaxSize(Double baseMaxSize) {
        this.baseMaxSize = baseMaxSize;
        return this;
    }

    public Double getQuoteMaxSize() {
        return quoteMaxSize;
    }

    public SymbolInfo withQuoteMaxSize(Double quoteMaxSize) {
        this.quoteMaxSize = quoteMaxSize;
        return this;
    }

    public Double getBaseIncrement() {
        return baseIncrement;
    }

    public SymbolInfo withBaseIncrement(Double baseIncrement) {
        this.baseIncrement = baseIncrement;
        return this;
    }

    public Double getQuoteIncrement() {
        return quoteIncrement;
    }

    public SymbolInfo withQuoteIncrement(Double quoteIncrement) {
        this.quoteIncrement = quoteIncrement;
        return this;
    }

    public Double getPriceIncrement() {
        return priceIncrement;
    }

    public SymbolInfo withPriceIncrement(Double priceIncrement) {
        this.priceIncrement = priceIncrement;
        return this;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public SymbolInfo withFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
        return this;
    }

    public Boolean getEnableTrading() {
        return enableTrading;
    }

    public SymbolInfo withEnableTrading(Boolean enableTrading) {
        this.enableTrading = enableTrading;
        return this;
    }

    public Boolean getIsMarginEnabled() {
        return isMarginEnabled;
    }

    public SymbolInfo withIsMarginEnabled(Boolean isMarginEnabled) {
        this.isMarginEnabled = isMarginEnabled;
        return this;
    }

    public String getPriceLimitRate() {
        return priceLimitRate;
    }

    public SymbolInfo withPriceLimitRate(String priceLimitRate) {
        this.priceLimitRate = priceLimitRate;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SymbolInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("baseCurrency");
        sb.append('=');
        sb.append(((this.baseCurrency == null)?"<null>":this.baseCurrency));
        sb.append(',');
        sb.append("quoteCurrency");
        sb.append('=');
        sb.append(((this.quoteCurrency == null)?"<null>":this.quoteCurrency));
        sb.append(',');
        sb.append("baseMinSize");
        sb.append('=');
        sb.append(((this.baseMinSize == null)?"<null>":this.baseMinSize));
        sb.append(',');
        sb.append("quoteMinSize");
        sb.append('=');
        sb.append(((this.quoteMinSize == null)?"<null>":this.quoteMinSize));
        sb.append(',');
        sb.append("baseMaxSize");
        sb.append('=');
        sb.append(((this.baseMaxSize == null)?"<null>":this.baseMaxSize));
        sb.append(',');
        sb.append("quoteMaxSize");
        sb.append('=');
        sb.append(((this.quoteMaxSize == null)?"<null>":this.quoteMaxSize));
        sb.append(',');
        sb.append("baseIncrement");
        sb.append('=');
        sb.append(((this.baseIncrement == null)?"<null>":this.baseIncrement));
        sb.append(',');
        sb.append("quoteIncrement");
        sb.append('=');
        sb.append(((this.quoteIncrement == null)?"<null>":this.quoteIncrement));
        sb.append(',');
        sb.append("priceIncrement");
        sb.append('=');
        sb.append(((this.priceIncrement == null)?"<null>":this.priceIncrement));
        sb.append(',');
        sb.append("feeCurrency");
        sb.append('=');
        sb.append(((this.feeCurrency == null)?"<null>":this.feeCurrency));
        sb.append(',');
        sb.append("enableTrading");
        sb.append('=');
        sb.append(((this.enableTrading == null)?"<null>":this.enableTrading));
        sb.append(',');
        sb.append("isMarginEnabled");
        sb.append('=');
        sb.append(((this.isMarginEnabled == null)?"<null>":this.isMarginEnabled));
        sb.append(',');
        sb.append("priceLimitRate");
        sb.append('=');
        sb.append(((this.priceLimitRate == null)?"<null>":this.priceLimitRate));
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
        result = ((result* 31)+((this.priceLimitRate == null)? 0 :this.priceLimitRate.hashCode()));
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.quoteMaxSize == null)? 0 :this.quoteMaxSize.hashCode()));
        result = ((result* 31)+((this.enableTrading == null)? 0 :this.enableTrading.hashCode()));
        result = ((result* 31)+((this.priceIncrement == null)? 0 :this.priceIncrement.hashCode()));
        result = ((result* 31)+((this.baseMaxSize == null)? 0 :this.baseMaxSize.hashCode()));
        result = ((result* 31)+((this.feeCurrency == null)? 0 :this.feeCurrency.hashCode()));
        result = ((result* 31)+((this.baseCurrency == null)? 0 :this.baseCurrency.hashCode()));
        result = ((result* 31)+((this.quoteCurrency == null)? 0 :this.quoteCurrency.hashCode()));
        result = ((result* 31)+((this.quoteIncrement == null)? 0 :this.quoteIncrement.hashCode()));
        result = ((result* 31)+((this.baseMinSize == null)? 0 :this.baseMinSize.hashCode()));
        result = ((result* 31)+((this.quoteMinSize == null)? 0 :this.quoteMinSize.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.baseIncrement == null)? 0 :this.baseIncrement.hashCode()));
        result = ((result* 31)+((this.isMarginEnabled == null)? 0 :this.isMarginEnabled.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SymbolInfo) == false) {
            return false;
        }
        SymbolInfo rhs = ((SymbolInfo) other);
        return ((((((((((((((((this.priceLimitRate == rhs.priceLimitRate)||((this.priceLimitRate!= null)&&this.priceLimitRate.equals(rhs.priceLimitRate)))&&((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol))))&&((this.quoteMaxSize == rhs.quoteMaxSize)||((this.quoteMaxSize!= null)&&this.quoteMaxSize.equals(rhs.quoteMaxSize))))&&((this.enableTrading == rhs.enableTrading)||((this.enableTrading!= null)&&this.enableTrading.equals(rhs.enableTrading))))&&((this.priceIncrement == rhs.priceIncrement)||((this.priceIncrement!= null)&&this.priceIncrement.equals(rhs.priceIncrement))))&&((this.baseMaxSize == rhs.baseMaxSize)||((this.baseMaxSize!= null)&&this.baseMaxSize.equals(rhs.baseMaxSize))))&&((this.feeCurrency == rhs.feeCurrency)||((this.feeCurrency!= null)&&this.feeCurrency.equals(rhs.feeCurrency))))&&((this.baseCurrency == rhs.baseCurrency)||((this.baseCurrency!= null)&&this.baseCurrency.equals(rhs.baseCurrency))))&&((this.quoteCurrency == rhs.quoteCurrency)||((this.quoteCurrency!= null)&&this.quoteCurrency.equals(rhs.quoteCurrency))))&&((this.quoteIncrement == rhs.quoteIncrement)||((this.quoteIncrement!= null)&&this.quoteIncrement.equals(rhs.quoteIncrement))))&&((this.baseMinSize == rhs.baseMinSize)||((this.baseMinSize!= null)&&this.baseMinSize.equals(rhs.baseMinSize))))&&((this.quoteMinSize == rhs.quoteMinSize)||((this.quoteMinSize!= null)&&this.quoteMinSize.equals(rhs.quoteMinSize))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.baseIncrement == rhs.baseIncrement)||((this.baseIncrement!= null)&&this.baseIncrement.equals(rhs.baseIncrement))))&&((this.isMarginEnabled == rhs.isMarginEnabled)||((this.isMarginEnabled!= null)&&this.isMarginEnabled.equals(rhs.isMarginEnabled))));
    }

}
