package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import schemas.objects.History;
import schemas.objects.RecentOrderOrder;
import schemas.responses.Data;
import schemas.responses.Data__1;

public class GsonAdapters {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Data.class, new GetOrderBookResponseDataDeserializer())
            .registerTypeAdapter(Data__1.class, new GetTickerResponseDataDeserializer())
            .registerTypeAdapter(RecentOrderOrder.class, new RecentOrderOrderDeserializer())
            .registerTypeAdapter(History.class, new HistoryDeserializer())
            .setPrettyPrinting()
            .create();

    public static Gson getGson() {
        return GSON;
    }

}
