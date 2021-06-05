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
import java.util.logging.Logger;

/**
 * Runner for trade actions
 */
public class TimedRepeatingTradeAction {

    private static final Logger LOGGER = Logging.handledLogger(TimedRepeatingTradeAction.class);
    private static final int DEFAULT_PRE_SYMBOL_SECONDS = 60;

    private final KucoinClientV2 client;
    private final int timeoutSeconds;
    private int preSymbolSeconds;

    public TimedRepeatingTradeAction(KucoinClientV2 client, int timeoutSeconds) {
        this.client = client;
        this.timeoutSeconds = timeoutSeconds;
        this.preSymbolSeconds = DEFAULT_PRE_SYMBOL_SECONDS;
    }

    public void setPreSymbolSeconds(int preSymbolSeconds) {
        this.preSymbolSeconds = preSymbolSeconds;
    }

    public <T> Future<T> executeAction(KucoinTradeAction<T> action, Date startDate) {

        if (startDate.before(new Date())) {
            startDate = new Date(new Date().getTime() + preSymbolSeconds * 1000L);
        }
        final Date fullStartDate = startDate;

        AtomicReference<SymbolInfo> symbolInfoRef = new AtomicReference<>(null);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    while (System.currentTimeMillis() < fullStartDate.getTime()) {
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
                    }
                } catch (Exception e) {
                    LOGGER.severe(e.getMessage());
                }
            }
        }, new Date(startDate.getTime() - preSymbolSeconds * 1000L));

        CompletableFuture<T> future = new CompletableFuture<>();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                SymbolInfo symbolInfo = symbolInfoRef.get();
                long startTime = System.currentTimeMillis();

                while (System.currentTimeMillis() < startTime + timeoutSeconds * 1000L) {

                    try {

                        T actionResult = action.attempt(symbolInfo);
                        if (actionResult != null) {
                            future.complete(actionResult);
                            return;
                        }

                    } catch (RuntimeException e) {
                        LOGGER.warning(e.getMessage());
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
