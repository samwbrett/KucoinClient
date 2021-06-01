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

    public static String stripLeadingFollowingQuotes(String value) {
        if (value.length() > 2 && value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') {
            return value.substring(1, value.length() - 1);
        } else if (value.length() > 1 && value.charAt(0) == '"') {
            return value.substring(1);
        } else if (value.length() > 1 && value.charAt(value.length() - 1) == '"') {
            return value.substring(0, value.length() - 1);
        } else {
            return value;
        }
    }

}
