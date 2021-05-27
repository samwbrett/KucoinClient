package responses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolListResponse extends KucoinResponse {

    private final Map<String, SymbolInfo> infoMap = new HashMap<>();

    public SymbolListResponse(KucoinResponse response) {
        super(response);
        buildMap();
    }

    private void buildMap() {
        List<Map<String, Object>> data = (List<Map<String, Object>>) getResponseMap().get("data");
        for (Map<String, Object> datum : data) {
            String symbol = (String) datum.get("symbol");
            String baseMinSizeString = (String) datum.get("baseMinSize");
            String quoteMinSizeString = (String) datum.get("quoteMinSize");
            String baseMaxSizeString = (String) datum.get("baseMaxSize");
            String quoteMaxSizeString = (String) datum.get("quoteMaxSize");
            String baseIncrementString = (String) datum.get("baseIncrement");
            String quoteIncrementString = (String) datum.get("quoteIncrement");
            String priceIncrementString = (String) datum.get("priceIncrement");
            String priceLimitRateString = (String) datum.get("priceLimitRate");
            Boolean isMarginEnabled = (Boolean) datum.get("isMarginEnabled");
            Boolean enableTrading = (Boolean) datum.get("enableTrading");
            if (symbol == null
                || baseMinSizeString == null
                || quoteMinSizeString == null
                || baseMaxSizeString == null
                || quoteMaxSizeString == null
                || baseIncrementString == null
                || quoteIncrementString == null
                || priceIncrementString == null
                || priceLimitRateString == null
                || isMarginEnabled == null
                || enableTrading == null) {
                continue;
            }

            double baseMinSize = Double.parseDouble(baseMinSizeString);
            double quoteMinSize = Double.parseDouble(quoteMinSizeString);
            double baseMaxSize = Double.parseDouble(baseMaxSizeString);
            double quoteMaxSize = Double.parseDouble(quoteMaxSizeString);
            double baseIncrement = Double.parseDouble(baseIncrementString);
            double quoteIncrement = Double.parseDouble(quoteIncrementString);
            double priceIncrement = Double.parseDouble(priceIncrementString);
            double priceLimitRate = Double.parseDouble(priceLimitRateString);
            infoMap.put(symbol,
                    new SymbolInfo(
                            symbol,
                            baseMinSize,
                            quoteMinSize,
                            baseMaxSize,
                            quoteMaxSize,
                            baseIncrement,
                            quoteIncrement,
                            priceIncrement,
                            priceLimitRate,
                            isMarginEnabled,
                            enableTrading
                    ));
        }
    }

    public SymbolInfo getSymbolInfo(String symbol) {
        return infoMap.get(symbol);
    }

    public class SymbolInfo {

        private final String symbol;
        private final double baseMinSize;
        private final double quoteMinSize;
        private final double baseMaxSize;
        private final double quoteMaxSize;
        private final double baseIncrement;
        private final double quoteIncrement;
        private final double priceIncrement;
        private final double priceLimitRate;
        private final boolean isMarginEnabled;
        private final boolean enableTrading;

        private SymbolInfo(String symbol,
                           double baseMinSize,
                           double quoteMinSize,
                           double baseMaxSize,
                           double quoteMaxSize,
                           double baseIncrement,
                           double quoteIncrement,
                           double priceIncrement,
                           double priceLimitRate,
                           boolean isMarginEnabled,
                           boolean enableTrading) {

            this.symbol = symbol;
            this.baseMinSize = baseMinSize;
            this.quoteMinSize = quoteMinSize;
            this.baseMaxSize = baseMaxSize;
            this.quoteMaxSize = quoteMaxSize;
            this.baseIncrement = baseIncrement;
            this.quoteIncrement = quoteIncrement;
            this.priceIncrement = priceIncrement;
            this.priceLimitRate = priceLimitRate;
            this.isMarginEnabled = isMarginEnabled;
            this.enableTrading = enableTrading;
        }

        public String toString() {
            return String.join("\n",
                    "Symbol: " + symbol,
                    "Base Min Size: " + baseMinSize,
                    "Quote Min Size: " + quoteMinSize,
                    "Base Max Size: " + baseMaxSize,
                    "Quote Max Size: " + quoteMaxSize,
                    "Base Increment: " + baseIncrement,
                    "Quote Increment: " + quoteIncrement,
                    "Price Increment: " + priceIncrement,
                    "Price Limit Rate: " + priceLimitRate,
                    "Is Margin Enabled: " + isMarginEnabled,
                    "Enable Trading: " + enableTrading);
        }

        public String getSymbol() {
            return symbol;
        }

        public double getBaseMinSize() {
            return baseMinSize;
        }

        public double getQuoteMinSize() {
            return quoteMinSize;
        }

        public double getBaseMaxSize() {
            return baseMaxSize;
        }

        public double getQuoteMaxSize() {
            return quoteMaxSize;
        }

        public double getBaseIncrement() {
            return baseIncrement;
        }

        public double getQuoteIncrement() {
            return quoteIncrement;
        }

        public double getPriceIncrement() {
            return priceIncrement;
        }

        public double getPriceLimitRate() {
            return priceLimitRate;
        }

        public boolean isMarginEnabled() {
            return isMarginEnabled;
        }

        public boolean isEnableTrading() {
            return enableTrading;
        }
    }

}
