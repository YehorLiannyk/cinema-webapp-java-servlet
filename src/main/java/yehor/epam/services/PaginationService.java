package yehor.epam.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * Service for pagination
 */
public interface PaginationService {
    /**
     * Get parameter for pagination from {@link HttpServletRequest}
     *
     * @param request {@link HttpServletRequest}
     * @return map of pagination params
     */
    Map<String, Integer> getPaginationParamsFromRequest(HttpServletRequest request);
}
