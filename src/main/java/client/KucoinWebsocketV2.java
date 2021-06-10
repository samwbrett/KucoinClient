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
import java.util.logging.Logger;

public class KucoinWebsocketV2<T> implements WebSocket.Listener, Closeable {

    private static final Logger LOGGER = Logging.handledLogger(KucoinWebsocketV2.class);
    private static final Cleaner CLEANER = Cleaner.create();

    private final Cleaner.Cleanable cleanable;

    private final InstanceServer server;
    private final boolean privateChannel;
    private final String connectId;
    private final String topicId;
    private final String topic;
    private final Class<T> topicResponseClazz;
    private final WebsocketMessageHandler<T> handler;

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

            this.topic = topic;
            this.privateChannel = privateChannel;
            this.topicResponseClazz = topicResponseClazz;
            this.handler = handler;
            this.server = instanceServers.get(0);
            this.connectId = UUID.randomUUID().toString();
            this.topicId = UUID.randomUUID().toString();
            WebSocket webSocket = client.getHttpClient().newWebSocketBuilder().buildAsync(
                    new URI(server.getEndpoint() + "?token=" + connectResponse.getData().getToken() + "&connectId=" + connectId),
                    this).get();

            this.cleanable = CLEANER.register(this, () -> {
                webSocket.sendText(GsonAdapters.getGson().toJson(Map.of(
                        "id", topicId,
                        "type", "unsubscribe",
                        "topic", topic,
                        "privateChannel", privateChannel,
                        "response", true
                )), true);
                webSocket.sendClose(200, "close");
            });
        } catch (RequestException | WebsocketException | URISyntaxException | InterruptedException | ExecutionException e) {
            LOGGER.severe("Unable to establish websocket connection: " + e.getMessage());
            throw new WebsocketException(e);
        }
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        LOGGER.info(data.toString());
        webSocket.request(1);
        String dataString = data.toString();
        WebsocketMessage response = GsonAdapters.getGson().fromJson(dataString, WebsocketMessage.class);
        if (response.getType() == WebsocketMessage.Type.MESSAGE) {
            return handler.onMessage(GsonAdapters.getGson().fromJson(dataString, topicResponseClazz));
        }

        if (response.getType() == WebsocketMessage.Type.WELCOME) {
            // Schedule pings
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ByteBuffer ping =
                            ByteBuffer.wrap(GsonAdapters.getGson().toJson(Map.of(
                                    "id", connectId,
                                    "type", "ping"))
                                    .getBytes(StandardCharsets.UTF_16));
                    LOGGER.info("Ping to server: " + ping.toString());
                    webSocket.sendPing(ping);
                }
            }, 0L, server.getPingInterval());

            // Subscribe to topic
            webSocket.sendText(GsonAdapters.getGson().toJson(Map.of(
                    "id", topicId,
                    "type", "subscribe",
                    "topic", topic,
                    "privateChannel", privateChannel,
                    "response", true
            )), true);
        }
        return null;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        LOGGER.info(webSocket.toString());
        webSocket.request(1);
    }

    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        LOGGER.info(message.toString());
        webSocket.request(1);
        return null;
    }

    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        LOGGER.info(message.toString());
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
