package client;

@FunctionalInterface
public interface WebsocketMessageHandler<T> {

    void onMessage(T messageResponse);

}
