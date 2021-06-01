package gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeConverter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(json.getAsLong()), ZoneId.systemDefault());
    }

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toInstant(ZoneId.systemDefault().getRules().getOffset(src)).toEpochMilli());
    }
}
