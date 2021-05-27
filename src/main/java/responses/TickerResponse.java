package responses;

import java.util.Map;

public class TickerResponse extends KucoinResponse {

    public TickerResponse(KucoinResponse response) {
        super(response);
    }

    private Map<String, Object> getDataMap() {
        return (Map<String, Object>) responseMap.get("data");
    }

    private Double getDoubleValue(String key) {
        Map<String, Object> dataMap = getDataMap();
        if (dataMap == null) {
            return null;
        }

        String stringValue = (String) dataMap.get(key);
        if (stringValue == null) {
            return null;
        }

        double doubleValue = Double.parseDouble(stringValue);
        return doubleValue == 0 ? null : doubleValue;
    }

    public Double getBestAskPrice() {
        return getDoubleValue("bestAsk");
    }

    public Double getBestBidPrice() {
        return getDoubleValue("bestBid");
    }

    public Double getLastTradedPrice() {
        return getDoubleValue("price");
    }
}
