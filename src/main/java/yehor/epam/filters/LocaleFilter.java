package yehor.epam.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.jstl.core.Config;
import org.apache.log4j.Logger;
import yehor.epam.services.CookieService;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;
import java.util.Locale;

import static yehor.epam.utilities.OtherConstants.DEFAULT_LANG;
import static yehor.epam.utilities.OtherConstants.LANG;

/**
 * Filter for localization
 */
@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    private static final Logger logger = LoggerManager.getLogger(LocaleFilter.class);
    private static final String CLASS_NAME = LocaleFilter.class.getName();

    public void init(FilterConfig arg0) throws ServletException {
        logger.info("Init " + CLASS_NAME);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String locale = null;

        //get locale from cookie
        logger.debug("Under set locale ");
        final Cookie[] cookies = req.getCookies();
        logger.debug("cookie[] is null: " + (cookies == null));
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LANG)) {
                    logger.debug("Entry to cookie.getName().equals(LANG)");
                    locale = cookie.getValue();
                    logger.info("Set locale = " + locale + " from cookie");
                }
            }
        }

        //get locale from request if it has
        if (req.getParameter(LANG) != null) {
            final String langParameter = req.getParameter(LANG);
            CookieService cookieService = new CookieService();
            cookieService.setLocaleCookie(res, langParameter);
            locale = langParameter;
            logger.info("Set locale = " + langParameter + " from request");
        }

        //set locale
        if (locale == null) locale = DEFAULT_LANG;
        Locale localeObj = new Locale(locale);
        Config.set(req.getSession(), Config.FMT_LOCALE, localeObj);

        chain.doFilter(request, response);
    }

    public void destroy() {
        logger.info("Destroy " + CLASS_NAME);
    }


}