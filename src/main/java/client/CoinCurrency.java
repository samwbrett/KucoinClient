package client;

public enum CoinCurrency {
    USD,
    USDT,
    KCS,
    BTC,
    ETH,
    ADA,
    DOGE,
    KONO,
    PRQ,
    PYR,
    FEAR,
    MAHA,
    ALBT,
    XCAD,
    GLCH,
    UNO,
    PROM,
    DERP,
    APL,
    ELON,
    VEED,
    DIVI,
    JUP,
    LPOOL,
    LSS,
    ABBC,
    KOK,
    POLS,
    ZCX,
    ROSN,
    DORA,
    GMEE,
    NORD,
    XAVA,
    SFUND;

    public static String getSymbol(CoinCurrency baseCoin, CoinCurrency quoteCoin) {
        return baseCoin + "-" + quoteCoin;
    }

    public static CoinCurrency fromValue(String currency) {
        for (CoinCurrency coinCurrency : CoinCurrency.values()) {
            if (coinCurrency.name().equals(currency)) {
                return coinCurrency;
            }
        }
        return null;
    }
}
