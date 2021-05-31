package gson;

import com.google.gson.*;
import schemas.objects.RecentOrderOrder;
import schemas.responses.Data__1;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class RecentOrderOrderDeserializer implements JsonDeserializer<RecentOrderOrder> {

    @Override
    public RecentOrderOrder deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        RecentOrderOrder recentOrder = new RecentOrderOrder();
        JsonObject jsonObject = json.getAsJsonObject();
        recentOrder.withId(jsonObject.get("id").getAsString());
        recentOrder.withSymbol(jsonObject.get("symbol").getAsString());
        recentOrder.withOpType(jsonObject.get("opType").getAsString());
        recentOrder.withType(jsonObject.get("type").getAsString());
        recentOrder.withSide(jsonObject.get("side").getAsString());
        recentOrder.withPrice(jsonObject.get("price").getAsDouble());
        recentOrder.withSize(jsonObject.get("size").getAsDouble());
        recentOrder.withFunds(jsonObject.get("funds").getAsDouble());
        recentOrder.withDealFunds(jsonObject.get("dealFunds").getAsDouble());
        recentOrder.withDealSize(jsonObject.get("dealSize").getAsDouble());
        recentOrder.withFee(jsonObject.get("fee").getAsDouble());
        recentOrder.withFeeCurrency(jsonObject.get("feeCurrency").getAsString());
        recentOrder.withStp(jsonObject.get("stp").getAsString());
        recentOrder.withStop(jsonObject.get("stop").getAsString());
        recentOrder.withStopTriggered(jsonObject.get("stopTriggered").getAsBoolean());
        recentOrder.withStopPrice(jsonObject.get("stopPrice").getAsString());
        recentOrder.withTimeInForce(jsonObject.get("timeInForce").getAsString());
        recentOrder.withPostOnly(jsonObject.get("postOnly").getAsBoolean());
        recentOrder.withHidden(jsonObject.get("hidden").getAsBoolean());
        recentOrder.withIceberg(jsonObject.get("iceberg").getAsBoolean());
        recentOrder.withVisibleSize(jsonObject.get("visibleSize").getAsDouble());
        recentOrder.withCancelAfter(jsonObject.get("cancelAfter").getAsLong());
        recentOrder.withChannel(jsonObject.get("channel").getAsString());
        recentOrder.withClientOid(jsonObject.get("clientOid").getAsString());
        recentOrder.withCancelExist(jsonObject.get("cancelExist").getAsBoolean());
        recentOrder.withCreatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.get("createdAt").getAsLong()), ZoneId.systemDefault()));
        recentOrder.withTradeType(jsonObject.get("tradeType").getAsString());
        return recentOrder;
    }

}
