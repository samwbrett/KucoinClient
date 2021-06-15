package actions;

import client.KucoinClientV2;
import logging.Logging;
import schemas.objects.SymbolInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Avoid using a logger in this class except for major emergencies as the log writes to disk
 * Write the results from the run upon finishing
 * Also supports multiple attempts...avoid reusing the same instance of a trade action for different run sets
 * @param <T> Return type from the attempts
 */
public abstract class KucoinTradeAction<T> {

    private static final Logger LOGGER = Logging.handledLogger(KucoinTradeAction.class);
    private static final int MAX_LIVE_INFO_LENGTH = 1000000;
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private final KucoinClientV2 client;
    private final String symbol;
    private final StringBuffer liveInfo; // Write when finished
    private final String uuid;

    protected KucoinTradeAction(KucoinClientV2 client, String symbol) {
        this.client = client;
        this.symbol = symbol;
        this.liveInfo = new StringBuffer();
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof KucoinTradeAction && this.uuid.equals(((KucoinTradeAction<?>) o).uuid);
    }

    @Override
    public int hashCode() {
        return this.uuid.hashCode();
    }

    public KucoinClientV2 getClient() {
        return client;
    }

    public String getSymbol() {
        return symbol;
    }

    protected void addLiveInfo(String info) {
        liveInfo.append(DATE_FORMAT.format(new Date())).append("\n").append(info).append("\n");
        if (liveInfo.length() >= MAX_LIVE_INFO_LENGTH) {
            writeToLog(LOGGER);
        }
    }

    public String getLiveActions() {
        return liveInfo.toString();
    }

    public void clearLiveActions() {
        liveInfo.setLength(0);
    }

    public void writeToLog(Logger logger) {
        logger.info(getLiveActions());
        clearLiveActions();
    }

    public abstract T attempt(SymbolInfo symbolInfo);

}
