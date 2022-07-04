package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.SeatService;
import yehor.epam.services.SessionService;
import yehor.epam.services.impl.SeatServiceImpl;
import yehor.epam.services.impl.SessionServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.constants.JspPagePathConstants.SESSION_INFO_PAGE_PATH;

/**
 * Command to show Admin Session info page
 */
public class SessionInfoPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(SessionInfoPageCommand.class);
    private static final String CLASS_NAME = SessionInfoPageCommand.class.getName();
    private final SessionService sessionService;
    private final SeatService seatService;

    public SessionInfoPageCommand() {
        sessionService = new SessionServiceImpl();
        seatService = new SeatServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final int sessionId = Integer.parseInt(request.getParameter("sessionId"));
            Session session = sessionService.getById(sessionId);
            List<Seat> freeSeatList = seatService.getFreeSeatsBySessionId(sessionId);
            List<Seat> allSeatList = seatService.getAll();

            request.setAttribute("session", session);
            request.setAttribute("allSeatList", allSeatList);
            request.setAttribute("freeSeatList", freeSeatList);

            request.getRequestDispatcher(SESSION_INFO_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
