package yehor.epam.services.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import yehor.epam.entities.User;
import yehor.epam.services.CookieService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.constants.OtherConstants.*;

/**
 * Class service for Cookies
 */
public class CookieServiceImpl implements CookieService {
    private static final Logger logger = LoggerManager.getLogger(CookieServiceImpl.class);


    @Override
    public void loginCookie(HttpServletResponse response, User user, String rememberMe) {
        Cookie cookieId = new Cookie(USER_ID, Integer.toString(user.getId()));
        Cookie cookieRole = new Cookie(USER_ROLE, user.getUserRole().toString());
        int cookieLifetime = COOKIE_LOGIN_LIFETIME;
        if (rememberMe == null || rememberMe.isEmpty()) cookieLifetime = -1;
        cookieId.setMaxAge(cookieLifetime);
        cookieRole.setMaxAge(cookieLifetime);
        response.addCookie(cookieId);
        response.addCookie(cookieRole);
        logger.debug("Set user's cookie with id: " + user.getId() + ", role = " + user.getUserRole().toString() + " login");
    }


    @Override
    public void logoutCookie(HttpServletRequest request, HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        int userId = -1;
        String userRole = null;
        for (Cookie cookie : cookies) {
            if (cookie != null) {
                final String cookieName = cookie.getName();
                final boolean isUserID = cookieName.equals(USER_ID);
                final boolean isUserRole = cookieName.equals(USER_ROLE);
                if (isUserID || isUserRole) {
                    if (isUserID) {
                        userId = Integer.parseInt(cookie.getValue());
                        cookie.setValue("");
                    }
                    if (isUserRole) {
                        userRole = cookie.getValue();
                        cookie.setValue(User.Role.GUEST.toString());
                    }
                    cookie.setMaxAge(-1);
                    response.addCookie(cookie);
                }
            }
        }
        logger.debug("User with id: " + userId + ", role: " + userRole + "has erased his login cookies");
    }


    @Override
    public void setLocaleCookie(HttpServletResponse response, String locale) {
        Cookie cookie = new Cookie(LANG, locale);
        cookie.setMaxAge(COOKIE_LANG_LIFETIME);
        response.addCookie(cookie);
        logger.info("Set locale cookie, where " + LANG + " = " + locale);
    }


    @Override
    public void initSessionWithCookie(HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        final Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                final String cookieName = cookie.getName();
                if (cookieName.equals(USER_ID))
                    session.setAttribute(USER_ID, cookie.getValue());
                if (cookieName.equals(USER_ROLE))
                    session.setAttribute(USER_ROLE, cookie.getValue());
                if (cookieName.equals(LANG))
                    session.setAttribute(LANG, cookie.getValue());
            }
            logger.info("Initialize session with cookies, sessionId = " + session.getId());
        }
    }
}
