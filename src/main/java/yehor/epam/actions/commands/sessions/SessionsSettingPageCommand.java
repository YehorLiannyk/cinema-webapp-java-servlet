package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Session;
import yehor.epam.services.PaginationService;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.SessionService;
import yehor.epam.services.impl.PaginationServiceImpl;
import yehor.epam.services.impl.SessionServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.JspPagePathConstants.SESSIONS_SETTING_PAGE_PATH;
import static yehor.epam.utilities.constants.OtherConstants.PAGE_NO_PARAM;
import static yehor.epam.utilities.constants.OtherConstants.PAGE_SIZE_PARAM;

/**
 * Command to show Admin Sessions setting page
 */
public class SessionsSettingPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(SessionsSettingPageCommand.class);
    private static final String CLASS_NAME = SessionsSettingPageCommand.class.getName();
    private final SessionService sessionService;
    private final PaginationService paginationService;

    public SessionsSettingPageCommand() {
        sessionService = new SessionServiceImpl();
        paginationService = new PaginationServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final Map<String, Integer> paginationMap = paginationService.getPaginationParamsFromRequest(request);
            int page = paginationMap.get(PAGE_NO_PARAM);
            int size = paginationMap.get(PAGE_SIZE_PARAM);
            final int totalPages = sessionService.countTotalPages(size);

            request.setAttribute("totalPages", totalPages);
            final List<Session> sessionList = sessionService.getAll(page, size);
            request.setAttribute("sessionList", sessionList);
            request.getRequestDispatcher(SESSIONS_SETTING_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
