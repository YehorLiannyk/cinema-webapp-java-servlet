package yehor.epam.actions.commands.signing;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.UserDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.User;
import yehor.epam.exceptions.RegisterException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.CookieService;
import yehor.epam.services.UserService;
import yehor.epam.services.VerifyService;
import yehor.epam.services.impl.CookieServiceImpl;
import yehor.epam.services.impl.ErrorServiceImpl;
import yehor.epam.services.impl.UserServiceImpl;
import yehor.epam.services.impl.VerifyServiceImpl;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.PassEncryptionManager;
import yehor.epam.utilities.RedirectManager;
import yehor.epam.utilities.constants.OtherConstants;

import java.io.IOException;

import static yehor.epam.utilities.constants.CommandConstants.COMMAND_VIEW_PROFILE_PAGE;
import static yehor.epam.utilities.constants.OtherConstants.USER_ID;
import static yehor.epam.utilities.constants.OtherConstants.USER_ROLE;

/**
 * Command for User registration
 */
public class RegisterCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(RegisterCommand.class);
    private static final String CLASS_NAME = RegisterCommand.class.getName();
    private final UserService userService;
    private final CookieService cookieService;
    private final VerifyService verifyService;

    public RegisterCommand() {
        userService = new UserServiceImpl();
        cookieService = new CookieServiceImpl();
        verifyService = new VerifyServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            verifyService.captchaValidation(request, response); //captcha validation
            saveUser(request, response);
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_PROFILE_PAGE));
        } catch (Exception e) {
            ErrorServiceImpl.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Call service method to save user
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServiceException
     * @throws IOException
     */
    private void saveUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        final User user = getUserFromRequest(request);
        final boolean inserted = userService.save(user);
        final int userId = userService.getMaxId();
        user.setId(userId);
        if (inserted) {
            prepareUserSessionAndCookie(request, response, user);
        } else {
            logger.warn("User wasn't saved");
            throw new RegisterException("Couldn't create User");
        }
    }

    /**
     * Set cookies and Session attributes (UserId and UserRole)
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param user     User
     */
    private void prepareUserSessionAndCookie(HttpServletRequest request, HttpServletResponse response, User user) {
        logger.info("User was inserted");
        final HttpSession session = request.getSession(true);
        session.setAttribute(USER_ID, user.getId());
        session.setAttribute(USER_ROLE, user.getUserRole());
        final String rememberMe = request.getParameter("rememberMe");
        cookieService.loginCookie(response, user, rememberMe);
    }

    /**
     * Get user from request
     *
     * @param request HttpServletRequest
     * @return User object
     */
    private User getUserFromRequest(HttpServletRequest request) {
        final String password = request.getParameter("password");
        PassEncryptionManager passManager = new PassEncryptionManager();
        String saltValue = passManager.getSaltValue(OtherConstants.SALT_LENGTH);
        String securePassword = passManager.generateSecurePassword(password, saltValue);
        return new User(
                request.getParameter("firstName"),
                request.getParameter("secondName"),
                request.getParameter("email"),
                securePassword,
                request.getParameter("phoneNumber"),
                fromCheckboxToBoolean(request.getParameter("notification")),
                saltValue
        );
    }

    /**
     * Get boolean from checkbox value
     *
     * @param checkboxValue checkboxValue
     * @return checkboxValue in boolean
     */
    private boolean fromCheckboxToBoolean(String checkboxValue) {
        return checkboxValue != null && checkboxValue.equals("on");
    }
}
