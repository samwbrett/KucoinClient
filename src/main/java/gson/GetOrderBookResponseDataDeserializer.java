package gson;

import com.google.gson.*;
import schemas.objects.PriceSize;
import schemas.responses.Data;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class GetOrderBookResponseDataDeserializer implements JsonDeserializer<Data> {
    @Override
    public Data deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Data data = new Data();
        JsonObject jsonObject = json.getAsJsonObject();
        data.withTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.get("time").getAsLong()), ZoneId.systemDefault()));
        data.withSequence(jsonObject.get("sequence").getAsLong());
        data.withBids(getPriceSizes(jsonObject.getAsJsonArray("bids")));
        data.withAsks(getPriceSizes(jsonObject.getAsJsonArray("asks")));
        return data;
    }

    private List<PriceSize> getPriceSizes(JsonArray array) {
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
