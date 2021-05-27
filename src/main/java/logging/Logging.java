package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {

    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    private static final String LOG_LOCATION_PATTERN = "logs/log%g.xml";
    private static FileHandler LOG_HANDLER = null;
    static {
        try {
            LOG_HANDLER = new FileHandler(LOG_LOCATION_PATTERN, 100 * 1024 * 1024, 1000);
            LOGGER.addHandler(LOG_HANDLER);
            Runtime.getRuntime().addShutdownHook(new Thread(LOG_HANDLER::close));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File logging not enabled: " + e.getMessage(), e);
        }
    }

    public static <T> Logger handledLogger(Class<T> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        if (LOG_HANDLER != null) {
            logger.addHandler(LOG_HANDLER);
        }
        return logger;
    }

}
