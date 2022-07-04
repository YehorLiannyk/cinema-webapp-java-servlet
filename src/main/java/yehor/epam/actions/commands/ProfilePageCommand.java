package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Ticket;
import yehor.epam.services.PaginationService;
import yehor.epam.services.TicketService;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.impl.PaginationServiceImpl;
import yehor.epam.services.impl.TicketServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.JspPagePathConstants.USER_PROFILE_PAGE_PATH;
import static yehor.epam.utilities.constants.OtherConstants.*;

/**
 * Command to show User Profile page
 */
public class ProfilePageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(ProfilePageCommand.class);
    private static final String CLASS_NAME = ProfilePageCommand.class.getName();
    private final TicketService ticketService;
    private final PaginationService paginationService;

    public ProfilePageCommand() {
        ticketService = new TicketServiceImpl();
        paginationService = new PaginationServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final Map<String, Integer> paginationMap = paginationService.getPaginationParamsFromRequest(request);
            int page = paginationMap.get(PAGE_NO_PARAM);
            int size = paginationMap.get(PAGE_SIZE_PARAM);
            final int userId = Integer.parseInt(request.getSession().getAttribute(USER_ID).toString());
            final List<Ticket> ticketList = ticketService.getAllByUserId(userId, page, size);
            final int totalPages = ticketService.countTotalPagesByUserId(userId, size);

            request.setAttribute("totalPages", totalPages);
            request.setAttribute("ticketList", ticketList);
            request.getRequestDispatcher(USER_PROFILE_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
