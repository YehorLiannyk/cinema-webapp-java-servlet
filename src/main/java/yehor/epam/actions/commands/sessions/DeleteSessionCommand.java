package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.RedirectManager;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.CommandConstants.COMMAND_VIEW_SESSIONS_SETTING_PAGE;

public class DeleteSessionCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(DeleteSessionCommand.class);
    private static final String CLASS_NAME = DeleteSessionCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + CLASS_NAME + " execute command");
            final int sessionId = Integer.parseInt(request.getParameter("sessionId"));
            final SessionDAO sessionDAO = factory.getSessionDao();
            sessionDAO.delete(sessionId);
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_SESSIONS_SETTING_PAGE));
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

}
