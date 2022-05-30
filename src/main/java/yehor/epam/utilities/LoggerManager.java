package yehor.epam.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Class manager for Logger (Log4j)
 */
public class LoggerManager {
    private static final String PROPERTIES_FILE = "/log4j.properties";

    private LoggerManager() {
    }

    /**
     * Return Logger object
     *
     * @param clazz class which will use the logger
     * @return Logger object
     */
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
