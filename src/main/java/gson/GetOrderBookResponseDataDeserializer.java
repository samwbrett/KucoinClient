package gson;

import com.google.gson.*;
import schemas.objects.PriceSize;
import schemas.responses.Data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetOrderBookResponseDataDeserializer implements JsonDeserializer<Data> {
    @Override
    public Data deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Data data = new Data();
        JsonObject jsonObject = json.getAsJsonObject();
        GsonAdapters.addIfNonNullDateTime(jsonObject, data::withTime, "time");
        GsonAdapters.addIfNonNullLong(jsonObject, data::withSequence, "sequence");
        addIfNonNullBids(jsonObject, data);
        addIfNonNullAsks(jsonObject, data);
        return data;
    }

    private static void addIfNonNullBids(JsonObject object, Data data) {
        JsonElement element = object.get("bids");
        if (element != null && element != JsonNull.INSTANCE) {
            data.withBids(getPriceSizes((JsonArray) element));
        }
    }

    private static void addIfNonNullAsks(JsonObject object, Data data) {
        JsonElement element = object.get("asks");
        if (element != null && element != JsonNull.INSTANCE) {
            data.withAsks(getPriceSizes((JsonArray) element));
        }
    }

    private static List<PriceSize> getPriceSizes(JsonArray array) {
        if (array == null) {
            return Collections.emptyList();
        }

        List<PriceSize> priceSizes = new ArrayList<>(array.size());
        for (JsonElement jsonElement : array) {
            PriceSize priceSize = new PriceSize();
            JsonArray priceSizeArray = jsonElement.getAsJsonArray();
            priceSize.withPrice(priceSizeArray.get(0).getAsDouble());
            priceSize.withSize(priceSizeArray.get(1).getAsDouble());
            priceSizes.add(priceSize);
        }
        return priceSizes;
    }
}
