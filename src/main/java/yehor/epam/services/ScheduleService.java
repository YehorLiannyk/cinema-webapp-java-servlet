package yehor.epam.services;

import org.apache.log4j.Logger;
import yehor.epam.utilities.LoggerManager;

import java.util.HashMap;
import java.util.Map;

import static yehor.epam.utilities.OtherConstants.*;

/**
 * Class service of Schedule page
 */
public class ScheduleService {
    private static final Logger logger = LoggerManager.getLogger(ScheduleService.class);

    private ScheduleService() {
    }

    /**
     * Clean off received parameterMap of non filter/sort parameters
     *
     * @param parameterMap request's parameterMap
     * @return map containing only filter/sort parameters
     */
    public static Map<String, String> getFilterSortMapFromParams(Map<String, String[]> parameterMap) {
        final Map<String, String> filterSortMap = new HashMap<>();

        final boolean hasSortBy = parameterMap.containsKey(SESSION_SORT_BY_PARAM_NAME);
        final boolean hasSortMethod = parameterMap.containsKey(SESSION_SORT_METHOD_PARAM_NAME);
        final boolean hasFilterShow = parameterMap.containsKey(SESSION_FILTER_SHOW_PARAM_NAME);

        if (hasSortBy && hasSortMethod && hasFilterShow) {
            filterSortMap.put(SESSION_SORT_BY_PARAM_NAME, parameterMap.get(SESSION_SORT_BY_PARAM_NAME)[0]);
            filterSortMap.put(SESSION_SORT_METHOD_PARAM_NAME, parameterMap.get(SESSION_SORT_METHOD_PARAM_NAME)[0]);
            filterSortMap.put(SESSION_FILTER_SHOW_PARAM_NAME, parameterMap.get(SESSION_FILTER_SHOW_PARAM_NAME)[0]);
        }
        logger.info("Finish form filterSortMap");
        return filterSortMap;
    }
}
