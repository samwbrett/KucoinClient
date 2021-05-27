package responses;

import java.util.Map;

public class PlaceOrderResponse extends KucoinResponse {

    public PlaceOrderResponse(KucoinResponse response) {
        super(response);
    }

    public String getOrderId() {
        Map<String, Object> data = (Map<String, Object>) getResponseMap().get("data");
        if (data == null) {
            return null;
        }
        return (String) data.get("orderId");
    }
}
