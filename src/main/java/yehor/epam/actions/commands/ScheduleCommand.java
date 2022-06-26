package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Session;
import yehor.epam.services.impl.ErrorServiceImpl;
import yehor.epam.services.impl.ScheduleService;
import yehor.epam.utilities.LoggerManager;

import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.JspPagePathConstants.SCHEDULE_PAGE_PATH;

/**
 * Command to set Schedule page
 */
public class ScheduleCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(ScheduleCommand.class);
    private static final String CLASS_NAME = ScheduleCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.info("Created DAOFactory in " + CLASS_NAME + " execute command");
            List<Session> sessionList = null;

            final Map<String, String[]> parameterMap = request.getParameterMap();
            final Map<String, String> filterSortMap = ScheduleService.getFilterSortMapFromParams(parameterMap);

            sessionList = getAppropriateSessionList(factory, filterSortMap);
            request.setAttribute("sessionList", sessionList);

            request.getRequestDispatcher(SCHEDULE_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorServiceImpl.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Return usual SessionList if Schedule page wasn't sort or filter
     *
     * @param factory       DAOFactory
     * @param filterSortMap Map contains only sorting and filtering params
     * @return usual SessionList or already filtered and sorted
     */
    private List<Session> getAppropriateSessionList(DAOFactory factory, Map<String, String> filterSortMap) {
        final SessionDAO sessionDAO = factory.getSessionDao();
        if (!filterSortMap.isEmpty())
            return sessionDAO.getFilteredAndSortedSessionList(filterSortMap);
        else
            return sessionDAO.findAll();
    }
}
