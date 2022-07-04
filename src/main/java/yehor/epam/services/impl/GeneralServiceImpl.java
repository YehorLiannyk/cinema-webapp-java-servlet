package yehor.epam.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import yehor.epam.services.GeneralService;
import yehor.epam.utilities.LoggerManager;

import java.time.LocalDate;

import static yehor.epam.utilities.constants.OtherConstants.MAX_SESSION_TIME;
import static yehor.epam.utilities.constants.OtherConstants.MIN_SESSION_TIME;

/**
 * Service for initialization commonly used params, such as minSessionTime, maxSessionTime, nowDate, etc
 */
public class GeneralServiceImpl implements GeneralService {
    private static final Logger logger = LoggerManager.getLogger(GeneralServiceImpl.class);

    @Override
    public void initParams(HttpServletRequest request) {
        request.getServletContext().setAttribute("minSessionTime", MIN_SESSION_TIME);
        request.getServletContext().setAttribute("maxSessionTime", MAX_SESSION_TIME);
        request.getSession(true).setAttribute("nowDate", LocalDate.now());
        logger.info("Params were initialized in " + GeneralServiceImpl.class.getName());
    }
}
