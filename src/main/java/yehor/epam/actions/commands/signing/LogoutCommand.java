package yehor.epam.actions.commands.signing;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.User;
import yehor.epam.services.CookieService;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;

import static yehor.epam.utilities.CommandConstants.COMMAND_MAIN_SERVLET;
import static yehor.epam.utilities.OtherConstants.USER_ID;
import static yehor.epam.utilities.OtherConstants.USER_ROLE;

/**
 * Command to logout User, end sessions and clean cookie
 */
public class LogoutCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(LogoutCommand.class);
    private static final String CLASS_NAME = LogoutCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            final HttpSession session = request.getSession();
            final int userId = Integer.parseInt(session.getAttribute(USER_ID).toString());
            final User.Role userRole = User.Role.valueOf(session.getAttribute(USER_ROLE).toString());
            final String sessionId = session.getId();
            logger.info("User with id = " + userId + ", role = " + userRole + " logout");
            session.invalidate();
            logger.info("Session id = " + sessionId + " was invalided by user logout, userId = " + userId);
            CookieService cookieService = new CookieService();
            cookieService.logoutCookie(request, response);
            response.sendRedirect(COMMAND_MAIN_SERVLET);
        } catch (IOException e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
