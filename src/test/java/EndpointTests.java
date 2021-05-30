import client.KucoinClientV2;
import com.google.gson.Gson;
import exceptions.ConfigurationException;
import exceptions.RequestException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import client.KucoinClientV2Response;
import schemas.responses.GetSymbolListResponse;

import java.io.*;

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

    @Test
    public void testSymbolListResponse() throws ValidationException, RequestException, IOException {

        Schema schema = createSchema("/responses/GetSymbolListResponse.json");
        KucoinClientV2Response<GetSymbolListResponse> response = CLIENT.getSymbolListResponse();
        JSONObject jsonSubject = new JSONObject(new Gson().toJson(response.getResponseBody(), GetSymbolListResponse.class));
        schema.validate(jsonSubject);
    }
}
