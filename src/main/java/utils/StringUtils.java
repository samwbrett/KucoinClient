package utils;

public class StringUtils {

    public static String capitalizeFirst(String str) {
        if (str.isEmpty()) {
            return str;
        } else if (str.length() == 1) {
            return str.toUpperCase();
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

}
