package exceptions;

public class RequestException extends Exception {

    public RequestException(String message) {
        super(message);
    }

    public RequestException(Throwable throwable) {
        super(throwable);
    }

}
