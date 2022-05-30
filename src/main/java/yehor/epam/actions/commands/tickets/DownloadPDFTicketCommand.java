package yehor.epam.actions.commands.tickets;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Ticket;
import yehor.epam.services.ErrorService;
import yehor.epam.services.TicketService;
import yehor.epam.utilities.LoggerManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Command to download Ticket in PDF format
 */
public class DownloadPDFTicketCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(DownloadPDFTicketCommand.class);
    private static final String CLASS_NAME = DownloadPDFTicketCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.info("Created DAOFactory in " + CLASS_NAME + " execute command");
            response.setContentType("application/pdf;charset=UTF-8");
            response.addHeader("Content-Disposition", "inline; filename=" + "ticket.pdf");
            int ticketId = Integer.parseInt(request.getParameter("ticketId"));
            final Ticket ticket = factory.getTicketDao().findById(ticketId);
            formAndWritePDF(request, response, ticket);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Call form PDF method and then write received ByteArrayOutputStream to servletOutputStream
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param ticket   Ticket for PDF
     * @throws IOException
     */
    private void formAndWritePDF(HttpServletRequest request, HttpServletResponse response, Ticket ticket) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            TicketService ticketService = new TicketService();
            byteArrayOutputStream = ticketService.formPDFTicket(ticket);
            final ServletOutputStream servletOutputStream = getServletOutputStream(request, response);
            byteArrayOutputStream.writeTo(servletOutputStream);
        } catch (Exception e) {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            }
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Return ServletOutputStream if everything is fine or close it and forward to ErrorPage
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ServletOutputStream if no exception is thrown
     * @throws IOException
     */
    private ServletOutputStream getServletOutputStream(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
        } catch (Exception e) {
            if (servletOutputStream != null) {
                servletOutputStream.flush();
                servletOutputStream.close();
            }
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
        return servletOutputStream;
    }
}
