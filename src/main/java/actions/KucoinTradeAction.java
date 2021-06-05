package actions;

import client.KucoinClientV2;
import schemas.objects.SymbolInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Avoid using a logger in this class except for major emergencies as the log writes to disk
 * Write the results from the run upon finishing
 * Also supports multiple attempts...avoid reusing the same instance of a trade action for different run sets
 * @param <T> Return type from the attempts
 */
public abstract class KucoinTradeAction<T> {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private final KucoinClientV2 client;
    private final String symbol;
    private final StringBuilder liveInfo; // Written when finished

    protected KucoinTradeAction(KucoinClientV2 client, String symbol) {
        this.client = client;
        this.symbol = symbol;
        this.liveInfo = new StringBuilder();
    }

    public KucoinClientV2 getClient() {
        return client;
    }

    public String getSymbol() {
        return symbol;
    }

    protected void addLiveInfo(String info) {
        liveInfo.append(DATE_FORMAT.format(new Date())).append("\n").append(info).append("\n");
    }

    public String getLiveActions() {
        return liveInfo.toString();
    }

    public void clearLiveActions() {
        liveInfo.setLength(0);
    }

    public void writeToLog(Logger logger) {
        logger.info(liveInfo.toString());
        liveInfo.setLength(0);
    }

    public abstract T attempt(SymbolInfo symbolInfo);

}
