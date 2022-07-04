package yehor.epam.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.commands.ErrorPageCommand;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.constants.OtherConstants.REQUEST_PARAM_ERROR_MESSAGE;

/**
 * Class for handling error and then forward to ErrorPage
 */
public class ErrorService {
    private static final Logger logger = LoggerManager.getLogger(ErrorService.class);

    private ErrorService() {
    }

    /**
     * Method for handling error and then forward to ErrorPage
     *
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @param message   message for logging
     * @param className name of class which throw exception
     * @param e         object of Throwable class (object of error class)
     */
    public static void handleException(HttpServletRequest request, HttpServletResponse response, String message, String className, Throwable e) {
        logger.error(message + ". Class: " + className, e);
        if (!response.isCommitted()) {
            request.setAttribute(REQUEST_PARAM_ERROR_MESSAGE, e.getMessage());
            logger.debug("Call ErrorPageCommand().execute from " + ErrorService.class.getName());
            new ErrorPageCommand().execute(request, response);
        } else {
            logger.debug("Response is committed, so couldn't redirect to error page from " + ErrorService.class.getName());
        }
    }

    /**
     * Method for handling error and then forward to ErrorPage
     *
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @param className name of class which throw exception
     * @param e         object of Throwable class (object of error class)
     */
    public static void handleException(HttpServletRequest request, HttpServletResponse response, String className, Throwable e) {
        handleException(request, response, "Couldn't execute command", className, e);
    }

}
