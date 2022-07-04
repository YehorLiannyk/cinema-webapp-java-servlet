package yehor.epam.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface PaginationService {
    Map<String, Integer> getPaginationParamsFromRequest(HttpServletRequest request);
}
