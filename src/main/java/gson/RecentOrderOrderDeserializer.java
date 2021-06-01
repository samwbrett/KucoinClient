package gson;

import com.google.gson.*;
import schemas.objects.RecentOrderOrder;

import java.lang.reflect.Type;

public class RecentOrderOrderDeserializer implements JsonDeserializer<RecentOrderOrder> {

    @Override
    public RecentOrderOrder deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        RecentOrderOrder recentOrder = new RecentOrderOrder();
        JsonObject jsonObject = json.getAsJsonObject();

        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withId, "id");
        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withSymbol, "symbol");
        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withOpType, "opType");
        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withType, "type");
        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withSide, "side");

        GsonAdapters.addIfNonNullDouble(jsonObject, recentOrder::withPrice, "price");
        GsonAdapters.addIfNonNullDouble(jsonObject, recentOrder::withSize, "size");
        GsonAdapters.addIfNonNullDouble(jsonObject, recentOrder::withFunds, "funds");
        GsonAdapters.addIfNonNullDouble(jsonObject, recentOrder::withDealFunds, "dealFunds");
        GsonAdapters.addIfNonNullDouble(jsonObject, recentOrder::withDealSize, "dealSize");
        GsonAdapters.addIfNonNullDouble(jsonObject, recentOrder::withFee, "fee");

        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withFeeCurrency, "feeCurrency");
        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withStp, "stp");
        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withStop, "stop");

        GsonAdapters.addIfNonNullBoolean(jsonObject, recentOrder::withStopTriggered, "stopTriggered");

        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withStopPrice, "stopPrice");
        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withTimeInForce, "timeInForce");

        GsonAdapters.addIfNonNullBoolean(jsonObject, recentOrder::withPostOnly, "postOnly");
        GsonAdapters.addIfNonNullBoolean(jsonObject, recentOrder::withHidden, "hidden");
        GsonAdapters.addIfNonNullBoolean(jsonObject, recentOrder::withIceberg, "iceberg");

        GsonAdapters.addIfNonNullDouble(jsonObject, recentOrder::withVisibleSize, "visibleSize");
        GsonAdapters.addIfNonNullLong(jsonObject, recentOrder::withCancelAfter, "cancelAfter");
        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withChannel, "channel");
        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withClientOid, "clientOid");

        GsonAdapters.addIfNonNullBoolean(jsonObject, recentOrder::withIsActive, "isActive");
        GsonAdapters.addIfNonNullBoolean(jsonObject, recentOrder::withCancelExist, "cancelExist");
        GsonAdapters.addIfNonNullDateTime(jsonObject, recentOrder::withCreatedAt, "createdAt");
        GsonAdapters.addIfNonNullString(jsonObject, recentOrder::withTradeType, "tradeType");

        return recentOrder;
    }

}
