package yehor.epam.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import yehor.epam.entities.User;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static yehor.epam.utilities.ActionCommandConstants.*;
import static yehor.epam.utilities.JspPagePathConstants.ERROR_PAGE_PATH;
import static yehor.epam.utilities.OtherConstants.REQUEST_PARAM_ERROR_MESSAGE;
import static yehor.epam.utilities.OtherConstants.USER_ROLE;

public class SecurityFilter implements Filter {
    private static final Logger logger = LoggerManager.getLogger(SecurityFilter.class);
    private final List<String> guestAccessPath = new ArrayList<>();
    private final List<String> userAccessPath = new ArrayList<>();
    private final List<String> adminAccessPath = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Entry to filter");
        initGuestAccess();
        initUserAccess();
        initAdminAccess();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        final HttpSession session = req.getSession();

        String command = req.getParameter("command");
        logger.debug("Command from SecurityFilter = " + req.getParameter("command"));
        logger.debug("Session role attribute from SecurityFilter = " + session.getAttribute(USER_ROLE));

        if (session.getAttribute(USER_ROLE) == null || session.getAttribute(USER_ROLE).toString().equals(User.Role.GUEST.toString())) {
            logger.debug("Entry to GUEST's if section. Session.USER_ROLE = '" + session.getAttribute(USER_ROLE) + '\'');
            if (!guestAccessPath.contains(command)) {
                forwardToErrorPage(session, command, req, resp);
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
            logger.debug("SecurityFilter, skip all if sections. session.getAttribute(USER_ROLE) = '" + session.getAttribute(USER_ROLE) + '\'');
            logger.debug("session.getAttribute(USER_ROLE).equals(User.Role.USER.toString()) = " + session.getAttribute(USER_ROLE).equals(User.Role.USER.toString()));
            filterChain.doFilter(req, resp);
            return;
        }
        filterChain.doFilter(req, resp);
    }

    private void forwardToErrorPage(HttpSession session, String command, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.warn("Have no enough permits for the command (" + session.getAttribute(USER_ROLE).toString() + ") '" + command + '\'');
        req.setAttribute(REQUEST_PARAM_ERROR_MESSAGE, "You have no enough permissions to visit this page");
        req.getRequestDispatcher(ERROR_PAGE_PATH).forward(req, resp);
    }

    @Override
    public void destroy() {
        logger.debug("Exit from filter");
    }

    /**
     * init GUEST accessible paths
     */
    private void initGuestAccess() {
        guestAccessPath.add(null);
        guestAccessPath.add(ACTION_VIEW_MAIN);
        guestAccessPath.add(ACTION_VIEW_LOGIN);
        guestAccessPath.add(ACTION_LOGIN);
        guestAccessPath.add(ACTION_REGISTER);
        guestAccessPath.add(ACTION_VIEW_REGISTER);
        guestAccessPath.add(ACTION_VIEW_SCHEDULE);
    }

    /**
     * init USER accessible paths
     */
    private void initUserAccess() {
        userAccessPath.add(null);
        userAccessPath.add(ACTION_VIEW_MAIN);
        userAccessPath.add(ACTION_VIEW_SCHEDULE);
        userAccessPath.add(ACTION_LOGOUT);
        userAccessPath.add(PROFILE_VIEW_PAGE);
    }

    /**
     * init ADMIN accessible paths
     */
    private void initAdminAccess() {
        adminAccessPath.add(null);
        adminAccessPath.add(ACTION_VIEW_MAIN);
        adminAccessPath.add(ACTION_VIEW_SCHEDULE);
        adminAccessPath.add(ACTION_LOGOUT);
        adminAccessPath.add(PROFILE_VIEW_PAGE);
    }
}
