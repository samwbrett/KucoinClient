package client;

import com.google.gson.Gson;
import exceptions.EncryptionException;
import exceptions.RequestException;
import params.TradeParameters;
import responses.KucoinResponse;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KucoinClientV2 {

    private enum RequestType {
        POST,
        GET
    }

    private static final String CONFIG_LOCATION = "config.properties";

    private static final String SIGNATURE = "KC-API-SIGN";
    private static final String TIMESTAMP = "KC-API-TIMESTAMP";
    private static final String KEY = "KC-API-KEY";
    private static final String SECRET = "KC-API-SECRET";
    private static final String PASSPHRASE = "KC-API-PASSPHRASE";
    private static final String KEY_VERSION = "KC-API-KEY-VERSION";

    private static final String BASE_URL = "https://api.kucoin.com";

    private final Properties properties;

    public KucoinClientV2() {
        this.properties = new Properties();
        try {
            this.properties.load(getClass().getClassLoader().getResourceAsStream(CONFIG_LOCATION));
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Could not read config file:" + e.getMessage());
            System.exit(1);
        }
    }

    public Future<KucoinResponse> placeOrderUntilExists(int timeout, TradeParameters tradeParameters) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<KucoinResponse> future = executorService.submit(() -> {
            long startTime = System.currentTimeMillis();
            KucoinResponse response = null;
            while (System.currentTimeMillis() < startTime + timeout * 1000L) {
                response = placeOrder(tradeParameters);
                if (response.isSuccess()) {
                    return response;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            String timeoutMessage = "Unable to place order " + tradeParameters + " in " + timeout + " seconds: " + response;
            Logger.getLogger(getClass().getName()).log(Level.WARNING, timeoutMessage);
            throw new TimeoutException(timeoutMessage);
        });
        executorService.shutdown();
        return future;
    }

    public KucoinResponse placeOrder(TradeParameters tradeParameters) throws RequestException {
        return POST("/api/v1/orders", tradeParameters.asMap());
    }

    public KucoinResponse getAccounts() throws RequestException {
        return GET("/api/v1/accounts");
    }

    public KucoinResponse POST(String url, Map<String, Object> data) throws RequestException {
        return makeRequest(url, RequestType.POST, data);
    }

    public KucoinResponse GET(String url) throws RequestException {
        return makeRequest(url, RequestType.GET, null);
    }

    public KucoinResponse GET(String url, int pageNumber, int pageSize) throws RequestException {
        return makeRequest(url, RequestType.GET, Map.of("currentPage", pageNumber, "pageSize", pageSize));
    }

    private KucoinResponse makeRequest(String url, RequestType requestType, Map<String, Object> data) throws RequestException {

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest.Builder builder =
                    HttpRequest
                            .newBuilder()
                            .uri(URI.create(BASE_URL + url))
                            .header("Content-Type", "application/json");
            for (Map.Entry<String, String> header : getHeaders(url, requestType, data).entrySet()) {
                builder = builder.header(header.getKey(), header.getValue());
            }
            if (data != null) {
                builder = builder.POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(data)));
            }
            HttpRequest request = builder.build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            KucoinResponse kucoinResponse = new KucoinResponse(response);
            return kucoinResponse;
        } catch (EncryptionException | IOException | InterruptedException e) {
            throw new RequestException(e);
        }
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
