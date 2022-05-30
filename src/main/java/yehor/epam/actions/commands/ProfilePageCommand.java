package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.TicketDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Ticket;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.JspPagePathConstants.USER_PROFILE_PAGE_PATH;
import static yehor.epam.utilities.OtherConstants.USER_ID;

/**
 * Command to show User Profile page
 */
public class ProfilePageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(ProfilePageCommand.class);
    private static final String SIMPLE_NAME = ProfilePageCommand.class.getSimpleName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.info("Created DAOFactory in " + SIMPLE_NAME + " execute command");
            final int userId = Integer.parseInt(request.getSession().getAttribute(USER_ID).toString());
            final TicketDAO ticketDao = factory.getTicketDao();
            final List<Ticket> ticketList = ticketDao.findAllByUserId(userId);
            request.setAttribute("ticketList", ticketList);
            request.getRequestDispatcher(USER_PROFILE_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, SIMPLE_NAME, e);
        }
    }
}
