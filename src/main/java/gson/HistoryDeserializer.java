package gson;

import com.google.gson.*;
import schemas.objects.History;
import schemas.objects.RecentOrderOrder;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class HistoryDeserializer implements JsonDeserializer<History> {

    @Override
    public History deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        History history = new History();
        JsonObject jsonObject = json.getAsJsonObject();
        history.withSequence(jsonObject.get("sequence") != JsonNull.INSTANCE ? jsonObject.get("sequence").getAsLong() : null);
        history.withPrice(jsonObject.get("price") != JsonNull.INSTANCE ? jsonObject.get("price").getAsDouble() : null);
        history.withSize(jsonObject.get("size") != JsonNull.INSTANCE ? jsonObject.get("size").getAsDouble() : null);
        history.withSide(jsonObject.get("side") != JsonNull.INSTANCE ? jsonObject.get("side").getAsString() : null);
        history.withTime(jsonObject.get("time") != JsonNull.INSTANCE ? LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.get("time").getAsLong()), ZoneId.systemDefault()) : null);
        return history;
    }

}
