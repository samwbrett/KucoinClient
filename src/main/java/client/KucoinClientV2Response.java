package client;

import gson.GsonAdapters;
import logging.Logging;

import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class KucoinClientV2Response<T> {

    private static final Logger LOGGER = Logging.handledLogger(KucoinClientV2Response.class);

    private final HttpResponse<String> response;
    private final T responseBody;
    private final long code;

    public KucoinClientV2Response(HttpResponse<String> response, Class<T> clazz, CodeGetter<T> codeGetter, T defaultObject) {
        this.response = response;
        if (getStatusCode() / 100 == 2) {
            this.responseBody = GsonAdapters.getGson().fromJson(response.body(), clazz);
            this.code = codeGetter.getCode(this.responseBody);
        } else {
            this.responseBody = defaultObject;
            this.code = 0;
        }
    }

    public int getStatusCode() {
        return response.statusCode();
    }

    public long getCode() {
        return code;
    }

    public HttpResponse<String> getHttpResponse() {
        return response;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public boolean isSuccess() {
        return getStatusCode() / 100 == 2 && getCode() / 100000 == 2;
    }

    public String toString() {
        return responseBody.toString();
    }

}
