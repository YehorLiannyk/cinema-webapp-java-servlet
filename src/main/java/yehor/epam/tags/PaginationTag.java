package yehor.epam.tags;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspTagException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.slf4j.Logger;
import yehor.epam.services.PaginationService;
import yehor.epam.services.impl.PaginationServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;
import java.util.Map;

import static yehor.epam.utilities.constants.OtherConstants.PAGE_NO_PARAM;
import static yehor.epam.utilities.constants.OtherConstants.PAGE_SIZE_PARAM;

/**
 * Class of implementing pagination tag
 */
public class PaginationTag extends TagSupport {
    private static final Logger logger = LoggerManager.getLogger(PaginationTag.class);
    private HttpServletRequest request;
    private Integer totalPages;
    private String prev;
    private String next;

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public int doStartTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            logger.debug("Total pages = {}", totalPages);
            final PaginationService paginationService = new PaginationServiceImpl();
            final Map<String, Integer> paginationMap = paginationService.getPaginationParamsFromRequest(request);
            int page = paginationMap.get(PAGE_NO_PARAM);
            int size = paginationMap.get(PAGE_SIZE_PARAM);

            final String requestURI = (String) request.getAttribute("jakarta.servlet.forward.request_uri");
            final String queryString = request.getQueryString();

            String query = "";
            if (queryString != null && !queryString.isBlank()) {
                query = getCleanQuery(queryString);
            }

            logger.debug("requestURI: " + requestURI);
            logger.debug("Ready query: " + query);

            query = query.lastIndexOf('&') == query.length() - 1 ? query : query + '&';
            if (totalPages > 1) {
                printPaginationBlock(out, page, size, requestURI, query);
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

    private void printPaginationBlock(JspWriter out, int page, int size, String requestURI, String query) throws IOException {
        String prevUrl = requestURI + '?' + query + PAGE_NO_PARAM + '=' + (page - 1) + '&' + PAGE_SIZE_PARAM + '=' + size;
        String nextUrl = requestURI + '?' + query + PAGE_NO_PARAM + '=' + (page + 1) + '&' + PAGE_SIZE_PARAM + '=' + size;

        out.write("<div class='p-2 w-100'><nav class='blog-pagination mx-auto'>");
        if (page > 1 && page <= totalPages) {
            out.write("<a class='btn btn-outline-primary mx-1' href='" + prevUrl + "'>" + prev + "</a>");
        } else {
            out.write("<a class='btn btn-outline-secondary mx-1 disabled' href='" + prevUrl + "'>" + prev + "</a>");
        }
        if (page > 0 && page < totalPages) {
            out.write("<a class='btn btn-outline-primary mx-1' href='" + nextUrl + "'>" + next + "</a>");
        } else {
            out.write("<a class='btn btn-outline-secondary mx-1 disabled' href='" + nextUrl + "'>" + next + "</a>");
        }
        out.write("</nav></div>");
    }

    private String getCleanQuery(String queryString) {
        String query = getQueryWithout(queryString, PAGE_NO_PARAM);
        query = getQueryWithout(query, PAGE_SIZE_PARAM);
        return query;
    }

    private String getQueryWithout(String oldQuery, String param) {
        String query = oldQuery;
        if (oldQuery.contains(param)) {
            final int start = oldQuery.indexOf(param + "=");
            final int indexOfNextParam = oldQuery.indexOf("&", start);
            final int lastIndex = oldQuery.length() - 1;
            final int end = indexOfNextParam == -1 ? lastIndex : indexOfNextParam + 1;
            query = oldQuery.substring(0, start) + oldQuery.substring(end, lastIndex);
        }
        return query;
    }
}
