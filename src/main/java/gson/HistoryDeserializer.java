package gson;

import com.google.gson.*;
import schemas.objects.History;

import java.lang.reflect.Type;

public class HistoryDeserializer implements JsonDeserializer<History> {

    @Override
    public History deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        History history = new History();
        JsonObject jsonObject = json.getAsJsonObject();
        GsonAdapters.addIfNonNullLong(jsonObject, history::withSequence, "sequence");
        GsonAdapters.addIfNonNullDouble(jsonObject, history::withPrice, "price");
        GsonAdapters.addIfNonNullDouble(jsonObject, history::withSize, "size");
        GsonAdapters.addIfNonNullEnum(jsonObject, history::withSide, History.Side::fromValue, "side");
        GsonAdapters.addIfNonNullDateTimePicoSeconds(jsonObject, history::withTime, "time");
        return history;
    }

}
