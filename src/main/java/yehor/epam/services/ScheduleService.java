package yehor.epam.services;

import org.apache.log4j.Logger;
import yehor.epam.utilities.LoggerManager;

import java.util.HashMap;
import java.util.Map;

import static yehor.epam.utilities.OtherConstants.*;

public class ScheduleService {
    private static final Logger logger = LoggerManager.getLogger(ScheduleService.class);

    public static Map<String, String> getFilterSortMapFromParams(Map<String, String[]> parameterMap) {
        final Map<String, String> filterSortMap = new HashMap<>();

        final boolean hasSortBy = parameterMap.containsKey(SESSION_SORT_BY_PARAM_NAME);
        final boolean hasSortMethod = parameterMap.containsKey(SESSION_SORT_METHOD_PARAM_NAME);
        final boolean hasFilterAll = parameterMap.containsKey(SESSION_FILTER_SHOW_ALL);
        final boolean hasFilterOnlyAvailable = parameterMap.containsKey(SESSION_FILTER_SHOW_ONLY_AVAILABLE);

        if (hasSortBy && hasSortMethod && (hasFilterOnlyAvailable || hasFilterAll)) {
            filterSortMap.put(SESSION_SORT_BY_PARAM_NAME, parameterMap.get(SESSION_SORT_BY_PARAM_NAME)[0]);
            filterSortMap.put(SESSION_SORT_METHOD_PARAM_NAME, parameterMap.get(SESSION_SORT_METHOD_PARAM_NAME)[0]);
            if (hasFilterAll)
                filterSortMap.put(SESSION_FILTER_SHOW_ALL, parameterMap.get(SESSION_FILTER_SHOW_ALL)[0]);
            else
                filterSortMap.put(SESSION_FILTER_SHOW_ONLY_AVAILABLE, parameterMap.get(SESSION_FILTER_SHOW_ONLY_AVAILABLE)[0]);
        }
        logger.debug("Fill filterSortMap: " + filterSortMap);
        return filterSortMap;
    }

    private ScheduleService() {
    }
}
