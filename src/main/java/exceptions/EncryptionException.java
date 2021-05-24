package exceptions;

public class EncryptionException extends Exception {

    public EncryptionException(String message) {
        super(message);
    }

    public EncryptionException(Throwable throwable) {
        super(throwable);
    }

}
