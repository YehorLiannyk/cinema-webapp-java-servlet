package yehor.epam.services;

import yehor.epam.entities.Ticket;

import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

public interface TicketPdfService {
    /**
     * create ByteArrayOutputStream from ticket object and form it to PDF table
     *
     * @param ticket Ticket object
     * @return ByteArrayOutputStream
     */
    ByteArrayOutputStream formPDFTicket(Ticket ticket);

    void writePdfToResponse(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response);
}
