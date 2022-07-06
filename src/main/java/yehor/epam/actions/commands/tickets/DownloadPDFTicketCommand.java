package yehor.epam.actions.commands.tickets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.jstl.core.Config;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Ticket;
import yehor.epam.services.TicketPdfService;
import yehor.epam.services.TicketService;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.impl.TicketPdfServiceImpl;
import yehor.epam.services.impl.TicketServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

/**
 * Command to download Ticket in PDF format
 */
public class DownloadPDFTicketCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(DownloadPDFTicketCommand.class);
    private static final String CLASS_NAME = DownloadPDFTicketCommand.class.getName();
    private final TicketService ticketService;
    private final TicketPdfService ticketPdfService;

    public DownloadPDFTicketCommand() {
        ticketService = new TicketServiceImpl();
        ticketPdfService = new TicketPdfServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            int ticketId = Integer.parseInt(request.getParameter("ticketId"));
            final Ticket ticket = ticketService.getById(ticketId);
            final Locale locale = (Locale) Config.get(request.getSession(), Config.FMT_LOCALE);
            final ByteArrayOutputStream stream = ticketPdfService.formPDFTicket(ticket, locale);
            ticketPdfService.writePdfToResponse(stream, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
