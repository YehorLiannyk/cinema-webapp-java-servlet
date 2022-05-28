package yehor.epam.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import yehor.epam.entities.User;
import yehor.epam.services.CookieService;
import yehor.epam.services.GeneralService;
import yehor.epam.utilities.RedirectManager;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static yehor.epam.utilities.CommandConstants.*;
import static yehor.epam.utilities.JspPagePathConstants.ERROR_PAGE_PATH;
import static yehor.epam.utilities.OtherConstants.*;

@WebFilter(urlPatterns = {"/*"}, filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    private static final Logger logger = LoggerManager.getLogger(SecurityFilter.class);
    private static final String CLASS_NAME = SecurityFilter.class.getName();
    private final List<String> guestAccessPath = new ArrayList<>();
    private final List<String> userAccessPath = new ArrayList<>();
    private final List<String> adminAccessPath = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Entry to filter: " + CLASS_NAME);

        initGuestAccess();
        initUserAccess();
        initAdminAccess();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        final HttpSession session = req.getSession();

        //
        getCookies(req, session);
        //
        GeneralService.initParams(req);

        String command = req.getParameter("command");
        logger.debug("Command from " + CLASS_NAME + " = " + req.getParameter("command"));
        logger.debug("Session role attribute from " + CLASS_NAME + " = " + session.getAttribute(USER_ROLE));

        if (session.getAttribute(USER_ROLE) == null || session.getAttribute(USER_ROLE).toString().equals(User.Role.GUEST.toString())) {
            logger.debug("Entry to GUEST's if section. Session.USER_ROLE = '" + session.getAttribute(USER_ROLE) + '\'');
            if (!guestAccessPath.contains(command)) {
                redirectToLoginPage(session, command, resp);
                return;
            }
        } else if (session.getAttribute(USER_ROLE).toString().equals(User.Role.USER.toString())) {
            logger.debug("Entry to USER's if section. Session.USER_ROLE = '" + session.getAttribute(USER_ROLE) + '\'');
            if (!userAccessPath.contains(command)) {
                forwardToErrorPage(session, command, req, resp);
                return;
            }
        } else if (session.getAttribute(USER_ROLE).toString().equals(User.Role.ADMIN.toString())) {
            logger.debug("Entry to ADMIN if section. Session.USER_ROLE = '" + session.getAttribute(USER_ROLE) + '\'');
            if (!adminAccessPath.contains(command)) {
                forwardToErrorPage(session, command, req, resp);
                return;
            }
        } else {
            logger.debug(CLASS_NAME + ", skip all if sections. session.getAttribute(USER_ROLE) = '" + session.getAttribute(USER_ROLE) + '\'');
            logger.debug("session.getAttribute(USER_ROLE).equals(User.Role.USER.toString()) = " + session.getAttribute(USER_ROLE).equals(User.Role.USER.toString()));
            filterChain.doFilter(req, resp);
            return;
        }
        filterChain.doFilter(req, resp);
    }

    /**
     * Get info from Cookies and set to Session
     *
     * @param req     HttpServletRequest
     * @param session current sesion
     */
    private void getCookies(HttpServletRequest req, HttpSession session) {
        if (session == null || session.getAttribute(USER_ROLE) == null || session.getAttribute(USER_ID).equals(0)) {
            CookieService cookieService = new CookieService();
            cookieService.initCookies(req);
            logger.debug("Entry to getCookies block in " + CLASS_NAME);
        } else
            logger.debug("Skip getCookies block in " + CLASS_NAME);
    }

    private void forwardToErrorPage(HttpSession session, String command, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.warn("Have no enough permits for the command (" + session.getAttribute(USER_ROLE) + ") '" + command + '\'');
        req.setAttribute(REQUEST_PARAM_ERROR_MESSAGE, "You have no enough permissions to visit this page");
        req.getRequestDispatcher(ERROR_PAGE_PATH).forward(req, resp);
    }

    private void redirectToLoginPage(HttpSession session, String command, HttpServletResponse resp)
            throws IOException {
        logger.warn("Have no enough permits for the command (" + session.getAttribute(USER_ROLE) + ") '" + command + '\'');
        logger.info("Redirect to login page");
        resp.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_LOGIN_PAGE));
    }

    @Override
    public void destroy() {
        logger.info("Exit from filter: " + CLASS_NAME);
    }

    /**
     * init GUEST accessible paths
     */
    private void initGuestAccess() {
        guestAccessPath.add(null);
        guestAccessPath.add(COMMAND_VIEW_ERROR_PAGE);
        guestAccessPath.add(COMMAND_VIEW_MAIN_PAGE);
        guestAccessPath.add(COMMAND_VIEW_SCHEDULE_PAGE);
        guestAccessPath.add(COMMAND_VIEW_SESSION_PAGE);

        guestAccessPath.add(COMMAND_VIEW_LOGIN_PAGE);
        guestAccessPath.add(COMMAND_LOGIN);
        guestAccessPath.add(COMMAND_REGISTER);
        guestAccessPath.add(COMMAND_VIEW_REGISTER_PAGE);
    }

    /**
     * init USER accessible paths
     */
    private void initUserAccess() {
        userAccessPath.add(null);
        userAccessPath.add(COMMAND_VIEW_ERROR_PAGE);
        userAccessPath.add(COMMAND_VIEW_MAIN_PAGE);
        userAccessPath.add(COMMAND_VIEW_SCHEDULE_PAGE);
        userAccessPath.add(COMMAND_VIEW_SESSION_PAGE);

        userAccessPath.add(COMMAND_LOGOUT);

        userAccessPath.add(COMMAND_VIEW_PROFILE_PAGE);
        userAccessPath.add(COMMAND_VIEW_BUY_TICKET_PAGE);
        userAccessPath.add(COMMAND_BUY_TICKET);
        userAccessPath.add(COMMAND_VIEW_SUCCESS_PAY_PAGE);
    }

    /**
     * init ADMIN accessible paths
     */
    private void initAdminAccess() {
        adminAccessPath.add(null);
        adminAccessPath.add(COMMAND_VIEW_ERROR_PAGE);
        adminAccessPath.add(COMMAND_VIEW_MAIN_PAGE);
        adminAccessPath.add(COMMAND_VIEW_SCHEDULE_PAGE);

        adminAccessPath.add(COMMAND_LOGOUT);

        adminAccessPath.add(COMMAND_VIEW_ADD_FILM_PAGE);
        adminAccessPath.add(COMMAND_ADD_FILM);
        adminAccessPath.add(COMMAND_VIEW_FILMS_SETTING_PAGE);

        adminAccessPath.add(COMMAND_VIEW_ADD_SESSION_PAGE);
        adminAccessPath.add(COMMAND_ADD_SESSION);
        adminAccessPath.add(COMMAND_VIEW_SESSIONS_SETTING_PAGE);
        adminAccessPath.add(COMMAND_DELETE_SESSION);
        adminAccessPath.add(COMMAND_VIEW_SESSION_INFO_PAGE);

    }
}
