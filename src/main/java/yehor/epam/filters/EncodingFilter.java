package yehor.epam.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;

/**
 * Encoding to UTF-8 filter
 */
@WebFilter(urlPatterns = {"/*"}, filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    private static final Logger logger = LoggerManager.getLogger(EncodingFilter.class);
    private static final String CLASS_NAME = EncodingFilter.class.getName();
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Entry to filter: " + CLASS_NAME);
        encoding = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("Exit from filter: " + CLASS_NAME);
    }
}
