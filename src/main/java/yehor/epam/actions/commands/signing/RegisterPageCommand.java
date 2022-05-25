package yehor.epam.actions.commands.signing;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.JspPagePathConstants.REGISTER_PAGE_PATH;

public class RegisterPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(RegisterPageCommand.class);
    private final String className = RegisterPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.debug("Forward to register page");
            request.getRequestDispatcher(REGISTER_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, className, e);
        }
    }
}
