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
        data.withTime(jsonObject.get("time") != JsonNull.INSTANCE ? LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.get("time").getAsLong()), ZoneId.systemDefault()) : null);
        data.withSequence(jsonObject.get("sequence") != JsonNull.INSTANCE ? jsonObject.get("sequence").getAsLong() : null );
        data.withBestAsk(jsonObject.get("bestAsk") != JsonNull.INSTANCE ? jsonObject.get("bestAsk").getAsDouble() : null );
        data.withBestAskSize(jsonObject.get("bestAskSize") != JsonNull.INSTANCE ? jsonObject.get("bestAskSize").getAsDouble() : null );
        data.withBestBid(jsonObject.get("bestBid") != JsonNull.INSTANCE ? jsonObject.get("bestBid").getAsDouble() : null );
        data.withBestBidSize(jsonObject.get("bestBidSize") != JsonNull.INSTANCE ? jsonObject.get("bestBidSize").getAsDouble() : null );
        data.withPrice(jsonObject.get("price") != JsonNull.INSTANCE ? jsonObject.get("price").getAsDouble() : null );
        data.withSize(jsonObject.get("size") != JsonNull.INSTANCE ? jsonObject.get("size").getAsDouble() : null );
        return data;
    }
}