package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Session;
import yehor.epam.services.impl.ErrorServiceImpl;
import yehor.epam.services.SessionService;
import yehor.epam.services.impl.SessionServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.constants.JspPagePathConstants.SESSIONS_SETTING_PAGE_PATH;

/**
 * Command to show Admin Sessions setting page
 */
public class SessionsSettingPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(SessionsSettingPageCommand.class);
    private static final String CLASS_NAME = SessionsSettingPageCommand.class.getName();
    private final SessionService sessionService;

    public SessionsSettingPageCommand() {
        sessionService = new SessionServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final List<Session> sessionList = sessionService.getAll();
            request.setAttribute("sessionList", sessionList);
            request.getRequestDispatcher(SESSIONS_SETTING_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorServiceImpl.handleException(request, response, CLASS_NAME, e);
        }
    }
}
