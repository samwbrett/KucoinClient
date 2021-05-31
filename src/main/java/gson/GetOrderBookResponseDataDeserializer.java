package gson;

import com.google.gson.*;
import schemas.objects.PriceSize;
import schemas.responses.Data;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetOrderBookResponseDataDeserializer implements JsonDeserializer<Data> {
    @Override
    public Data deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Data data = new Data();
        JsonObject jsonObject = json.getAsJsonObject();
        data.withTime(jsonObject.get("time") != JsonNull.INSTANCE ? LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.get("time").getAsLong()), ZoneId.systemDefault()) : null);
        data.withSequence(jsonObject.get("sequence") != JsonNull.INSTANCE ? jsonObject.get("sequence").getAsLong() : null);
        data.withBids(getPriceSizes(jsonObject.get("bids") != JsonNull.INSTANCE ? jsonObject.getAsJsonArray("bids") : null));
        data.withAsks(getPriceSizes(jsonObject.get("asks") != JsonNull.INSTANCE ? jsonObject.getAsJsonArray("asks") : null));
        return data;
    }

    private List<PriceSize> getPriceSizes(JsonArray array) {
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
