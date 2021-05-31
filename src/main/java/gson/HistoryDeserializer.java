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
        history.withSequence(jsonObject.get("sequence").getAsLong());
        history.withPrice(jsonObject.get("price").getAsDouble());
        history.withSize(jsonObject.get("size").getAsDouble());
        history.withSide(jsonObject.get("side").getAsString());
        history.withTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.get("time").getAsLong()), ZoneId.systemDefault()));
        return history;
    }

}
