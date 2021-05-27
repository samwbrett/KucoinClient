package gson;

import com.google.gson.*;
import logging.Logging;
import schemas.objects.OrderBook;
import schemas.objects.SequencedOrderBook;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Logger;

/**
 * TODO: Should use an actual adapter for when transitioning to a higher performance JSON parser
 */
public class GsonAdapters {

    private static final Logger LOGGER = Logging.handledLogger(GsonAdapters.class);

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter())
            .registerTypeAdapter(SequencedOrderBook.class, new SequencedOrderBookDeserializer())
            .registerTypeAdapter(OrderBook.class, new OrderBookDeserializer())
            .create();

    public static Gson getGson() {
        return GSON;
    }

    // Serializer methods
    static void addIfNonNull(JsonObject object, String property, String value) {
        if (value != null) {
            object.addProperty(property, value);
        }
    }

    static void addIfNonNull(JsonObject object, String property, Number value) {
        if (value != null) {
            object.addProperty(property, value);
        }
    }

    static void addIfNonNull(JsonObject object, String property, Boolean value) {
        if (value != null) {
            object.addProperty(property, value);
        }
    }

    static void addIfNonNull(JsonObject object, String property, LocalDateTime value) {
        if (value != null) {
            object.addProperty(property, value.toInstant(ZoneId.systemDefault().getRules().getOffset(value)).toEpochMilli());
        }
    }

    @FunctionalInterface
    public interface EnumValueGetter<T> {
        String value(T enumValue);
    }

    static <T> void addIfNonNull(JsonObject object, String property, T value, EnumValueGetter<T> valueGetter) {
        if (value != null) {
            object.addProperty(property, valueGetter.value(value));
        }
    }

    // Deserializer methods, jsonschema2pojo classes have a with"property" method
    static boolean hasProperty(JsonObject jsonObject, String property) {
        JsonElement element = jsonObject.get(property);
        return element != null && element != JsonNull.INSTANCE;
    }

    @FunctionalInterface
    public interface WithLong {
        void withLong(long longValue);
    }

    static void addIfNonNullLong(JsonObject jsonObject, WithLong withLongMethod, String property) {
        if (hasProperty(jsonObject, property)) {
            withLongMethod.withLong(jsonObject.get(property).getAsLong());
        }
    }

    @FunctionalInterface
    public interface WithString {
        void withString(String stringValue);
    }


    static void addIfNonNullString(JsonObject jsonObject, WithString withStringMethod, String property) {
        if (hasProperty(jsonObject, property)) {
            withStringMethod.withString(jsonObject.get(property).getAsString());
        }
    }

    @FunctionalInterface
    public interface WithEnum<T> {
        void withEnum(T enumValue);
    }

    @FunctionalInterface
    public interface WithFromValue<T> {
        T fromValue(String str);
    }

    static <T> void addIfNonNullEnum(JsonObject jsonObject, WithEnum<T> withEnumMethod, WithFromValue<T> withFromValueMethod, String property) {
        if (hasProperty(jsonObject, property)) {
            withEnumMethod.withEnum(withFromValueMethod.fromValue(jsonObject.get(property).getAsString()));
        }
    }

    @FunctionalInterface
    public interface WithDouble {
        void withDouble(double doubleValue);
    }

    static void addIfNonNullDouble(JsonObject jsonObject, WithDouble withDoubleMethod, String property) {
        if (hasProperty(jsonObject, property)) {
            withDoubleMethod.withDouble(jsonObject.get(property).getAsDouble());
        }
    }

    @FunctionalInterface
    public interface WithBoolean {
        void withBoolean(boolean booleanValue);
    }

    static void addIfNonNullBoolean(JsonObject jsonObject, WithBoolean withBooleanMethod, String property) {
        boolean value = false;
        if (hasProperty(jsonObject, property)) {
            value = jsonObject.get(property).getAsBoolean();
        }
        withBooleanMethod.withBoolean(value);
    }

    @FunctionalInterface
    public interface WithDateTime {
        void withDateTime(LocalDateTime localDateTime);
    }

    static void addIfNonNullDateTime(JsonObject jsonObject, WithDateTime withDateTimeMethod, String property) {
        if (hasProperty(jsonObject, property)) {
            withDateTimeMethod.withDateTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.get(property).getAsLong()), ZoneId.systemDefault()));
        }
    }

    static void addIfNonNullDateTimeNanoSeconds(JsonObject jsonObject, WithDateTime withDateTimeMethod, String property) {
        if (hasProperty(jsonObject, property)) {
            withDateTimeMethod.withDateTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.get(property).getAsLong() / 1000000), ZoneId.systemDefault()));
        }
    }

}
