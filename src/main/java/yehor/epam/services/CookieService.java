package yehor.epam.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import yehor.epam.entities.User;

public interface CookieService {
    /**
     * Set Cookie after login
     *
     * @param response   HttpServletResponse
     * @param user       user
     * @param rememberMe login checkbox Remember me
     */
    void loginCookie(HttpServletResponse response, User user, String rememberMe);

    /**
     * Clean login cookies
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    void logoutCookie(HttpServletRequest request, HttpServletResponse response);

    /**
     * Setting Locale/language cookies
     *
     * @param response HttpServletResponse
     * @param locale   locale param value
     */
    void setLocaleCookie(HttpServletResponse response, String locale);

    /**
     * Init cookies when start session
     *
     * @param request HttpServletRequest
     */
    void initSessionWithCookie(HttpServletRequest request);
}
