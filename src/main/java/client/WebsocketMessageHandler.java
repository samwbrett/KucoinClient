package client;

import java.util.concurrent.CompletionStage;

@FunctionalInterface
public interface WebsocketMessageHandler<T> {

    CompletionStage<?> onMessage(T messageResponse);

}
