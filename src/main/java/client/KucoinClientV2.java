package client;

import com.google.gson.Gson;
import enums.CoinCurrency;
import exceptions.ConfigurationException;
import exceptions.EncryptionException;
import exceptions.RequestException;
import logging.Logging;
import params.TradeParameters;
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
import java.util.logging.Logger;

public class KucoinClientV2 {

    private static final Logger LOGGER = Logging.handledLogger(Logger.getLogger(KucoinClientV2.class.getName()));

    private static final String CONFIG_LOCATION = "config.properties";

    private static final String SIGNATURE = "KC-API-SIGN";
    private static final String TIMESTAMP = "KC-API-TIMESTAMP";
    private static final String KEY = "KC-API-KEY";
    private static final String SECRET = "KC-API-SECRET";
    private static final String PASSPHRASE = "KC-API-PASSPHRASE";
    private static final String KEY_VERSION = "KC-API-KEY-VERSION";

    private static final String BASE_URL = "https://api.kucoin.com";

    private final Properties properties;
    private final HttpClient client;

    public KucoinClientV2() throws ConfigurationException {
        this.properties = new Properties();
        this.client = HttpClient.newHttpClient();
        try {
            this.properties.load(getClass().getClassLoader().getResourceAsStream(CONFIG_LOCATION));
        } catch (IOException e) {
            LOGGER.severe("Could not read config file: " + e.getMessage());
            throw new ConfigurationException(e);
        }
    }

    public KucoinClientV2Response<PostOrderResponse> placeOrder(TradeParameters tradeParameters) throws RequestException {
        return POST("/api/v1/orders", tradeParameters.asMap(), PostOrderResponse.class);
    }

    // Limit of 5 orders with this endpoint
    public KucoinClientV2Response<PostBulkOrdersResponse> placeMultiOrders(String symbol, List<TradeParameters> tradeParameters) throws RequestException {
        return POST("/api/v1/orders/multi",
                Map.of("symbol", symbol, "orderList", tradeParameters.subList(0, Math.min(5, tradeParameters.size()))),
                PostBulkOrdersResponse.class);
    }

    public KucoinClientV2Response<PostOrderResponse> getOrder(String orderId) throws RequestException {
        return GET("/api/v1/orders/" + orderId, PostOrderResponse.class);
    }

    public KucoinClientV2Response<GetRecentOrdersResponse> getRecentOrders(int pageSize) throws RequestException {
        return GET("/api/v1/limit/orders", Map.of("currentPage", 1, "pageSize", pageSize), GetRecentOrdersResponse.class);
    }

    public KucoinClientV2Response<GetSymbolListResponse> getSymbolListResponse() throws RequestException {
        return GET("/api/v1/symbols", GetSymbolListResponse.class);
    }

    public KucoinClientV2Response<GetHistoryResponse> getHistoryResponse(String symbol) throws RequestException {
        return GET("/api/v1/market/histories", Map.of("symbol", symbol), GetHistoryResponse.class);
    }

    public KucoinClientV2Response<GetTickerResponse> getTicker(String symbol) throws RequestException {
        return GET("/api/v1/market/orderbook/level1", Map.of("symbol", symbol), GetTickerResponse.class);
    }

    public KucoinClientV2Response<GetOrderBookResponse> getOrderBook(String symbol) throws RequestException {
        return GET("/api/v1/market/orderbook/level2_20", Map.of("symbol", symbol), GetOrderBookResponse.class);
    }

    public KucoinClientV2Response<GetAccountsResponse> getAccounts() throws RequestException {
        return GET("/api/v1/accounts", GetAccountsResponse.class);
    }

    public <T> KucoinClientV2Response<T> POST(String url, Map<String, Object> data, Class<T> clazz) throws RequestException {
        return makeRequest(url, RequestType.POST, data, clazz);
    }

    public <T> KucoinClientV2Response<T> GET(String url, Class<T> clazz) throws RequestException {
        return GET(url, null, clazz);
    }

    public <T> KucoinClientV2Response<T> GET(String url, Map<String, Object> parameters, Class<T> clazz) throws RequestException {
        return makeRequest(url + getParameterString(parameters), RequestType.GET, null, clazz);
    }

    private <T> KucoinClientV2Response<T> makeRequest(String url, RequestType requestType, Map<String, Object> data, Class<T> clazz) throws RequestException {
        try {
            HttpRequest.Builder builder =
                    HttpRequest
                            .newBuilder()
                            .uri(URI.create(BASE_URL + url))
                            .header("Content-Type", "application/json");
            for (Map.Entry<String, String> header : getHeaders(url, requestType, data).entrySet()) {
                builder = builder.header(header.getKey(), header.getValue());
            }
            if (requestType == RequestType.POST && data != null) {
                builder = builder.POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(data)));
            }
            HttpRequest request = builder.build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            KucoinClientV2Response<T> kucoinClientV2Response = new KucoinClientV2Response<>(response, clazz);
            return kucoinClientV2Response;
        } catch (EncryptionException | IOException | InterruptedException e) {
            throw new RequestException(e);
        }
    }

    private String getParameterString(Map<String, Object> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("?");
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return builder.substring(0, builder.length() - 1);
    }

    private Map<String, String> getHeaders(String url, RequestType requestType, Map<String, Object> data) throws EncryptionException {

        long currentMilli = System.currentTimeMillis();
        String toSign = currentMilli + requestType.name() + url + (data != null ? new Gson().toJson(data) : "");
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
