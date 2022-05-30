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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.JspPagePathConstants.PAYING_PAGE_PATH;
import static yehor.epam.utilities.OtherConstants.USER_ID;

/**
 * Command show page of chosen tickets
 */
public class BuyTicketPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(BuyTicketPageCommand.class);
    private static final String CLASS_NAME = BuyTicketPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.info("Created DAOFactory in " + CLASS_NAME + " execute command");

            final int sessionId = Integer.parseInt(request.getParameter("sessionId"));
            final Session session = getSession(factory, sessionId);
            request.setAttribute("session", session);

            final int userId = Integer.parseInt(request.getSession().getAttribute(USER_ID).toString());
            final User user = getUser(factory, userId);

            final List<Seat> seatListFromRequest = getSeatListFromRequest(request, factory);
            final List<Ticket> ticketList = getTicketList(session, seatListFromRequest, user);
            request.getSession().setAttribute("ticketList", ticketList);

            final BigDecimal totalCost = getTotalCost(ticketList);
            request.setAttribute("totalCost", totalCost);

            request.getRequestDispatcher(PAYING_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Form Ticket List for chosen Session and Seats
     *
     * @param session  chosen Session
     * @param seatList chosen Seats
     * @param user     other-words customer
     * @return formed TicketList or throw an exception if no tickets added
     */
    private List<Ticket> getTicketList(Session session, List<Seat> seatList, User user) {
        final List<Ticket> ticketList = new ArrayList<>();
        for (Seat seat : seatList) {
            final Ticket ticket = new Ticket(session, user, seat, session.getTicketPrice());
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

    /**
     * Form SeatList from request
     *
     * @param request HttpServletRequest
     * @param factory DAOFactory
     * @return formed SeatList or throw an exception if no Seats chosen
     */
    private List<Seat> getSeatListFromRequest(HttpServletRequest request, DAOFactory factory) {
        List<Seat> seatList = new ArrayList<>();
        final Map<String, String[]> parameterMap = request.getParameterMap();
        final String[] seatIds = parameterMap.get("seatIds");
        final SeatDAO seatDao = factory.getSeatDao();
        if (seatIds == null || seatIds.length == 0) {
            logger.error("Seat Array is null or empty");
            throw new NullPointerException("You did not choose any seat");
        }
        for (String seatId : seatIds) {
            final Seat seat = seatDao.findById(Integer.parseInt(seatId));
            seatList.add(seat);
        }
        return seatList;
    }

    /**
     * Count total cost of all tickets
     *
     * @param ticketList chosen tickets
     * @return total cost
     */
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
