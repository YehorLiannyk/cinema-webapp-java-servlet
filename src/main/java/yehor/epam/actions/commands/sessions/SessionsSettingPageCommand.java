package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Session;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.JspPagePathConstants.SESSIONS_SETTING_PAGE_PATH;

/**
 * Command to show Admin Sessions setting page
 */
public class SessionsSettingPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(SessionsSettingPageCommand.class);
    private static final String CLASS_NAME = SessionsSettingPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + CLASS_NAME + " execute command");
            final List<Session> sessionList = getSessionList(factory);
            request.setAttribute("sessionList", sessionList);
            request.getRequestDispatcher(SESSIONS_SETTING_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    private List<Session> getSessionList(DAOFactory factory) {
        final SessionDAO sessionDAO = factory.getSessionDao();
        return sessionDAO.findAll();
    }
}
