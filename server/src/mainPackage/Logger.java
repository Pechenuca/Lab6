package mainPackage;

import com.sun.org.slf4j.internal.LoggerFactory;

public class Logger {
    private static Logger logger = LoggerFactory.getLogger();

    public static Logger getLogger() {
        return logger;
    }

    public static void info(String s) {
        info(s);
    }

    public static void error(String s) {
        error(s);
    }
}
