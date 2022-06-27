package yehor.epam.services;

import jakarta.servlet.http.HttpServletRequest;

public interface GeneralService {
    /**
     * Initialization commonly used params
     *
     * @param request HttpServletRequest
     */
    void initParams(HttpServletRequest request);
}
