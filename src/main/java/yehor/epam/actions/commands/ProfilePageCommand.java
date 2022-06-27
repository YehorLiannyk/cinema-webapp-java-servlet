package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.TicketDao;
import yehor.epam.entities.Ticket;
import yehor.epam.services.TicketService;
import yehor.epam.services.impl.ErrorServiceImpl;
import yehor.epam.services.impl.TicketServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.constants.JspPagePathConstants.USER_PROFILE_PAGE_PATH;
import static yehor.epam.utilities.constants.OtherConstants.USER_ID;

/**
 * Command to show User Profile page
 */
public class ProfilePageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(ProfilePageCommand.class);
    private static final String CLASS_NAME = ProfilePageCommand.class.getName();
    private final TicketService ticketService;

    public ProfilePageCommand() {
        ticketService = new TicketServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final int userId = Integer.parseInt(request.getSession().getAttribute(USER_ID).toString());
            final List<Ticket> ticketList = ticketService.findAllByUserId(userId);
            request.setAttribute("ticketList", ticketList);
            request.getRequestDispatcher(USER_PROFILE_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorServiceImpl.handleException(request, response, CLASS_NAME, e);
        }
    }
}
