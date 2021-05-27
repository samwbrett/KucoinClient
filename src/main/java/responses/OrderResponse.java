package responses;

import java.util.Map;

public class OrderResponse extends KucoinResponse {

    public OrderResponse(KucoinResponse response) {
        super(response);
    }

    public String getOrderId() {
        return (String) getResponseMap().get("orderId");
    }

    private Map<String, Object> getDataMap() {
        return (Map<String, Object>) responseMap.get("data");
    }

    public boolean isActive() {
        Map<String, Object> dataMap = getDataMap();
        if (dataMap == null) {
            return true;
        }
        Boolean isActive = (Boolean) dataMap.get("isActive");
        return isActive != null && isActive;
    }

    public double getDealFunds() {
        Map<String, Object> dataMap = getDataMap();
        if (dataMap == null) {
            return 0;
        }
        String dealFunds = (String) dataMap.get("dealFunds");
        return dealFunds == null ? 0 : Double.parseDouble(dealFunds);
    }

    public double getDealSize() {
        Map<String, Object> dataMap = getDataMap();
        if (dataMap == null) {
            return 0;
        }
        String dealSize = (String) dataMap.get("dealSize");
        return dealSize == null ? 0 : Double.parseDouble(dealSize);
    }
}
