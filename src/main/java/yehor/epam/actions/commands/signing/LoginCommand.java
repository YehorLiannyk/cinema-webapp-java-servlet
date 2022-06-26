package yehor.epam.actions.commands.signing;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.UserDAO;
import yehor.epam.exceptions.AuthException;
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

/**
 * User login class, check if user exist and verify login and password
 */
public class LoginCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(LoginCommand.class);
    private static final String CLASS_NAME = LoginCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + CLASS_NAME + " execute command");
            final String login = request.getParameter("login");
            final String password = request.getParameter("password");
            final String rememberMe = request.getParameter("rememberMe");
            final UserDAO userDao = factory.getUserDao();
            userAuth(request, response, login, password, userDao, rememberMe);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Authorization of User and redirect
     *
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @param login      User's login
     * @param password   User's non encrypted password
     * @param userDao    UserDAO
     * @param rememberMe checkbox value Remember me
     * @throws IOException
     */
    private void userAuth(HttpServletRequest request, HttpServletResponse response, String login,
                          String password, UserDAO userDao, String rememberMe) throws IOException {
        try {
            final Map<String, String> saltAndPass = userDao.getSaltAndPassByLogin(login);
            final String saltValue = saltAndPass.get("salt");
            final String encryptedPass = saltAndPass.get("password");
            PassEncryptionManager passManager = new PassEncryptionManager();
            boolean isVerified = passManager.verifyUserPassword(password, encryptedPass, saltValue);
            if (!isVerified) throw new AuthException("Wrong password");
            final User user = prepareUser(request, response, login, userDao, rememberMe);
            redirectUser(response, user);
        } catch (AuthException e) {
            logger.warn("Troubles with user login: " + request.getParameter("login"), e);
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * If user is USER redirect to user profile page, if is ADMIN to main page
     *
     * @param response
     * @param user
     * @throws IOException
     */
    private void redirectUser(HttpServletResponse response, User user) throws IOException {
        if (user.getUserRole() == User.Role.USER)
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_PROFILE_PAGE));
        else
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_MAIN_PAGE));
    }

    /**
     * Prepare user's session and create cookie
     *
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @param login      User's login
     * @param userDao    UserDAO
     * @param rememberMe checkbox value Remember me
     * @return User object
     * @throws AuthException
     */
    private User prepareUser(HttpServletRequest request, HttpServletResponse response,
                             String login, UserDAO userDao, String rememberMe) throws AuthException {
        final User user = userDao.getUser(login);
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_ID, user.getId());
        session.setAttribute(USER_ROLE, user.getUserRole());
        logger.info("User with id: " + user.getId() + ", role = " + user.getUserRole().toString() + " login");
        CookieService cookieService = new CookieService();
        cookieService.loginCookie(response, user, rememberMe);
        return user;
    }
}
