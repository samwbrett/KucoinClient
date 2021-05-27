package exceptions;

public class ConfigurationException extends Exception {

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(Throwable throwable) {
        super(throwable);
    }
}
