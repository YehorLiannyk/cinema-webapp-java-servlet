package yehor.epam.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import yehor.epam.services.PaginationService;
import yehor.epam.utilities.LoggerManager;

import java.util.HashMap;
import java.util.Map;

import static yehor.epam.utilities.constants.OtherConstants.*;

public class PaginationServiceImpl implements PaginationService {
    private static final Logger logger = LoggerManager.getLogger(PaginationServiceImpl.class);
    private static final String CLASS_NAME = PaginationServiceImpl.class.getName();

    @Override
    public Map<String, Integer> getPaginationParamsFromRequest(HttpServletRequest request) {
        Map<String, Integer> map = new HashMap<>();
        final String pageParam = request.getParameter(PAGE_NO_PARAM);
        final String sizeParam = request.getParameter(PAGE_SIZE_PARAM);
        int page = pageParam == null || pageParam.isBlank() ? 1 : Integer.parseInt(pageParam);
        int size = sizeParam == null || sizeParam.isBlank() ? DEF_PAGING_SIZE : Integer.parseInt(sizeParam);
        logger.debug("PageNo = " + page + ", pageSize = " + size);
        map.put(PAGE_NO_PARAM, page);
        map.put(PAGE_SIZE_PARAM, size);
        return map;
    }
}
