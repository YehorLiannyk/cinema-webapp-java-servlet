package yehor.epam.actions.commands.signing;

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
import yehor.epam.services.ErrorService;
import yehor.epam.services.PassEncryptionManager;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.RedirectManager;

import java.io.IOException;
import java.util.Map;

import static yehor.epam.utilities.CommandConstants.COMMAND_VIEW_MAIN_PAGE;
import static yehor.epam.utilities.CommandConstants.COMMAND_VIEW_PROFILE_PAGE;
import static yehor.epam.utilities.OtherConstants.USER_ID;
import static yehor.epam.utilities.OtherConstants.USER_ROLE;

public class LoginCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(LoginCommand.class);
    private String className = LoginCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + className + " execute command");
            final String login = request.getParameter("login");
            final String password = request.getParameter("password");
            final UserDAO userDao = factory.getUserDao();
            userAuth(request, response, login, password, userDao);
        } catch (Exception e) {
            ErrorService.handleException(request, response, className, e);
        }
    }

    private void userAuth(HttpServletRequest request, HttpServletResponse response, String login, String password, UserDAO userDao) throws IOException {
        try {
            final Map<String, String> saltAndPass = userDao.getSaltAndPassByLogin(login);
            final String saltValue = saltAndPass.get("salt");
            final String encryptedPass = saltAndPass.get("password");
            PassEncryptionManager passManager = new PassEncryptionManager();
            boolean isVerified = passManager.verifyUserPassword(password, encryptedPass, saltValue);
            if (!isVerified) throw new AuthException("Wrong password");
            final User user = prepareUser(request, response, login, userDao);
            redirectUser(response, user);
        } catch (AuthException e) {
            logger.warn("Troubles with user login: " + request.getParameter("login"), e);
            ErrorService.handleException(request, response, className, e);
        }
    }

    private void redirectUser(HttpServletResponse response, User user) throws IOException {
        if (user.getUserRole() == User.Role.USER)
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_PROFILE_PAGE));
        else
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_MAIN_PAGE));
    }

    private User prepareUser(HttpServletRequest request, HttpServletResponse response, String login, UserDAO userDao) throws AuthException {
        final User user = userDao.getUser(login);
        HttpSession session = request.getSession();
        session.setAttribute(USER_ID, user.getId());
        session.setAttribute(USER_ROLE, user.getUserRole());
        logger.info("User with id: " + user.getId() + ", role = " + user.getUserRole().toString() + " login");
        CookieService cookieService = new CookieService();
        cookieService.loginCookie(response, user);
        return user;
    }
}
