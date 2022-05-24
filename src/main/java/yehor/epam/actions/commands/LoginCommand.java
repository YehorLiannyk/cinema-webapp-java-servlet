package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.UserDAO;
import yehor.epam.dao.exception.AuthException;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.User;
import yehor.epam.services.CookieService;
import yehor.epam.utilities.InnerRedirectManager;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;

import static yehor.epam.utilities.CommandConstants.*;
import static yehor.epam.utilities.OtherConstants.*;

public class LoginCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(LoginCommand.class);
    private String classSimpleName = LoginCommand.class.getSimpleName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + classSimpleName + " execute command");
            final String login = request.getParameter("login");
            final String password = request.getParameter("password");
            final UserDAO userDao = factory.getUserDao();
            userAuth(request, response, login, password, userDao);
        } catch (Exception e) {
            logger.error("Couldn't execute " + classSimpleName + " command", e);
        }
    }

    private void userAuth(HttpServletRequest request, HttpServletResponse response, String login, String password, UserDAO userDao) throws IOException {
        try {
            final User user = userDao.getUser(login, password);
            HttpSession session = request.getSession();
            session.setAttribute(USER_ID, user.getId());
            session.setAttribute(USER_ROLE, user.getUserRole());
            logger.info("User with id: " + user.getId() + ", role = " + user.getUserRole().toString() + " login");
            CookieService cookieService = new CookieService();
            cookieService.loginCookie(response, user);
            response.sendRedirect(InnerRedirectManager.getRedirectLocation(COMMAND_VIEW_PROFILE_PAGE));
        } catch (AuthException e) {
            logger.warn("Couldn't find user with login: " + request.getParameter("login"), e);
            request.setAttribute(REQUEST_PARAM_ERROR_MESSAGE, e.getMessage());
            logger.debug("Forward to errorPage from " + classSimpleName);
            new ErrorPageCommand().execute(request, response);
        }
    }
}
