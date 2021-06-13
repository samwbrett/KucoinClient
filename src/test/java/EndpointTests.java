import client.CoinCurrency;
import client.Endpoint;
import client.KucoinClientV2;
import client.KucoinClientV2Response;
import exceptions.RequestException;
import gson.GsonAdapters;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import schemas.objects.BulkOrder;
import schemas.parameters.ListOrdersParameters;
import schemas.requests.PostBulkOrdersRequest;
import schemas.requests.PostOrderRequest;
import schemas.responses.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class EndpointTests {

    protected static final String SCHEMA_LOCATION = "src/main/resources/schemas";
    protected static KucoinClientV2 CLIENT;

    private Schema createSchema(String schemaPath) throws IOException {

        Schema schema = null;
        File file = new File(SCHEMA_LOCATION + schemaPath);
        try (InputStream inputStream = new FileInputStream(file)) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            SchemaLoader schemaLoader = SchemaLoader.builder()
                    .schemaJson(rawSchema)
                    .resolutionScope(file.toURI().toString())
                    .build();
            schema = schemaLoader.load().build();
        }
        return schema;
    }

    @BeforeAll
    protected static void createClient() {
        CLIENT = new KucoinClientV2(true);
    }

    private <T> void testEndpoint(Endpoint<T> endpoint, String schemaLocation, Class<T> clazz) throws ValidationException, RequestException, IOException {
        try {
            KucoinClientV2Response<T> response = endpoint.getResponse();
            Assertions.assertTrue(response.isSuccess());

            System.out.println(response.getResponseBody() + "\n");
            Schema schema = createSchema(schemaLocation);
            schema.validate(new JSONObject(GsonAdapters.getGson().toJson(response.getResponseBody(), clazz)));
            //schema.validate(new JSONObject(response.getHttpResponse().body()));
        } catch (ValidationException e) {
            for (String message : e.getAllMessages()) {
                System.out.println(message);
            }
            throw e;
        }
    }

    @Test
    public void testTickerResponse() throws ValidationException, RequestException, IOException {
        testEndpoint(() -> CLIENT.getTicker(CoinCurrency.getSymbol(CoinCurrency.BTC, CoinCurrency.USDT)), "/responses/GetTickerResponse.json", GetTickerResponse.class);
    }

    @Test
    public void testRecentOrderResponse() throws ValidationException, RequestException, IOException {
        testEndpoint(() -> CLIENT.getRecentOrders(100), "/responses/GetRecentOrdersResponse.json", GetRecentOrdersResponse.class);
    }

    @Test
    public void testAccountResponse() throws ValidationException, RequestException, IOException {
        testEndpoint(() -> CLIENT.getAccounts(), "/responses/GetAccountsResponse.json", GetAccountsResponse.class);
    }

    @Test
    public void testSymbolListResponse() throws ValidationException, RequestException, IOException {
        testEndpoint(() -> CLIENT.getSymbolListResponse(), "/responses/GetSymbolListResponse.json", GetSymbolListResponse.class);
    }

    @Test
    public void testHistoryResponse() throws ValidationException, RequestException, IOException {
        testEndpoint(() -> CLIENT.getHistoryResponse(CoinCurrency.getSymbol(CoinCurrency.DIVI, CoinCurrency.USDT)), "/responses/GetHistoryResponse.json", GetHistoryResponse.class);
    }

    @Test
    public void testOrderBookResponse() throws ValidationException, RequestException, IOException {
        testEndpoint(() -> CLIENT.getOrderBook(CoinCurrency.getSymbol(CoinCurrency.BTC, CoinCurrency.USDT)), "/responses/GetOrderBookResponse.json", GetOrderBookResponse.class);
    }

    @Test
    public void testGetOrderResponse() throws ValidationException, RequestException, IOException {
        AtomicReference<String> orderIdRef = new AtomicReference<>(null);
        testEndpoint(() -> {
            ListOrdersParameters parameters = new ListOrdersParameters()
                    .withSymbol("BTC-USDT")
                    .withSide(ListOrdersParameters.Side.BUY)
                    .withStartAt(LocalDateTime.of(2021, 5, 31, 0, 0, 0));
            KucoinClientV2Response<ListOrdersResponse> response = CLIENT.listOrders(parameters);
            orderIdRef.set(response.getResponseBody().getData().getItems().get(0).getId());
            return response;
        }, "/responses/ListOrdersResponse.json", ListOrdersResponse.class);
        testEndpoint(() -> CLIENT.getOrder(orderIdRef.get()), "/responses/GetOrderResponse.json", GetOrderResponse.class);
    }

    @Test
    public void testPostOrderResponse() throws ValidationException, RequestException, IOException {
        testEndpoint(() -> CLIENT.postOrder(new PostOrderRequest()
                .withSize(0.01)
                .withPrice(0.01)
                .withClientOid(UUID.randomUUID().toString())
                .withSide(PostOrderRequest.Side.BUY)
                .withType(PostOrderRequest.Type.LIMIT)
                .withSymbol(CoinCurrency.getSymbol(CoinCurrency.KCS, CoinCurrency.USDT))
                .withPostOnly(true)), "/responses/PostOrderResponse.json", PostOrderResponse.class);
    }

    @Test
    public void testPostBulkOrdersResponse() throws ValidationException, RequestException, IOException {
        testEndpoint(() -> CLIENT.postBulkOrders(new PostBulkOrdersRequest()
                .withSymbol(CoinCurrency.getSymbol(CoinCurrency.KCS, CoinCurrency.USDT))
                .withOrderList(Arrays.asList(new BulkOrder()
                        .withSize(0.01)
                        .withPrice(0.01)
                        .withClientOid(UUID.randomUUID().toString())
                        .withSymbol(CoinCurrency.getSymbol(CoinCurrency.KCS, CoinCurrency.USDT))
                        .withSide(BulkOrder.Side.BUY)
                        .withType(BulkOrder.Type.LIMIT)))), "/responses/PostBulkOrdersResponse.json", PostBulkOrdersResponse.class);
    }

}
