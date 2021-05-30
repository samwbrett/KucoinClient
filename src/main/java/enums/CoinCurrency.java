package enums;

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
    VEED;

    public static String getSymbol(CoinCurrency baseCoin, CoinCurrency counterCoin) {
        return baseCoin + "-" + counterCoin;
    }
}
