package client;

import com.google.gson.Gson;
import logging.Logging;

import java.lang.reflect.InvocationTargetException;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class KucoinClientV2Response<T> {

    private static final Logger LOGGER = Logging.handledLogger(Logger.getLogger(KucoinClientV2Response.class.getName()));

    private final HttpResponse<String> response;
    private final T responseBody;
    private final int code;

    public KucoinClientV2Response(HttpResponse<String> response, Class<T> clazz) {
        this.response = response;
        if (getStatusCode() / 100 == 2) {
            this.responseBody = new Gson().fromJson(response.body(), clazz);
            int tempCode = 0;
            try {
                tempCode = ((Long)this.responseBody.getClass().getMethod("getCode").invoke(this.responseBody)).intValue();
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.severe("Response body does not have getCode method\n" + e.getMessage());
            }
            this.code = tempCode;
        } else {
            this.responseBody = null;
            this.code = 0;
        }
    }

    public int getStatusCode() {
        return response.statusCode();
    }

    public int getCode() {
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
