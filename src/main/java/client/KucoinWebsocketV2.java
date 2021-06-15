package client;

import exceptions.RequestException;
import exceptions.WebsocketException;
import gson.GsonAdapters;
import logging.Logging;
import schemas.objects.InstanceServer;
import schemas.responses.WebsocketConnectResponse;
import schemas.websockets.*;

import java.io.Closeable;
import java.lang.ref.Cleaner;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KucoinWebsocketV2<T> implements WebSocket.Listener, Closeable {

    private static final Logger LOGGER = Logging.handledLogger(KucoinWebsocketV2.class);
    private static final Cleaner CLEANER = Cleaner.create();
    private static final ScheduledThreadPoolExecutor PING_EXECUTOR = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 10);

    private static final int DEFAULT_CONNECT_TIMEOUT_SECONDS = 30;
    private static final int MAX_CONNECT_TRIES = 10;

    private final KucoinClientV2 client;
    private final boolean privateChannel;
    private final String topic;
    private final Class<T> topicResponseClazz;
    private final WebsocketMessageHandler<T> handler;
    private final int connectTimeoutSeconds;
    private CountDownLatch connectLatch;

    private final AtomicReference<Cleaner.Cleanable> cleanableReference = new AtomicReference<>(null);
    private final AtomicReference<String> topicIdReference = new AtomicReference<>(null);
    private final StringBuffer builder = new StringBuffer();
    private volatile boolean isConnected = false;

    private KucoinWebsocketV2(KucoinClientV2 client, String topic, Class<T> topicResponseClazz,
                              WebsocketMessageHandler<T> handler, boolean privateChannel, int connectTimeoutSeconds) throws WebsocketException {
        try {
            this.client = client;
            this.topic = topic;
            this.privateChannel = privateChannel;
            this.topicResponseClazz = topicResponseClazz;
            this.handler = handler;
            this.connectTimeoutSeconds = connectTimeoutSeconds;
            reconnect(0);
        } catch (WebsocketException e) {
            LOGGER.log(Level.SEVERE, "Unable to establish websocket connection: " + e.getMessage(), e);
            throw new WebsocketException(e);
        }
    }

    @Override
    public void close() {
        cleanableReference.get().clean();
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        webSocket.request(1);
        if (!last) {
            builder.append(data);
            return null;
        } else {
            builder.append(data);
        }
        String dataString = builder.toString();
        builder.setLength(0);

        WebsocketMessage response = GsonAdapters.getGson().fromJson(dataString, WebsocketMessage.class);
        if (response.getType() == WebsocketMessage.Type.MESSAGE) {
            handler.onMessage(GsonAdapters.getGson().fromJson(dataString, topicResponseClazz));
            return null;
        }

        if (response.getType() == WebsocketMessage.Type.WELCOME) {
            // Subscribe to topic
            LOGGER.info(webSocket + "\nWelcome received: " + data);
            webSocket.sendText(GsonAdapters.getGson().toJson(Map.of(
                    "id", topicIdReference.get(),
                    "type", "subscribe",
                    "topic", topic,
                    "privateChannel", privateChannel,
                    "response", true
            )), true);
            this.connectLatch.countDown();
            this.isConnected = true;
        }
        return null;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        webSocket.request(1);
    }

    @Override
    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        return null;
    }

    @Override
    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        return null;
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        if (isConnected) {
            LOGGER.warning("Server initiated termination...attempting restart: " + statusCode + " - " + reason + "\n" + webSocket);
            try {
                reconnect(0);
            } catch (WebsocketException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        LOGGER.log(Level.WARNING, error.getMessage(), error);
    }

    private void reconnect(int retry) throws WebsocketException {
        if (retry == MAX_CONNECT_TRIES) {
            throw new WebsocketException("Max " + MAX_CONNECT_TRIES + " reconnects attempted.");
        }
        try {
            if (cleanableReference.get() != null) {
                cleanableReference.get().clean();
            }
            connect();
        } catch (WebsocketException | URISyntaxException | ExecutionException | InterruptedException | RequestException e) {
            LOGGER.log(Level.WARNING, "Reconnect failed: " + e.getMessage(), e);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException interruptedException) {
                LOGGER.log(Level.WARNING, interruptedException.getMessage(), interruptedException);
            }
            reconnect(retry + 1);
        }
    }

    private boolean connect() throws WebsocketException, URISyntaxException, ExecutionException, InterruptedException, RequestException {

        this.connectLatch = new CountDownLatch(1);
        KucoinClientV2Response<WebsocketConnectResponse> websocketConnect = client.websocketConnect();
        if (!websocketConnect.isSuccess()) {
            throw new WebsocketException("Bad status code:\n\t" + websocketConnect.getHttpResponse().body());
        }
        WebsocketConnectResponse connectResponse = websocketConnect.getResponseBody();

        List<InstanceServer> instanceServers = connectResponse.getData().getInstanceServers();
        if (instanceServers.isEmpty()) {
            throw new WebsocketException("No instance servers:\n\t" + websocketConnect.getHttpResponse().body());
        }
        InstanceServer server = instanceServers.get(0);

        this.topicIdReference.set(UUID.randomUUID().toString());

        String connectId = UUID.randomUUID().toString();
        WebSocket webSocket = client.getHttpClient().newWebSocketBuilder().buildAsync(
                new URI(server.getEndpoint() + "?token=" + connectResponse.getData().getToken() + "&connectId=" + connectId),
                this).get();

        // Schedule pings
        ScheduledFuture<?> pingFuture = PING_EXECUTOR.scheduleAtFixedRate(() -> {
            if (isConnected) {
                ByteBuffer ping =
                        ByteBuffer.wrap(GsonAdapters.getGson().toJson(Map.of(
                                "id", connectId,
                                "type", "ping"))
                                .getBytes(StandardCharsets.UTF_16));
                webSocket.sendPing(ping);
            }
        }, 0L, server.getPingInterval(), TimeUnit.MILLISECONDS);

        Runnable closer = () -> {
            isConnected = false;
            pingFuture.cancel(true);
            webSocket.sendText(GsonAdapters.getGson().toJson(Map.of(
                    "id", topicIdReference.get(),
                    "type", "unsubscribe",
                    "topic", topic,
                    "privateChannel", privateChannel,
                    "response", true
            )), true);
            webSocket.sendClose(200, "close");
        };

        Thread closerThread = new Thread(closer);
        Runtime.getRuntime().addShutdownHook(closerThread);
        this.cleanableReference.set(CLEANER.register(this, () -> {
            closer.run();
            Runtime.getRuntime().removeShutdownHook(closerThread);
        }));

        return connectLatch.await(connectTimeoutSeconds, TimeUnit.SECONDS);
    }

    public static KucoinWebsocketV2<BestOrdersMessage> bestFiveOrders(KucoinClientV2 client, String symbol, WebsocketMessageHandler<BestOrdersMessage> handler) throws WebsocketException {
        return bestFiveOrders(client, symbol, handler, DEFAULT_CONNECT_TIMEOUT_SECONDS);
    }

    public static KucoinWebsocketV2<BestOrdersMessage> bestFiftyOrders(KucoinClientV2 client, String symbol, WebsocketMessageHandler<BestOrdersMessage> handler) throws WebsocketException {
        return bestFiftyOrders(client, symbol, handler, DEFAULT_CONNECT_TIMEOUT_SECONDS);
    }

    public static KucoinWebsocketV2<SymbolTickerMessage> symbolTicker(KucoinClientV2 client, String symbol, WebsocketMessageHandler<SymbolTickerMessage> handler) throws WebsocketException {
        return symbolTicker(client, symbol, handler, DEFAULT_CONNECT_TIMEOUT_SECONDS);
    }

    public static KucoinWebsocketV2<OrderChangeMessage> orderChange(KucoinClientV2 client, WebsocketMessageHandler<OrderChangeMessage> handler) throws WebsocketException {
        return orderChange(client, handler, DEFAULT_CONNECT_TIMEOUT_SECONDS);
    }

    public static KucoinWebsocketV2<MatchExecutionMessage> matchExecution(KucoinClientV2 client, String symbol, WebsocketMessageHandler<MatchExecutionMessage> handler) throws WebsocketException {
        return matchExecution(client, symbol, handler, DEFAULT_CONNECT_TIMEOUT_SECONDS);
    }

    public static KucoinWebsocketV2<BestOrdersMessage> bestFiveOrders(KucoinClientV2 client, String symbol, WebsocketMessageHandler<BestOrdersMessage> handler, int connectTimeoutSeconds) throws WebsocketException {
        return new KucoinWebsocketV2<>(client, "/spotMarket/level2Depth5:" + symbol, BestOrdersMessage.class, handler, false, connectTimeoutSeconds);
    }

    public static KucoinWebsocketV2<BestOrdersMessage> bestFiftyOrders(KucoinClientV2 client, String symbol, WebsocketMessageHandler<BestOrdersMessage> handler, int connectTimeoutSeconds) throws WebsocketException {
        return new KucoinWebsocketV2<>(client, "/spotMarket/level2Depth50:" + symbol, BestOrdersMessage.class, handler, false, connectTimeoutSeconds);
    }

    public static KucoinWebsocketV2<SymbolTickerMessage> symbolTicker(KucoinClientV2 client, String symbol, WebsocketMessageHandler<SymbolTickerMessage> handler, int connectTimeoutSeconds) throws WebsocketException {
        return new KucoinWebsocketV2<>(client, "/market/ticker:" + symbol, SymbolTickerMessage.class, handler, false, connectTimeoutSeconds);
    }

    public static KucoinWebsocketV2<OrderChangeMessage> orderChange(KucoinClientV2 client, WebsocketMessageHandler<OrderChangeMessage> handler, int connectTimeoutSeconds) throws WebsocketException {
        return new KucoinWebsocketV2<>(client, "/spotMarket/tradeOrders", OrderChangeMessage.class, handler, true, connectTimeoutSeconds);
    }

    public static KucoinWebsocketV2<MatchExecutionMessage> matchExecution(KucoinClientV2 client, String symbol, WebsocketMessageHandler<MatchExecutionMessage> handler, int connectTimeoutSeconds) throws WebsocketException {
        return new KucoinWebsocketV2<>(client, "/market/match:" + symbol, MatchExecutionMessage.class, handler, false, connectTimeoutSeconds);
    }

}
