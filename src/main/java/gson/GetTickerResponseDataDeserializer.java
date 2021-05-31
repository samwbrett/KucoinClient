package gson;

import com.google.gson.*;
import schemas.responses.Data__1;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class GetTickerResponseDataDeserializer implements JsonDeserializer<Data__1> {

    @Override
    public Data__1 deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Data__1 data = new Data__1();
        JsonObject jsonObject = json.getAsJsonObject();
        data.withTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.get("time").getAsLong()), ZoneId.systemDefault()));
        data.withSequence(jsonObject.get("sequence").getAsLong());
        data.withBestAsk(jsonObject.get("bestAsk").getAsDouble());
        data.withBestAskSize(jsonObject.get("bestAskSize").getAsDouble());
        data.withBestBid(jsonObject.get("bestBid").getAsDouble());
        data.withBestBidSize(jsonObject.get("bestBidSize").getAsDouble());
        data.withPrice(jsonObject.get("price").getAsDouble());
        data.withSize(jsonObject.get("size").getAsDouble());
        return data;
    }
}