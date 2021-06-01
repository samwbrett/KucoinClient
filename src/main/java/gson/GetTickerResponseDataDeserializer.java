package gson;

import com.google.gson.*;
import schemas.responses.Data__1;

import java.lang.reflect.Type;

public class GetTickerResponseDataDeserializer implements JsonDeserializer<Data__1> {

    @Override
    public Data__1 deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Data__1 data = new Data__1();
        JsonObject jsonObject = json.getAsJsonObject();
        GsonAdapters.addIfNonNullDateTime(jsonObject, data::withTime, "time");
        GsonAdapters.addIfNonNullLong(jsonObject, data::withSequence, "sequence");
        GsonAdapters.addIfNonNullDouble(jsonObject, data::withBestAsk, "bestAsk");
        GsonAdapters.addIfNonNullDouble(jsonObject, data::withBestAskSize, "bestAskSize");
        GsonAdapters.addIfNonNullDouble(jsonObject, data::withBestBid, "bestBid");
        GsonAdapters.addIfNonNullDouble(jsonObject, data::withBestBidSize, "bestBidSize");
        GsonAdapters.addIfNonNullDouble(jsonObject, data::withPrice, "price");
        GsonAdapters.addIfNonNullDouble(jsonObject, data::withSize, "size");
        return data;
    }
}