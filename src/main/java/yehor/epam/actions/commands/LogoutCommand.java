package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import yehor.epam.actions.ActionCommand;
import yehor.epam.entities.User;
import yehor.epam.services.CookieService;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;

import static yehor.epam.utilities.OtherConstants.USER_ID;
import static yehor.epam.utilities.OtherConstants.USER_ROLE;

public class LogoutCommand implements ActionCommand {
    private static final Logger logger = LoggerManager.getLogger(LogoutCommand.class);
    private String classSimpleName = LogoutCommand.class.getSimpleName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            final HttpSession session = request.getSession();
            final Integer userId = (Integer) session.getAttribute(USER_ID);
            final User.Role userRole = (User.Role) session.getAttribute(USER_ROLE);
            final String sessionId = session.getId();
            logger.info("User with id = " + userId.toString() + ", role = " + userRole.toString() + " logout");
            session.invalidate();
            logger.info("Session id = " + sessionId + " was invalided by user logout, userId = " + userId);
            CookieService cookieService = new CookieService();
            cookieService.eraseLoginCookie(request, response);
            logger.debug("Redirect to main page");
            response.sendRedirect("main");
        } catch (IOException e) {
            logger.error("Couldn't execute " + classSimpleName + " command", e);
        }
    }
}
