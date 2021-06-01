package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

    public static double toPrecision(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.FLOOR);
        return bd.doubleValue();
    }

}
