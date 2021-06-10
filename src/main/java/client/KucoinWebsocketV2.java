package client;

import exceptions.RequestException;
import exceptions.WebsocketException;
import gson.GsonAdapters;
import logging.Logging;
import schemas.objects.InstanceServer;
import schemas.responses.WebsocketConnectResponse;
import schemas.websockets.BestOrdersMessage;
import schemas.websockets.OrderChangeMessage;
import schemas.websockets.SymbolTickerMessage;
import schemas.websockets.WebsocketMessage;

import java.io.Closeable;
import java.lang.ref.Cleaner;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class KucoinWebsocketV2<T> implements WebSocket.Listener, Closeable {

    private static final Logger LOGGER = Logging.handledLogger(KucoinWebsocketV2.class);
    private static final Cleaner CLEANER = Cleaner.create();

    private final Cleaner.Cleanable cleanable;

    private final boolean privateChannel;
    private final String topicId;
    private final String topic;
    private final Class<T> topicResponseClazz;
    private final WebsocketMessageHandler<T> handler;

    private volatile boolean isConnected = false;

    private KucoinWebsocketV2(KucoinClientV2 client, String topic, Class<T> topicResponseClazz, WebsocketMessageHandler<T> handler, boolean privateChannel) throws WebsocketException {
        try {
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

            this.topic = topic;
            this.privateChannel = privateChannel;
            this.topicResponseClazz = topicResponseClazz;
            this.handler = handler;
            this.topicId = UUID.randomUUID().toString();
            
            String connectId = UUID.randomUUID().toString();
            WebSocket webSocket = client.getHttpClient().newWebSocketBuilder().buildAsync(
                    new URI(server.getEndpoint() + "?token=" + connectResponse.getData().getToken() + "&connectId=" + connectId),
                    this).get();

            // Schedule pings
            TimerTask pingTask = new TimerTask() {
                @Override
                public void run() {
                    if (isConnected) {
                        ByteBuffer ping =
                                ByteBuffer.wrap(GsonAdapters.getGson().toJson(Map.of(
                                        "id", connectId,
                                        "type", "ping"))
                                        .getBytes(StandardCharsets.UTF_16));
                        webSocket.sendPing(ping);
                    }
                }
            };
            Timer timer = new Timer();
            timer.schedule(pingTask, 0L, server.getPingInterval());

            Runnable closer = () -> {
                pingTask.cancel();
                timer.cancel();
                webSocket.sendText(GsonAdapters.getGson().toJson(Map.of(
                        "id", topicId,
                        "type", "unsubscribe",
                        "topic", topic,
                        "privateChannel", privateChannel,
                        "response", true
                )), true);
                webSocket.sendClose(200, "close");
            };
            this.cleanable = CLEANER.register(this, closer);
            Runtime.getRuntime().addShutdownHook(new Thread(closer));
        } catch (RequestException | WebsocketException | URISyntaxException | InterruptedException | ExecutionException e) {
            LOGGER.severe("Unable to establish websocket connection: " + e.getMessage());
            throw new WebsocketException(e);
        }
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        webSocket.request(1);
        String dataString = data.toString();
        WebsocketMessage response = GsonAdapters.getGson().fromJson(dataString, WebsocketMessage.class);
        if (response.getType() == WebsocketMessage.Type.MESSAGE) {
            return handler.onMessage(GsonAdapters.getGson().fromJson(dataString, topicResponseClazz));
        }

        if (response.getType() == WebsocketMessage.Type.WELCOME) {
            // Subscribe to topic
            webSocket.sendText(GsonAdapters.getGson().toJson(Map.of(
                    "id", topicId,
                    "type", "subscribe",
                    "topic", topic,
                    "privateChannel", privateChannel,
                    "response", true
            )), true);
            this.isConnected = true;
        }
        return null;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        webSocket.request(1);
    }

    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        return null;
    }

    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        return null;
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        LOGGER.severe(error.getMessage());
    }

    public void close() {
        cleanable.clean();
    }

    public static <T> KucoinWebsocketV2<T> waitForConnect(KucoinWebsocketV2<T> websocket, int timeoutSeconds) throws InterruptedException, TimeoutException {
        long startTime = System.currentTimeMillis();
        while (!websocket.isConnected && System.currentTimeMillis() < startTime + 1000L * timeoutSeconds) {
            Thread.sleep(100);
        }
        if (websocket.isConnected) {
            return websocket;
        } else {
            throw new TimeoutException("Websocket " + websocket + " did not connect in " + timeoutSeconds + " seconds");
        }
    }

    public static KucoinWebsocketV2<BestOrdersMessage> bestFiveOrders(KucoinClientV2 client, String symbol, WebsocketMessageHandler<BestOrdersMessage> handler) throws WebsocketException {
        return new KucoinWebsocketV2<>(client, "/spotMarket/level2Depth5:" + symbol, BestOrdersMessage.class, handler, false);
    }

    public static KucoinWebsocketV2<BestOrdersMessage> bestFiftyOrders(KucoinClientV2 client, String symbol, WebsocketMessageHandler<BestOrdersMessage> handler) throws WebsocketException {
        return new KucoinWebsocketV2<>(client, "/spotMarket/level2Depth50:" + symbol, BestOrdersMessage.class, handler, false);
    }

    public static KucoinWebsocketV2<SymbolTickerMessage> symbolTicker(KucoinClientV2 client, String symbol, WebsocketMessageHandler<SymbolTickerMessage> handler) throws WebsocketException {
        return new KucoinWebsocketV2<>(client, "/market/ticker:" + symbol, SymbolTickerMessage.class, handler, false);
    }

    public static KucoinWebsocketV2<OrderChangeMessage> orderChange(KucoinClientV2 client, WebsocketMessageHandler<OrderChangeMessage> handler) throws WebsocketException {
        return new KucoinWebsocketV2<>(client, "/spotMarket/tradeOrders", OrderChangeMessage.class, handler, true);
    }

}
