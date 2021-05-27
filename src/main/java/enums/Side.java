package enums;

public enum Side {
    buy,
    sell;

    public static Side getSide(String side) {
        if (buy.name().equals(side)) {
            return buy;
        } else if (sell.name().equals(side)) {
            return sell;
        } else {
            return null;
        }
    }
}
