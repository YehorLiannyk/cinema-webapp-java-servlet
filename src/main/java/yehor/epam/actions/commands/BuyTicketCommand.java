package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.actions.commands.exceptions.TicketException;
import yehor.epam.dao.SeatDAO;
import yehor.epam.dao.TicketDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Ticket;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.JspPagePathConstants.SUCCESS_PAID_PAGE_PATH;

public class BuyTicketCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(BuyTicketCommand.class);
    private String CLASS_NAME = BuyTicketCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + CLASS_NAME + " execute command");
            logger.debug("request.getAttribute(\"ticketList\") = " + request.getSession().getAttribute("ticketList").toString());
            List<Ticket> ticketList = (List<Ticket>) request.getSession().getAttribute("ticketList");

            final SeatDAO seatDao = factory.getSeatDao();
            final TicketDAO ticketDao = factory.getTicketDao();
            for (Ticket ticket : ticketList) {
                if (!seatDao.isSeatReserved(ticket.getSeat().getId(), ticket.getSession().getId())) {
                    ticketDao.insert(ticket);
                } else {
                    logger.warn("Seat is already reserved");
                    throw new TicketException("Seat is already reserved, choose another one");
                }
            }
            request.getRequestDispatcher(SUCCESS_PAID_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    private List<Seat> getReservedSeats(DAOFactory factory, int sessionId) {
        final SeatDAO seatDAO = factory.getSeatDao();
        return seatDAO.findAllReservedBySession(sessionId);
    }
}
