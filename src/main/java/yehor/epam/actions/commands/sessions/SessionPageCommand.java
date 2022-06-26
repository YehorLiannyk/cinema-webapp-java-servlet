package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.services.ErrorService;
import yehor.epam.services.impl.SessionServiceImpl;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.JspPagePathConstants.SESSIONS_PAGE_PATH;

/**
 * Command to show Sessions page
 */
public class SessionPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(SessionPageCommand.class);
    private static final String CLASS_NAME = SessionPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.info("Created DAOFactory in " + CLASS_NAME + " execute command");
            SessionServiceImpl sessionService = new SessionServiceImpl();
            sessionService.prepareSessionPage(request, factory);
            request.getRequestDispatcher(SESSIONS_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
