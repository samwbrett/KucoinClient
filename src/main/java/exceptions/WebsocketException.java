package exceptions;

public class WebsocketException extends Exception {

    public WebsocketException(String message) {
        super(message);
    }

    public WebsocketException(Throwable throwable) {
        super(throwable);
    }
}
