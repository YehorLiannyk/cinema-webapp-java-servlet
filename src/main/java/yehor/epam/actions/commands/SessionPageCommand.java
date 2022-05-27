package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.SeatDAO;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.JspPagePathConstants.SESSIONS_PAGE_PATH;

public class SessionPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(SessionPageCommand.class);
    private String className = SessionPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + className + " execute command");

            final int sessionId = Integer.parseInt(request.getParameter("sessionId"));
            logger.debug("request.getParameter(\"sessionId\") = " + sessionId);

            final Session session = getSession(factory, sessionId);
            request.setAttribute("session", session);
            logger.debug("Session: " + session.toString());

            final List<Seat> allSeatList = getSeats(factory);
            request.setAttribute("allSeatList", allSeatList);
            logger.debug("allSeatList: " + allSeatList.toString());

            final List<Seat> freeSeatList = getFreeSeats(factory, sessionId);
            request.setAttribute("freeSeatList", freeSeatList);
            logger.debug("freeSeatList: " + freeSeatList.toString());

            request.getRequestDispatcher(SESSIONS_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, className, e);
        }
    }

    private List<Seat> getFreeSeats(DAOFactory factory, int sessionId) {
        final SeatDAO seatDAO = factory.getSeatDao();
        return seatDAO.findAllFreeSeatBySessionId(sessionId);
    }

    private List<Seat> getSeats(DAOFactory factory) {
        final SeatDAO seatDAO = factory.getSeatDao();
        return seatDAO.findAll();
    }

    private Session getSession(DAOFactory factory, int sessionId) {
        final SessionDAO sessionDAO = factory.getSessionDao();
        return sessionDAO.findById(sessionId);
    }
}
