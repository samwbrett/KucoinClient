package jsoniter;

import com.jsoniter.spi.Decoder;
import com.jsoniter.spi.JsoniterSpi;
import schemas.objects.History;
import schemas.objects.RecentOrderOrder;
import schemas.requests.ListOrdersParameters;
import schemas.responses.Data;
import schemas.responses.Data__1;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class JsoniterAdapters {

    private static final Decoder DATE_TIME_ENCODER = iter -> {
        long milli = Long.parseLong(iter.toString());
        if (milli > 1000000000000000000L) {
            milli /= 1000000L;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milli), ZoneId.systemDefault());
    };

    static {
        JsoniterSpi.registerPropertyDecoder(History.class, "time", DATE_TIME_ENCODER);
        JsoniterSpi.registerPropertyDecoder(RecentOrderOrder.class, "createdAt", DATE_TIME_ENCODER);
        JsoniterSpi.registerPropertyDecoder(ListOrdersParameters.class, "startAt", DATE_TIME_ENCODER);
        JsoniterSpi.registerPropertyDecoder(ListOrdersParameters.class, "endAt", DATE_TIME_ENCODER);
        JsoniterSpi.registerPropertyDecoder(Data.class, "time", DATE_TIME_ENCODER);
        JsoniterSpi.registerPropertyDecoder(Data__1.class, "time", DATE_TIME_ENCODER);
    }

    public static void startup() { }

}
