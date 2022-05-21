package yehor.epam.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerManager {
    private static final String PROPERTIES_FILE = "/log4j.properties";

    private LoggerManager() {
    }

    public static Logger getLogger(Class clazz) {
        final Logger logger = Logger.getLogger(clazz);
        try {
            PropertyConfigurator.configure(LoggerManager.class.getResourceAsStream(PROPERTIES_FILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logger;
    }
}
