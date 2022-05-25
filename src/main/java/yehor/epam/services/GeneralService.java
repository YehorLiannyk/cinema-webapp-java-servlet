package yehor.epam.services;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import yehor.epam.utilities.LoggerManager;

import java.time.LocalDate;

import static yehor.epam.utilities.OtherConstants.MAX_SESSION_TIME;
import static yehor.epam.utilities.OtherConstants.MIN_SESSION_TIME;

/**
 * Service for initialization commonly used params, such as minSessionTime, maxSessionTime, nowDate, etc
 */
public class GeneralService {
    private static final Logger logger = LoggerManager.getLogger(GeneralService.class);

    private GeneralService() {
    }

    /**
     * Initialization commonly used params
     *
     * @param request HttpServletRequest
     */
    public static void initParams(HttpServletRequest request) {
        request.getServletContext().setAttribute("minSessionTime", MIN_SESSION_TIME);
        request.getServletContext().setAttribute("maxSessionTime", MAX_SESSION_TIME);
        request.getSession(true).setAttribute("nowDate", LocalDate.now());
        logger.info("Params were initialized in " + GeneralService.class.getName());
    }
}
