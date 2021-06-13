package actions;

import client.KucoinClientV2;
import client.KucoinClientV2Response;
import logging.Logging;
import schemas.objects.SymbolInfo;
import schemas.responses.GetSymbolListResponse;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Runner for trade actions
 */
public class WithSymbolTradeAction {

    private static final Logger LOGGER = Logging.handledLogger(WithSymbolTradeAction.class);
    private static final int DEFAULT_PRE_SYMBOL_SECONDS = 60;

    private final KucoinClientV2 client;
    private final int timeoutSeconds;
    private final boolean repeating;
    private int preSymbolSeconds;

    private WithSymbolTradeAction(KucoinClientV2 client, int timeoutSeconds, boolean repeating) {
        this.client = client;
        this.timeoutSeconds = timeoutSeconds;
        this.repeating = repeating;
        this.preSymbolSeconds = DEFAULT_PRE_SYMBOL_SECONDS;
    }

    public static WithSymbolTradeAction getTimedAction(KucoinClientV2 client) {
        return new WithSymbolTradeAction(client, 1000000, false);
    }

    public static WithSymbolTradeAction getTimedRepeatingAction(KucoinClientV2 client, int timeoutSeconds) {
        return new WithSymbolTradeAction(client, timeoutSeconds, true);
    }

    public void setPreSymbolSeconds(int preSymbolSeconds) {
        this.preSymbolSeconds = preSymbolSeconds;
    }

    public <T> Future<T> executeAction(KucoinTradeAction<T> action) {
        return executeAction(action, new Date());
    }

    public <T> Future<T> executeAction(KucoinTradeAction<T> action, Date startDate) {

        AtomicReference<SymbolInfo> symbolInfoRef = new AtomicReference<>(null);
        Runnable getSymbolInfo = () -> {
            try {
                while (true) {
                    // Get available symbols
                    KucoinClientV2Response<GetSymbolListResponse> symbolListResponse = client.getSymbolListResponse();
                    if (!symbolListResponse.isSuccess()) {
                        continue;
                    }

                    for (SymbolInfo info : symbolListResponse.getResponseBody().getData()) {
                        if (action.getSymbol().equals(info.getSymbol())) {
                            symbolInfoRef.set(info);
                            LOGGER.info(info.toString());
                            return;
                        }
                    }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        };

        Date symbolStartTime = new Date(startDate.getTime() - preSymbolSeconds * 1000L);
        if (new Date().before(symbolStartTime)) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    getSymbolInfo.run();
                }
            }, symbolStartTime);
        }

        CompletableFuture<T> future = new CompletableFuture<>();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                SymbolInfo symbolInfo = symbolInfoRef.get();
                if (symbolInfo == null) {
                    getSymbolInfo.run();
                    symbolInfo = symbolInfoRef.get();
                }
                long startTime = System.currentTimeMillis();

                while (System.currentTimeMillis() < startTime + timeoutSeconds * 1000L) {

                    try {

                        T actionResult = action.attempt(symbolInfo);
                        if (actionResult != null || !repeating) {
                            future.complete(actionResult);
                            return;
                        }

                    } catch (RuntimeException e) {
                        LOGGER.log(Level.WARNING, e.getMessage(), e);
                    }
                }

                future.complete(null);
                String timeoutMessage = "Unable to execute action " + action + " in " + timeoutSeconds + " seconds";
                LOGGER.warning(timeoutMessage);
            }
        }, startDate);

        return future;
    }
}
