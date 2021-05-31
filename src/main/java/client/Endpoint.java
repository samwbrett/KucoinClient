package client;

import exceptions.RequestException;

public interface Endpoint<T> {

    KucoinClientV2Response<T> getResponse() throws RequestException;

}
