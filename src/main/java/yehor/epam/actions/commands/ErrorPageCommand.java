package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.constants.JspPagePathConstants.ERROR_PAGE_PATH;

/**
 * Command forwarding to Error page from ErrorService
 */
public class ErrorPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(ErrorPageCommand.class);
    private static final String CLASS_NAME = ErrorPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            logger.debug("Forward to error page");
            request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            logger.error(CLASS_NAME + " got exception: ", e);
        }
    }
}
