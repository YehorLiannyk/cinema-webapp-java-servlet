package yehor.epam.services;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import yehor.epam.dao.SeatDAO;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

/**
 * Class service of Session
 */
public class SessionService {
    private static final Logger logger = LoggerManager.getLogger(SessionService.class);

    /**
     * Prepare Session list, allSeat and only freeSeat lists and set to them to session
     *
     * @param request HttpServletRequest
     * @param factory DAOFactory
     */
    public void prepareSessionPage(HttpServletRequest request, DAOFactory factory) {
        final int sessionId = Integer.parseInt(request.getParameter("sessionId"));

        final Session session = getSession(factory, sessionId);
        request.setAttribute("session", session);
        logger.debug("Session: " + session.toString());

        final List<Seat> allSeatList = getSeats(factory);
        request.setAttribute("allSeatList", allSeatList);
        logger.debug("allSeatList: " + allSeatList.toString());

        final List<Seat> freeSeatList = getFreeSeats(factory, sessionId);
        request.setAttribute("freeSeatList", freeSeatList);
        logger.debug("freeSeatList: " + freeSeatList.toString());
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
