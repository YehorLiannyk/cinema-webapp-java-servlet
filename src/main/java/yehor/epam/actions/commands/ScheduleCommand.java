package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Session;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.PaginationService;
import yehor.epam.services.SessionService;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.impl.PaginationServiceImpl;
import yehor.epam.services.impl.SessionServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.JspPagePathConstants.SCHEDULE_PAGE_PATH;
import static yehor.epam.utilities.constants.OtherConstants.*;

/**
 * Command to set Schedule page
 */
public class ScheduleCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(ScheduleCommand.class);
    private static final String CLASS_NAME = ScheduleCommand.class.getName();
    private final SessionService sessionService;
    private final PaginationService paginationService;

    public ScheduleCommand() {
        sessionService = new SessionServiceImpl();
        paginationService = new PaginationServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in {}", CLASS_NAME);
        try {
            final Map<String, Integer> paginationMap = paginationService.getPaginationParamsFromRequest(request);
            int page = paginationMap.get(PAGE_NO_PARAM);
            int size = paginationMap.get(PAGE_SIZE_PARAM);
            final Map<String, String[]> parameterMap = request.getParameterMap();
            final Map<String, String> filterSortMap = sessionService.getFilterSortMapFromParams(parameterMap);
            final int totalPages = sessionService.countTotalPages(size);


            List<Session> sessionList = getAppropriateSessionList(filterSortMap, page, size);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("sessionList", sessionList);
            request.getRequestDispatcher(SCHEDULE_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Return usual SessionList if Schedule page wasn't sort or filter
     *
     * @param filterSortMap Map contains only sorting and filtering params
     * @return usual SessionList or already filtered and sorted
     */
    private List<Session> getAppropriateSessionList(Map<String, String> filterSortMap, int page, int size) throws ServiceException {
        if (!filterSortMap.isEmpty())
            return sessionService.getFilteredAndSorted(filterSortMap, page, size);
        logger.debug("Return not sorted session list");
        return sessionService.getAll(page, size);
    }
}
