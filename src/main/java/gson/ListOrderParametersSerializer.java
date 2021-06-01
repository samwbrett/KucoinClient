package gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import schemas.requests.ListOrdersParameters;

import java.lang.reflect.Type;

public class ListOrderParametersSerializer implements JsonSerializer<ListOrdersParameters> {
    @Override
    public JsonElement serialize(ListOrdersParameters src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        GsonAdapters.addIfNonNull(jsonObject, "status", src.getStatus(), ListOrdersParameters.Status::value);
        GsonAdapters.addIfNonNull(jsonObject, "symbol", src.getSymbol());
        GsonAdapters.addIfNonNull(jsonObject, "side", src.getSide(), ListOrdersParameters.Side::value);
        GsonAdapters.addIfNonNull(jsonObject, "type", src.getType(), ListOrdersParameters.Type::value);
        GsonAdapters.addIfNonNull(jsonObject, "tradeType", src.getTradeType(), ListOrdersParameters.TradeType::value);
        GsonAdapters.addIfNonNull(jsonObject, "startAt", src.getStartAt());
        GsonAdapters.addIfNonNull(jsonObject, "endAt", src.getEndAt());
        return jsonObject;
    }
}
