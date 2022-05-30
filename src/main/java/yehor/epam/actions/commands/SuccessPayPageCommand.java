package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.JspPagePathConstants.SUCCESS_PAID_PAGE_PATH;

/**
 * Forward to SUCCESS_PAID_PAGE_PATH when payment is successful
 */
public class SuccessPayPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(SuccessPayPageCommand.class);
    private static final String CLASS_NAME = SuccessPayPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("Entry to" + CLASS_NAME);
            logger.debug("Forward to page of successful payment");
            request.getRequestDispatcher(SUCCESS_PAID_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
