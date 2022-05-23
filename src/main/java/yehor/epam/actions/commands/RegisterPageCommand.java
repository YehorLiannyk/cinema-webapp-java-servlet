package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.ActionCommand;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.JspPagePathConstants.REGISTER_PAGE_PATH;

public class RegisterPageCommand implements ActionCommand {
    private static final Logger logger = LoggerManager.getLogger(RegisterPageCommand.class);
    private final String classSimpleName = RegisterPageCommand.class.getSimpleName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.debug("Forward to register page");
            request.getRequestDispatcher(REGISTER_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            logger.error("Couldn't execute " + classSimpleName + " command", e);
        }
    }
}
