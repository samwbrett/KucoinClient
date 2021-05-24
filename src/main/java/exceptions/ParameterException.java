package exceptions;

public class ParameterException extends Exception {

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(Throwable throwable) {
        super(throwable);
    }

}
