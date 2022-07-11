package yehor.epam.services;

import yehor.epam.entities.Ticket;

import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Locale;

public interface TicketPdfService {
    /**
     * create ByteArrayOutputStream from ticket object and form it to PDF table
     *
     * @param ticket Ticket object
     * @return ByteArrayOutputStream
     */
    ByteArrayOutputStream formPDFTicket(Ticket ticket, Locale locale);

    /**
     * Write ByteArrayOutputStream to HttpServletResponse
     * @param byteArrayOutputStream PDF file in ByteArrayOutputStream
     * @param response HttpServletResponse
     */
    void writePdfToResponse(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response);
}
