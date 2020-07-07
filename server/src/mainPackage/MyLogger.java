package mainPackage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger("my logger");

    public static Logger getLogger() {
        return logger;
    }

    public static void info(String s) {
        logger.info(s);
    }

    public static void error(String s) {
        logger.error(s);
    }
}
