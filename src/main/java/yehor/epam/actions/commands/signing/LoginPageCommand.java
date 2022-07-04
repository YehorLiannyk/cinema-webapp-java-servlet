package yehor.epam.actions.commands.signing;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.constants.JspPagePathConstants.LOGIN_PAGE_PATH;

/**
 * Command for forwarding to login page
 */
public class LoginPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(LoginPageCommand.class);
    private static final String CLASS_NAME = LoginPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            logger.debug("Forward to login page");
            request.getRequestDispatcher(LOGIN_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
