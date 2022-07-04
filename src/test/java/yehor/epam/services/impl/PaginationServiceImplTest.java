package yehor.epam.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import yehor.epam.services.PaginationService;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class PaginationServiceImplTest {
    private PaginationService paginationService = spy(PaginationServiceImpl.class);
    private HttpServletRequest request = mock(HttpServletRequest.class);

    @Test
    void getPaginationParamsFromRequest() {
        final Map<String, Integer> map = paginationService.getPaginationParamsFromRequest(request);
        Assertions.assertFalse(map.isEmpty());
    }
}