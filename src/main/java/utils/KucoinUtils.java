package utils;

import schemas.objects.History;
import schemas.objects.PriceSize;

import java.util.List;

public class KucoinUtils {

    public static double getHistoryAverage(List<History> histories) {
        double priceSize = 0;
        double size = 0;
        for (History hist : histories) {
            priceSize += hist.getSize() * hist.getPrice();
            size += hist.getSize();
        }
        return size != 0 ? priceSize / size : 0;
    }

    public static double getPriceSizeAverage(List<PriceSize> priceSizes) {
        double priceSize = 0;
        double size = 0;
        for (PriceSize ps : priceSizes) {
            priceSize += ps.getSize() * ps.getPrice();
            size += ps.getSize();
        }
        return size != 0 ? priceSize / size : 0;
    }

}
