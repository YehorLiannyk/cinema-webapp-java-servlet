package yehor.epam.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import yehor.epam.entities.User;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.OtherConstants.*;

public class CookieService {
    private static final Logger logger = LoggerManager.getLogger(CookieService.class);

    public void addLoginCookie(HttpServletResponse response, User user) {
        Cookie cookieId = new Cookie(USER_ID, Integer.toString(user.getId()));
        Cookie cookieRole = new Cookie(USER_ROLE, user.getUserRole().toString());
        cookieId.setMaxAge(COOKIE_LOGIN_LIFETIME);
        cookieRole.setMaxAge(COOKIE_LOGIN_LIFETIME);
        response.addCookie(cookieId);
        response.addCookie(cookieRole);
        logger.debug("Set user's cookie with id: " + user.getId() + ", role = " + user.getUserRole().toString() + " login");
    }

    public void eraseLoginCookie(HttpServletRequest request, HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        int userId = -1;
        String userRole = null;
        for (Cookie cookie : cookies) {
            if (cookie != null) {
                final String cookieName = cookie.getName();
                final boolean isUserID = cookieName.equals(USER_ID);
                final boolean isUserRole = cookieName.equals(USER_ROLE);
                if (isUserID || isUserRole) {
                    if (isUserID) userId = Integer.parseInt(cookie.getValue());
                    if (isUserRole) userRole = cookie.getValue();
                    cookie.setValue("");
                    cookie.setMaxAge(-1);
                    response.addCookie(cookie);
                }
            }
        }
        logger.debug("User with id: " + userId + ", role: " + userRole + "has erased his login cookies");
    }

    public void initCookies(HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        final Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            final String cookieName = cookie.getName();
            if (cookie != null) {
                if (cookieName.equals(USER_ID))
                    session.setAttribute(USER_ID, cookie.getValue());
                if (cookieName.equals(USER_ROLE))
                    session.setAttribute(USER_ROLE, cookie.getValue());
            }
        }
        logger.debug("Initialize session with cookies, sessionId = " + session.getId());
    }
}