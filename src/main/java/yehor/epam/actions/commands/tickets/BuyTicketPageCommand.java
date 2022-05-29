package yehor.epam.actions.commands.tickets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.SeatDAO;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.UserDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.entities.Ticket;
import yehor.epam.entities.User;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import java.math.BigDecimal;
import java.util.*;

import static yehor.epam.utilities.JspPagePathConstants.PAYING_PAGE_PATH;
import static yehor.epam.utilities.OtherConstants.USER_ID;

public class BuyTicketPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(BuyTicketPageCommand.class);
    private String className = BuyTicketPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + className + " execute command");

            logger.debug("request.getParameter(\"sessionId\") = " + request.getParameter("sessionId"));
            final int sessionId = Integer.parseInt(request.getParameter("sessionId"));

            final Session session = getSession(factory, sessionId);
            request.setAttribute("session", session);
            logger.debug("Session: " + session.toString());

            final int userId = Integer.parseInt(request.getSession().getAttribute(USER_ID).toString());
            logger.debug("request.getSession().getAttribute(USER_ID).toString() = " + request.getSession().getAttribute(USER_ID).toString());
            final User user = getUser(factory, userId);

            final List<Seat> seatListFromRequest = getSeatListFromRequest(request, factory);

            final List<Ticket> ticketList = getTicketList(session, seatListFromRequest, user);

            final BigDecimal totalCost = getTotalCost(ticketList);

            request.getSession().setAttribute("ticketList", ticketList);
            request.setAttribute("totalCost", totalCost);
            request.getRequestDispatcher(PAYING_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, className, e);
        }
    }

    private List<Ticket> getTicketList(Session session, List<Seat> seatList, User user) {
        final List<Ticket> ticketList = new ArrayList<>();
        for (Seat seat : seatList) {
            final Ticket ticket = new Ticket(session, user, seat, session.getTicketPrice());
            logger.debug("Added ticket to ticketList: " + ticket.toString());
            ticketList.add(ticket);
        }
        if (ticketList.isEmpty()) {
            logger.error("ticketList is Empty");
            throw new NullPointerException("ticketList is Empty");
        }
        return ticketList;
    }

    private Session getSession(DAOFactory factory, int sessionId) {
        final SessionDAO sessionDAO = factory.getSessionDao();
        return sessionDAO.findById(sessionId);
    }

    private User getUser(DAOFactory factory, int userId) {
        final UserDAO userDao = factory.getUserDao();
        return userDao.findById(userId);
    }

    private List<Seat> getSeatListFromRequest(HttpServletRequest request, DAOFactory factory) {
        List<Seat> seatList = new ArrayList<>();
        final Map<String, String[]> parameterMap = request.getParameterMap();
        final String[] seatIds = parameterMap.get("seatIds");
        final SeatDAO seatDao = factory.getSeatDao();
        if (seatIds == null || seatIds.length == 0) {
            logger.error("Seat Array is null or empty");
            throw new NullPointerException("You did not choose any ticket");
        }
        for (String seatId : seatIds) {
            final Seat seat = seatDao.findById(Integer.parseInt(seatId));
            seatList.add(seat);
        }
        return seatList;
    }

    private BigDecimal getTotalCost(final List<Ticket> ticketList) {
        if (ticketList.isEmpty()) {
            logger.error("seatList is Empty, can't count total cost");
            throw new NullPointerException("seatList is Empty, can't count total cost");
        }
        BigDecimal totalCost = new BigDecimal(0);
        for (Ticket ticket : ticketList) {
            totalCost = totalCost.add(ticket.getTicketPrice());
        }
        return totalCost;
    }
}
