package yehor.epam.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.jstl.core.Config;
import org.slf4j.Logger;
import yehor.epam.services.CookieService;
import yehor.epam.services.GeneralService;
import yehor.epam.services.impl.CookieServiceImpl;
import yehor.epam.services.impl.GeneralServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;
import java.util.Locale;

import static yehor.epam.utilities.constants.OtherConstants.*;

/**
 * Filter for localization
 */
@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    private static final Logger logger = LoggerManager.getLogger(LocaleFilter.class);
    private static final String CLASS_NAME = LocaleFilter.class.getName();

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        logger.info("Init {}", CLASS_NAME);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);

        setSessionWithCookies(req, session);
        GeneralService generalService = new GeneralServiceImpl();
        generalService.initParams(req);

        String locale = session.getAttribute(LANG) != null ? (String) session.getAttribute(LANG) : DEFAULT_LANG;

        //get locale from request if it has
        if (req.getParameter(LANG) != null) {
            final String langParameter = req.getParameter(LANG);
            CookieServiceImpl cookieService = new CookieServiceImpl();
            cookieService.setLocaleCookie(res, langParameter);
            session.setAttribute(LANG, langParameter);
            locale = langParameter;
            logger.info("Set locale = {} from request", langParameter);
        }

        //set locale
        Locale localeObj = new Locale(locale);
        Config.set(req.getSession(), Config.FMT_LOCALE, localeObj);

        chain.doFilter(request, response);
    }

    /**
     * Get info from Cookies and set to Session
     *
     * @param req     HttpServletRequest
     * @param session current HttpSession
     */
    private void setSessionWithCookies(HttpServletRequest req, HttpSession session) {
        if (session == null || session.getAttribute(USER_ROLE) == null || session.getAttribute(USER_ID).equals(0) || session.getAttribute(LANG) == null) {
            CookieService cookieService = new CookieServiceImpl();
            cookieService.initSessionWithCookie(req);
        } else logger.debug("Skip getCookies block in {}, session lang = {}", CLASS_NAME, session.getAttribute(LANG));
    }

    @Override
    public void destroy() {
        logger.info("Destroy {}", CLASS_NAME);
    }


}