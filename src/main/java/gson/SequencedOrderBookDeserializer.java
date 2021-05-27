package gson;

import com.google.gson.*;
import schemas.objects.SequencedOrderBook;
import utils.GsonUtils;

import java.lang.reflect.Type;

public class SequencedOrderBookDeserializer implements JsonDeserializer<SequencedOrderBook> {

    @Override
    public SequencedOrderBook deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        SequencedOrderBook data = new SequencedOrderBook();
        JsonObject jsonObject = json.getAsJsonObject();
        GsonAdapters.addIfNonNullDateTime(jsonObject, data::withTime, "time");
        GsonAdapters.addIfNonNullLong(jsonObject, data::withSequence, "sequence");
        addIfNonNullBids(jsonObject, data);
        addIfNonNullAsks(jsonObject, data);
        return data;
    }

    private static void addIfNonNullBids(JsonObject object, SequencedOrderBook data) {
        JsonElement element = object.get("bids");
        if (element != null && element != JsonNull.INSTANCE) {
            data.withBids(GsonUtils.getPriceSizes((JsonArray) element));
        }
    }

    private static void addIfNonNullAsks(JsonObject object, SequencedOrderBook data) {
        JsonElement element = object.get("asks");
        if (element != null && element != JsonNull.INSTANCE) {
            data.withAsks(GsonUtils.getPriceSizes((JsonArray) element));
        }
    }

}
