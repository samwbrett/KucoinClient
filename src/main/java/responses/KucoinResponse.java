package responses;

import com.google.gson.Gson;
import exceptions.RequestException;

import java.net.http.HttpResponse;
import java.util.Map;

public class KucoinResponse {

    private final Map<String, String> responseMap;
    private final int statusCode;
    private final int code;

    public KucoinResponse(HttpResponse<String> response) {
        this.responseMap = new Gson().fromJson(response.body(), Map.class);
        this.statusCode = response.statusCode();
        this.code = Integer.parseInt(this.responseMap.get("code"));
    }

    public Map<String, String> getResponseMap() {
        return responseMap;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return statusCode / 100 == 2 && code / 100000 == 2;
    }

    public String toString() {
        return responseMap.toString();
    }

}
