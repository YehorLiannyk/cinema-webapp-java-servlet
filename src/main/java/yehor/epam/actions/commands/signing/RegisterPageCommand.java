package yehor.epam.actions.commands.signing;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.JspPagePathConstants.REGISTER_PAGE_PATH;

/**
 * Command for forwarding to registration page
 */
public class RegisterPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(RegisterPageCommand.class);
    private static final String CLASS_NAME = RegisterPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.debug("Forward to register page");
            request.getRequestDispatcher(REGISTER_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
