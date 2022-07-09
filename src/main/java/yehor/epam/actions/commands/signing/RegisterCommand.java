package yehor.epam.actions.commands.signing;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.User;
import yehor.epam.exceptions.RegisterException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.CookieService;
import yehor.epam.services.UserService;
import yehor.epam.services.VerifyService;
import yehor.epam.services.impl.CookieServiceImpl;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.impl.UserServiceImpl;
import yehor.epam.services.impl.VerifyServiceImpl;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.PassEncryptionManager;
import yehor.epam.utilities.RedirectManager;
import yehor.epam.utilities.constants.OtherConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.CommandConstants.COMMAND_VIEW_PROFILE_PAGE;
import static yehor.epam.utilities.constants.JspPagePathConstants.REGISTER_PAGE_PATH;
import static yehor.epam.utilities.constants.OtherConstants.*;

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
            //captcha validation
            verifyService.captchaValidation(request, response);

            final Map<String, String> userParamMap = getUserParamMap(request);
            final List<String> errorList = userService.getUserValidErrorList(userParamMap);
            if (errorList.isEmpty()) {
                final User user = getEncryptedUser(userParamMap);
                saveUser(request, response, user);
                response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_PROFILE_PAGE));
            } else {
                forwardWithErrors(request, response, errorList);
            }
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Forward to add film page with error list
     *
     * @param request   {@link HttpServletRequest}
     * @param response  {@link HttpServletResponse}
     * @param errorList received error List from validation service
     */
    private void forwardWithErrors(HttpServletRequest request, HttpServletResponse response, List<String> errorList) throws ServletException, IOException {
        VALID_ERROR_USER_PARAM_LIST.stream()
                .filter(error -> request.getAttribute(error) != null)
                .forEach(error -> request.setAttribute(error, false));
        errorList.forEach(error -> request.setAttribute(error, true));
        request.getRequestDispatcher(REGISTER_PAGE_PATH).forward(request, response);
    }

    /**
     * Call service method to save user
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServiceException
     */
    private void saveUser(HttpServletRequest request, HttpServletResponse response, User user) throws ServiceException {
        final boolean inserted = userService.save(user);
        final int userId = userService.getMaxId();
        user.setId(userId);
        if (inserted) {
            prepareUserSessionAndCookie(request, response, user);
        } else {
            logger.warn("User wasn't saved");
            throw new RegisterException("Couldn't create user, perhaps such user already exists");
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
     * Get Map of needed parameters for user creation
     *
     * @param request {@link HttpServletRequest}
     * @return map of needed parameters for user creation
     */
    private Map<String, String> getUserParamMap(HttpServletRequest request) {
        Map<String, String> userParamMap = new HashMap<>();
        userParamMap.put(F_NAME_PARAM, request.getParameter(F_NAME_PARAM));
        userParamMap.put(L_NAME_PARAM, request.getParameter(L_NAME_PARAM));
        userParamMap.put(EMAIL_PARAM, request.getParameter(EMAIL_PARAM));
        userParamMap.put(PASS_PARAM, request.getParameter(PASS_PARAM));
        userParamMap.put(PASS_CONFIRM_PARAM, request.getParameter(PASS_CONFIRM_PARAM));
        userParamMap.put(PHONE_PARAM, request.getParameter(PHONE_PARAM));
        userParamMap.put(NOTIFICATION_PARAM, request.getParameter(NOTIFICATION_PARAM));
        return userParamMap;
    }

    /**
     * Get User object from params Map with encrypted password
     *
     * @param userParamMap parameter map
     * @return User object
     */
    private User getEncryptedUser(Map<String, String> userParamMap) {
        PassEncryptionManager passManager = new PassEncryptionManager();
        String saltValue = passManager.getSaltValue(OtherConstants.SALT_LENGTH);
        String securePassword = passManager.generateSecurePassword(userParamMap.get(PASS_PARAM), saltValue);
        return new User(
                userParamMap.get(F_NAME_PARAM),
                userParamMap.get(L_NAME_PARAM),
                userParamMap.get(EMAIL_PARAM),
                securePassword,
                userParamMap.get(PHONE_PARAM),
                fromCheckboxToBoolean(userParamMap.get(NOTIFICATION_PARAM)),
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
