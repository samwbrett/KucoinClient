package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import schemas.objects.PriceSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GsonUtils {

    public static List<PriceSize> getPriceSizes(JsonArray array) {
        if (array == null) {
            return Collections.emptyList();
        }

        List<PriceSize> priceSizes = new ArrayList<>(array.size());
        for (JsonElement jsonElement : array) {
            PriceSize priceSize = new PriceSize();
            JsonArray priceSizeArray = jsonElement.getAsJsonArray();
            priceSize.withPrice(priceSizeArray.get(0).getAsDouble());
            priceSize.withSize(priceSizeArray.get(1).getAsDouble());
            priceSizes.add(priceSize);
        }
        return priceSizes;
    }

}
