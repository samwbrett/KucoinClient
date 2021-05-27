package responses;

import com.google.gson.Gson;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class KucoinResponse {

    protected final Map<String, Object> responseMap;
    protected final int statusCode;
    protected final int code;

    public KucoinResponse(HttpResponse<String> response) {
        this.statusCode = response.statusCode();
        if (statusCode / 100 == 2) {
            this.responseMap = new Gson().fromJson(response.body(), Map.class);
            this.code = Integer.parseInt((String)this.responseMap.get("code"));
        } else {
            this.responseMap = new HashMap<>();
            this.code = 0;
        }
        this.responseMap.put("body", response.body());
    }

    protected KucoinResponse(KucoinResponse response) {
        this.statusCode = response.statusCode;
        this.responseMap = response.responseMap;
        this.code = response.code;
    }

    public Map<String, Object> getResponseMap() {
        return responseMap;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return (String) responseMap.get("body");
    }

    public boolean isSuccess() {
        return statusCode / 100 == 2 && code / 100000 == 2;
    }

    public String toString() {
        return responseMap.toString();
    }

}
