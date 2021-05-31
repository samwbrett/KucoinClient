package gson;

import com.google.gson.*;
import schemas.objects.RecentOrderOrder;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class RecentOrderOrderDeserializer implements JsonDeserializer<RecentOrderOrder> {

    @Override
    public RecentOrderOrder deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        RecentOrderOrder recentOrder = new RecentOrderOrder();
        JsonObject jsonObject = json.getAsJsonObject();
        recentOrder.withId(jsonObject.get("id") != JsonNull.INSTANCE ? jsonObject.get("id").getAsString() : null );
        recentOrder.withSymbol(jsonObject.get("symbol") != JsonNull.INSTANCE ? jsonObject.get("symbol").getAsString() : null );
        recentOrder.withOpType(jsonObject.get("opType") != JsonNull.INSTANCE ? jsonObject.get("opType").getAsString() : null );
        recentOrder.withType(jsonObject.get("type") != JsonNull.INSTANCE ? jsonObject.get("type").getAsString() : null );
        recentOrder.withSide(jsonObject.get("side") != JsonNull.INSTANCE ? jsonObject.get("side").getAsString() : null );
        recentOrder.withPrice(jsonObject.get("price") != JsonNull.INSTANCE ? jsonObject.get("price").getAsDouble() : null );
        recentOrder.withSize(jsonObject.get("size") != JsonNull.INSTANCE ? jsonObject.get("size").getAsDouble() : null );
        recentOrder.withFunds(jsonObject.get("funds") != JsonNull.INSTANCE ? jsonObject.get("funds").getAsDouble() : null );
        recentOrder.withDealFunds(jsonObject.get("dealFunds") != JsonNull.INSTANCE ? jsonObject.get("dealFunds").getAsDouble() : null );
        recentOrder.withDealSize(jsonObject.get("dealSize") != JsonNull.INSTANCE ? jsonObject.get("dealSize").getAsDouble() : null );
        recentOrder.withFee(jsonObject.get("fee") != JsonNull.INSTANCE ? jsonObject.get("fee").getAsDouble() : null );
        recentOrder.withFeeCurrency(jsonObject.get("feeCurrency") != JsonNull.INSTANCE ? jsonObject.get("feeCurrency").getAsString() : null );
        recentOrder.withStp(jsonObject.get("stp") != JsonNull.INSTANCE ? jsonObject.get("stp").getAsString() : null );
        recentOrder.withStop(jsonObject.get("stop") != JsonNull.INSTANCE ? jsonObject.get("stop").getAsString() : null );
        recentOrder.withStopTriggered(jsonObject.get("stopTriggered") != JsonNull.INSTANCE && jsonObject.get("stopTriggered").getAsBoolean());
        recentOrder.withStopPrice(jsonObject.get("stopPrice") != JsonNull.INSTANCE ? jsonObject.get("stopPrice").getAsString() : null );
        recentOrder.withTimeInForce(jsonObject.get("timeInForce") != JsonNull.INSTANCE ? jsonObject.get("timeInForce").getAsString() : null );
        recentOrder.withPostOnly(jsonObject.get("postOnly") != JsonNull.INSTANCE && jsonObject.get("postOnly").getAsBoolean());
        recentOrder.withHidden(jsonObject.get("hidden") != JsonNull.INSTANCE && jsonObject.get("hidden").getAsBoolean());
        recentOrder.withIceberg(jsonObject.get("iceberg") != JsonNull.INSTANCE && jsonObject.get("iceberg").getAsBoolean());
        recentOrder.withVisibleSize(jsonObject.get("visibleSize") != JsonNull.INSTANCE ? jsonObject.get("visibleSize").getAsDouble() : null );
        recentOrder.withCancelAfter(jsonObject.get("cancelAfter") != JsonNull.INSTANCE ? jsonObject.get("cancelAfter").getAsLong() : null );
        recentOrder.withChannel(jsonObject.get("channel") != JsonNull.INSTANCE ? jsonObject.get("channel").getAsString() : null );
        recentOrder.withClientOid(jsonObject.get("clientOid") != JsonNull.INSTANCE ? jsonObject.get("clientOid").getAsString() : null );
        recentOrder.withIsActive(jsonObject.get("isActive") != JsonNull.INSTANCE && jsonObject.get("isActive").getAsBoolean());
        recentOrder.withCancelExist(jsonObject.get("cancelExist") != JsonNull.INSTANCE && jsonObject.get("cancelExist").getAsBoolean());
        recentOrder.withCreatedAt(jsonObject.get("createdAt") != JsonNull.INSTANCE ? LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.get("createdAt").getAsLong()), ZoneId.systemDefault()) : null );
        recentOrder.withTradeType(jsonObject.get("tradeType") != JsonNull.INSTANCE ? jsonObject.get("tradeType").getAsString() : null );
        return recentOrder;
    }

}
