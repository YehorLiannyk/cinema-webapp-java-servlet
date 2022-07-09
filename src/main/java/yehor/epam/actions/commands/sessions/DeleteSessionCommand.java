package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.SessionService;
import yehor.epam.services.impl.SessionServiceImpl;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.RedirectManager;

import static yehor.epam.utilities.constants.CommandConstants.COMMAND_VIEW_SESSIONS_SETTING_PAGE;

/**
 * Deleting Session (Admin)
 */
public class DeleteSessionCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(DeleteSessionCommand.class);
    private static final String CLASS_NAME = DeleteSessionCommand.class.getName();
    private final SessionService sessionService;

    public DeleteSessionCommand() {
        sessionService = new SessionServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final int sessionId = Integer.parseInt(request.getParameter("sessionId"));
            sessionService.delete(sessionId);
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_SESSIONS_SETTING_PAGE));
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
