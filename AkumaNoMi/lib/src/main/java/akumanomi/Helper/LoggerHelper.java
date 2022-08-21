package akumanomi.Helper;

import java.util.logging.Logger;

import java.util.logging.Level;

import akumanomi.AkumaNoMi;

public class LoggerHelper {
    public static Logger logger = AkumaNoMi.getPlugin(AkumaNoMi.class).getLogger();
    private static final String PREFIX = "[AkumaNoMi]";

    public static void info(String message) {
        logger.log(Level.INFO, String.format("%s %s", PREFIX, message));
    }

    public static void error(String message) {
        logger.severe(String.format("%s %s", PREFIX, message));
    }

    public static void warning(String message) {
        logger.warning(String.format("%s %s", PREFIX, message));
    }
}
