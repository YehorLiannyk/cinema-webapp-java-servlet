package yehor.epam.actions.commands.tickets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.entities.Ticket;
import yehor.epam.entities.User;
import yehor.epam.services.SeatService;
import yehor.epam.services.SessionService;
import yehor.epam.services.TicketService;
import yehor.epam.services.UserService;
import yehor.epam.services.impl.*;
import yehor.epam.utilities.LoggerManager;

import java.math.BigDecimal;
import java.util.List;

import static yehor.epam.utilities.constants.JspPagePathConstants.PAYING_PAGE_PATH;
import static yehor.epam.utilities.constants.OtherConstants.USER_ID;

/**
 * Command show page of chosen tickets
 */
public class BuyTicketPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(BuyTicketPageCommand.class);
    private static final String CLASS_NAME = BuyTicketPageCommand.class.getName();
    private final TicketService ticketService;
    private final SessionService sessionService;
    private final UserService userService;
    private final SeatService seatService;

    public BuyTicketPageCommand() {
        ticketService = new TicketServiceImpl();
        sessionService = new SessionServiceImpl();
        userService = new UserServiceImpl();
        seatService = new SeatServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final int sessionId = Integer.parseInt(request.getParameter("sessionId"));
            final int userId = Integer.parseInt(request.getSession().getAttribute(USER_ID).toString());
            final String[] seatIds = request.getParameterMap().get("seatIds");

            final Session session = sessionService.getById(sessionId);
            final User user = userService.getById(userId);
            final List<Seat> seatList = seatService.getSeatListByIdArray(seatIds);
            final List<Ticket> ticketList = ticketService.formTicketList(session, seatList, user);
            final BigDecimal totalCost = ticketService.countTotalCostOfTicketList(ticketList);

            request.getSession().setAttribute("ticketList", ticketList);
            request.setAttribute("session", session);
            request.setAttribute("totalCost", totalCost);

            request.getRequestDispatcher(PAYING_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
