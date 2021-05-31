import client.Endpoint;
import client.KucoinClientV2;
import client.KucoinClientV2Response;
import enums.CoinCurrency;
import exceptions.ConfigurationException;
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
import schemas.objects.BulkOrderOrder;
import schemas.requests.PostBulkOrdersRequest;
import schemas.requests.PostOrderRequest;
import schemas.responses.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

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
        try {
            CLIENT = new KucoinClientV2();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
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
        testEndpoint(() -> CLIENT.getHistoryResponse(CoinCurrency.getSymbol(CoinCurrency.BTC, CoinCurrency.USDT)), "/responses/GetHistoryResponse.json", GetHistoryResponse.class);
    }

    @Test
    public void testOrderBookResponse() throws ValidationException, RequestException, IOException {
        testEndpoint(() -> CLIENT.getOrderBook(CoinCurrency.getSymbol(CoinCurrency.BTC, CoinCurrency.USDT)), "/responses/GetOrderBookResponse.json", GetOrderBookResponse.class);
    }

    /*
    @Test
    public void testPostOrderResponse() throws ValidationException, RequestException, IOException {
        testEndpoint(() -> CLIENT.postOrder(new PostOrderRequest()
                .withFunds(0.1)
                .withClientOid(UUID.randomUUID().toString())
                .withSide("buy")
                .withType("market")
                .withSymbol(CoinCurrency.getSymbol(CoinCurrency.KCS, CoinCurrency.USDT))
                .withPostOnly(true)), "/responses/PostOrderResponse.json", PostOrderResponse.class);
    }

    @Test
    public void testPostBulkOrdersResponse() throws ValidationException, RequestException, IOException {
        testEndpoint(() -> CLIENT.postBulkOrders(new PostBulkOrdersRequest()
                .withSymbol(CoinCurrency.getSymbol(CoinCurrency.KCS, CoinCurrency.USDT))
                .withOrderList(Arrays.asList(new BulkOrderOrder()
                        .withSize(0.01)
                        .withPrice(6.61)
                        .withClientOid(UUID.randomUUID().toString())
                        .withSymbol(CoinCurrency.getSymbol(CoinCurrency.KCS, CoinCurrency.USDT))
                        .withSide("buy")
                        .withType("limit")))), "/responses/PostBulkOrdersResponse.json", PostBulkOrdersResponse.class);
    }
     */


}
