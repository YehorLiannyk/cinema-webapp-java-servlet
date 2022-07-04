package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.constants.JspPagePathConstants.SUCCESS_PAID_PAGE_PATH;

/**
 * Forward to SUCCESS_PAID_PAGE_PATH when payment is successful
 */
public class SuccessPayPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(SuccessPayPageCommand.class);
    private static final String CLASS_NAME = SuccessPayPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            logger.debug("Forward to page of successful payment");
            request.getRequestDispatcher(SUCCESS_PAID_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
