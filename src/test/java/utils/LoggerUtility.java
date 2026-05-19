package utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class LoggerUtility {
    private static final Logger logger =
            LogManager.getLogger(LoggerUtility.class);
    public static void info(String message) {
        logger.info(message);
    }
    public static void pass(String message) {
        logger.info("[PASS] " + message);
    }
    public static void fail(String message) {
        logger.error("[FAIL] " + message);
    }
    public static void warn(String message) {
        logger.warn("[WARN] " + message);
    }
    public static void debug(String message) {
        logger.debug(message);
    }
}
