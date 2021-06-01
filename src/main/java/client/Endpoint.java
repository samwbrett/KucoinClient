package client;

import exceptions.RequestException;

@FunctionalInterface
public interface Endpoint<T> {

    KucoinClientV2Response<T> getResponse() throws RequestException;

}
