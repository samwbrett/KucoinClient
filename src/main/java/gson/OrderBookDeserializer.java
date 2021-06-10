package gson;

import com.google.gson.*;
import schemas.objects.OrderBook;
import utils.GsonUtils;

import java.lang.reflect.Type;

public class OrderBookDeserializer implements JsonDeserializer<OrderBook> {

    @Override
    public OrderBook deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        OrderBook data = new OrderBook();
        JsonObject jsonObject = json.getAsJsonObject();
        GsonAdapters.addIfNonNullDateTime(jsonObject, data::withTimestamp, "timestamp");
        addIfNonNullBids(jsonObject, data);
        addIfNonNullAsks(jsonObject, data);
        return data;
    }

    private static void addIfNonNullBids(JsonObject object, OrderBook data) {
        JsonElement element = object.get("bids");
        if (element != null && element != JsonNull.INSTANCE) {
            data.withBids(GsonUtils.getPriceSizes((JsonArray) element));
        }
    }

    private static void addIfNonNullAsks(JsonObject object, OrderBook data) {
        JsonElement element = object.get("asks");
        if (element != null && element != JsonNull.INSTANCE) {
            data.withAsks(GsonUtils.getPriceSizes((JsonArray) element));
        }
    }

}
