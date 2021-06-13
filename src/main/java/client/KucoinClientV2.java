package client;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import exceptions.ConfigurationException;
import exceptions.EncryptionException;
import exceptions.RequestException;
import gson.GsonAdapters;
import logging.Logging;
import schemas.parameters.ListOrdersParameters;
import schemas.requests.PostBulkOrdersRequest;
import schemas.requests.PostOrderRequest;
import schemas.responses.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KucoinClientV2 {

    private static final Logger LOGGER = Logging.handledLogger(KucoinClientV2.class);

    public static final String CONFIG_LOCATION = "config.properties";

    private static final String SIGNATURE = "KC-API-SIGN";
    private static final String TIMESTAMP = "KC-API-TIMESTAMP";
    private static final String KEY = "KC-API-KEY";
    private static final String SECRET = "KC-API-SECRET";
    private static final String PASSPHRASE = "KC-API-PASSPHRASE";
    private static final String KEY_VERSION = "KC-API-KEY-VERSION";

    private final Properties properties;
    private final HttpClient client;
    private final String baseUrl;

    public KucoinClientV2(boolean isProduction) {
        this.properties = new Properties();
        this.client = HttpClient.newHttpClient();
        this.baseUrl = isProduction ? "https://api.kucoin.com" : "https://openapi-sandbox.kucoin.com";
        try {
            this.properties.load(KucoinClientV2.class.getClassLoader().getResourceAsStream(CONFIG_LOCATION));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Could not read config file: " + e.getMessage(), e);
            throw new ConfigurationException(e);
        }
    }

    HttpClient getHttpClient() {
        return client;
    }

    KucoinClientV2Response<WebsocketConnectResponse> websocketConnect() throws RequestException {
        return POST("/api/v1/bullet-private", Collections.emptyMap(), WebsocketConnectResponse.class, WebsocketConnectResponse::getCode, new WebsocketConnectResponse());
    }

    public KucoinClientV2Response<PostOrderResponse> postOrder(PostOrderRequest request) throws RequestException {
        return POST("/api/v1/orders", request, PostOrderResponse.class, PostOrderResponse::getCode, new PostOrderResponse());
    }

    // Limit of 5 orders with this endpoint
    public KucoinClientV2Response<PostBulkOrdersResponse> postBulkOrders(PostBulkOrdersRequest request) throws RequestException {
        return POST("/api/v1/orders/multi", request, PostBulkOrdersResponse.class, PostBulkOrdersResponse::getCode, new PostBulkOrdersResponse());
    }

    public KucoinClientV2Response<GetOrderResponse> getOrder(String orderId) throws RequestException {
        return GET("/api/v1/orders/" + orderId, GetOrderResponse.class, GetOrderResponse::getCode, new GetOrderResponse());
    }

    public KucoinClientV2Response<ListOrdersResponse> listOrders(ListOrdersParameters parameters) throws RequestException {
        return GET("/api/v1/orders", parameters, ListOrdersResponse.class, ListOrdersResponse::getCode, new ListOrdersResponse());
    }

    public KucoinClientV2Response<GetRecentOrdersResponse> getRecentOrders(int pageSize) throws RequestException {
        return GET("/api/v1/limit/orders", Map.of("currentPage", 1, "pageSize", pageSize), GetRecentOrdersResponse.class, GetRecentOrdersResponse::getCode, new GetRecentOrdersResponse());
    }

    public KucoinClientV2Response<GetSymbolListResponse> getSymbolListResponse() throws RequestException {
        return GET("/api/v1/symbols", GetSymbolListResponse.class, GetSymbolListResponse::getCode, new GetSymbolListResponse());
    }

    public KucoinClientV2Response<GetHistoryResponse> getHistoryResponse(String symbol) throws RequestException {
        return GET("/api/v1/market/histories", Map.of("symbol", symbol), GetHistoryResponse.class, GetHistoryResponse::getCode, new GetHistoryResponse());
    }

    public KucoinClientV2Response<GetTickerResponse> getTicker(String symbol) throws RequestException {
        return GET("/api/v1/market/orderbook/level1", Map.of("symbol", symbol), GetTickerResponse.class, GetTickerResponse::getCode, new GetTickerResponse());
    }

    public KucoinClientV2Response<GetOrderBookResponse> getOrderBook(String symbol) throws RequestException {
        return GET("/api/v1/market/orderbook/level2_20", Map.of("symbol", symbol), GetOrderBookResponse.class, GetOrderBookResponse::getCode, new GetOrderBookResponse());
    }

    public KucoinClientV2Response<GetAccountsResponse> getAccounts() throws RequestException {
        return GET("/api/v1/accounts", GetAccountsResponse.class, GetAccountsResponse::getCode, new GetAccountsResponse());
    }

    private <T, R> KucoinClientV2Response<T> POST(String url, R data, Class<T> clazz, CodeGetter<T> codeGetter, T defaultObject) throws RequestException {
        return makeRequest(url, RequestType.POST, data, clazz, codeGetter, defaultObject);
    }

    private <T> KucoinClientV2Response<T> GET(String url, Class<T> clazz, CodeGetter<T> codeGetter, T defaultObject) throws RequestException {
        return GET(url, null, clazz, codeGetter, defaultObject);
    }

    private <T, P> KucoinClientV2Response<T> GET(String url, P parameters, Class<T> clazz, CodeGetter<T> codeGetter, T defaultObject) throws RequestException {
        return makeRequest(url + getParameterString(parameters), RequestType.GET, null, clazz, codeGetter, defaultObject);
    }

    private <T, R> KucoinClientV2Response<T> makeRequest(String url, RequestType requestType, R data, Class<T> clazz, CodeGetter<T> codeGetter, T defaultObject) throws RequestException {
        try {
            HttpRequest.Builder builder =
                    HttpRequest
                            .newBuilder()
                            .uri(URI.create(baseUrl + url))
                            .header("Content-Type", "application/json");
            for (Map.Entry<String, String> header : getHeaders(url, requestType, data).entrySet()) {
                if (header.getValue() != null) {
                    builder = builder.header(header.getKey(), header.getValue());
                }
            }
            if (requestType == RequestType.POST) {
                builder = builder.POST(HttpRequest.BodyPublishers.ofString(GsonAdapters.getGson().toJson(data)));
            }
            HttpRequest request = builder.build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            KucoinClientV2Response<T> kucoinClientV2Response = new KucoinClientV2Response<>(response, clazz, codeGetter, defaultObject);
            return kucoinClientV2Response;
        } catch (EncryptionException | IOException | InterruptedException e) {
            throw new RequestException(e);
        }
    }

    private <P> String getParameterString(P parameters) {
        if (parameters == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("?");
        JsonElement element = GsonAdapters.getGson().toJsonTree(parameters);
        for (String key : element.getAsJsonObject().keySet()) {
            JsonElement el = element.getAsJsonObject().get(key);
            if (el != null && el != JsonNull.INSTANCE) {
                builder.append(key).append("=").append(el.getAsString()).append("&");
            }
        }
        return builder.substring(0, builder.length() - 1);
    }

    private <R> Map<String, String> getHeaders(String url, RequestType requestType, R data) throws EncryptionException {

        long currentMilli = System.currentTimeMillis();
        String toSign = currentMilli + requestType.name() + url + (data != null ? GsonAdapters.getGson().toJson(data) : "");
        String encryptedToSign = getEncryptedMessage(toSign);
        String encryptedPassphrase = getEncryptedMessage(properties.getProperty(PASSPHRASE));

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(SIGNATURE, encryptedToSign);
        headerMap.put(TIMESTAMP, Long.toString(currentMilli));
        headerMap.put(KEY, properties.getProperty(KEY));
        headerMap.put(PASSPHRASE, encryptedPassphrase);
        headerMap.put(KEY_VERSION, "2");

        return headerMap;
    }

    private String getEncryptedMessage(String message) throws EncryptionException {

        try {
            String secretKey = properties.getProperty(SECRET);
            Mac encrypter = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            encrypter.init(secret_key);

            String hash = Base64.getEncoder().encodeToString(encrypter.doFinal(message.getBytes(StandardCharsets.UTF_8)));
            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new EncryptionException(e);
        }
    }

}
