package yehor.epam.actions.commands.signing;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.User;
import yehor.epam.exceptions.AuthException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.CookieService;
import yehor.epam.services.UserService;
import yehor.epam.services.impl.CookieServiceImpl;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.impl.UserServiceImpl;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.RedirectManager;

import static yehor.epam.utilities.constants.CommandConstants.COMMAND_VIEW_MAIN_PAGE;
import static yehor.epam.utilities.constants.CommandConstants.COMMAND_VIEW_PROFILE_PAGE;
import static yehor.epam.utilities.constants.JspPagePathConstants.LOGIN_PAGE_PATH;
import static yehor.epam.utilities.constants.OtherConstants.USER_ID;
import static yehor.epam.utilities.constants.OtherConstants.USER_ROLE;

/**
 * User login class, check if user exist and verify login and password
 */
public class LoginCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(LoginCommand.class);
    private static final String CLASS_NAME = LoginCommand.class.getName();
    private final UserService userService;
    private final CookieService cookieService;

    public LoginCommand() {
        userService = new UserServiceImpl();
        cookieService = new CookieServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final String login = request.getParameter("login");
            final String emailError = userService.validateUserEmail(login);
            if (emailError.isBlank()) {
                final String password = request.getParameter("password");
                final String rememberMe = request.getParameter("rememberMe");
                userService.authenticateUser(login, password);
                final User user = userService.getUserByLogin(login);
                prepareUser(user, request, response, rememberMe);

                final String redirectPage = getRedirectPage(user.getUserRole());
                response.sendRedirect(RedirectManager.getRedirectLocation(redirectPage));
            } else {
                request.setAttribute(emailError, true);
                request.getRequestDispatcher(LOGIN_PAGE_PATH).forward(request, response);
            }
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * @param user       User
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @param rememberMe checkbox value Remember me
     */
    private void prepareUser(User user, HttpServletRequest request, HttpServletResponse response, String rememberMe) {
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_ID, user.getId());
        session.setAttribute(USER_ROLE, user.getUserRole());
        logger.info("User with id: " + user.getId() + ", role = " + user.getUserRole().toString() + " login");
        cookieService.loginCookie(response, user, rememberMe);
    }

    /**
     * If user is USER get profile page, if ADMIN - get main page
     *
     * @param userRole User role
     * @return page to redirect
     */
    private String getRedirectPage(User.Role userRole) {
        if (userRole == User.Role.USER)
            return COMMAND_VIEW_PROFILE_PAGE;
        return COMMAND_VIEW_MAIN_PAGE;
    }
}
