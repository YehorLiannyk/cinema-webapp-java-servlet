package yehor.epam.actions.commands.mail;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Ticket;
import yehor.epam.entities.User;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.OtherConstants.USER_ID;

public class SendTicketViaMailCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(SendTicketViaMailCommand.class);
    private static final String CLASS_NAME = SendTicketViaMailCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()){
            logger.info("Entry to " + CLASS_NAME + " execute command");

            logger.debug("ticketID: request.getParameter(\"ticketId\")");
            int ticketId = Integer.parseInt(request.getParameter("ticketId"));
            final Ticket ticket = factory.getTicketDao().findById(ticketId);
            logger.debug("ticket: " + ticket);

            final int userId = ticket.getUser().getId();
            logger.debug("userId: " + userId);
            final User user = factory.getUserDao().findById(userId);

            request.setAttribute("ticket", ticket);
            request.setAttribute("mailSubject", "Your ticket");
            request.setAttribute("user", user);

            new SendMailCommand().execute(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
