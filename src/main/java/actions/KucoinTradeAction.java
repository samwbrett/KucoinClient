package actions;

import client.KucoinClientV2;
import schemas.objects.SymbolInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
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
    private final StringBuilder liveInfo; // Write when finished
    private final ReadWriteLock liveInfoLock = new ReentrantReadWriteLock(true);

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
        liveInfoLock.writeLock().lock();
        try {
            liveInfo.append(DATE_FORMAT.format(new Date())).append("\n").append(info).append("\n");
        } finally {
            liveInfoLock.writeLock().unlock();
        }
    }

    public String getLiveActions() {
        liveInfoLock.readLock().lock();
        try {
            return liveInfo.toString();
        } finally {
            liveInfoLock.readLock().unlock();
        }
    }

    public void clearLiveActions() {
        liveInfoLock.writeLock().lock();
        try {
            liveInfo.setLength(0);
        } finally {
            liveInfoLock.writeLock().unlock();
        }
    }

    public void writeToLog(Logger logger) {
        logger.info(getLiveActions());
        clearLiveActions();
    }

    public abstract T attempt(SymbolInfo symbolInfo);

}
