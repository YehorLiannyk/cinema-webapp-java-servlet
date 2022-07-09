package yehor.epam.actions.commands.signing;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.User;
import yehor.epam.services.CookieService;
import yehor.epam.services.impl.CookieServiceImpl;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;

import static yehor.epam.utilities.constants.CommandConstants.COMMAND_MAIN_SERVLET;
import static yehor.epam.utilities.constants.OtherConstants.USER_ID;
import static yehor.epam.utilities.constants.OtherConstants.USER_ROLE;

/**
 * Command to log out User, end sessions and clean cookie
 */
public class LogoutCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(LogoutCommand.class);
    private static final String CLASS_NAME = LogoutCommand.class.getName();
    private final CookieService cookieService;

    public LogoutCommand() {
        cookieService = new CookieServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final HttpSession session = request.getSession();
            logUserLogout(session);
            session.invalidate();
            cookieService.logoutCookie(request, response);
            response.sendRedirect(COMMAND_MAIN_SERVLET);
        } catch (IOException e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    private void logUserLogout(HttpSession session) {
        final int userId = Integer.parseInt(session.getAttribute(USER_ID).toString());
        final User.Role userRole = User.Role.valueOf(session.getAttribute(USER_ROLE).toString());
        logger.info("User with id = " + userId + ", role = " + userRole + " logout");
    }
}
