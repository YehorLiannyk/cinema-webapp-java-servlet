package yehor.epam.services.impl;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import yehor.epam.entities.Film;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.entities.Ticket;
import yehor.epam.exceptions.PdfException;
import yehor.epam.services.TicketPdfService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class TicketPdfServiceImplTest {
    private final TicketPdfService ticketPdfService = spy(TicketPdfServiceImpl.class);

    @Test
    void formPDFTicket() {
        Ticket ticket = mock(Ticket.class);
        Session session = mock(Session.class);
        Film film = mock(Film.class);
        Seat seat = mock(Seat.class);
        Locale locale = Locale.getDefault();
        BigDecimal price = BigDecimal.TEN;

        when(ticket.getSession()).thenReturn(session);
        when(session.getFilm()).thenReturn(film);
        when(ticket.getSeat()).thenReturn(seat);
        when(ticket.getTicketPrice()).thenReturn(price);
        when(session.getDate()).thenReturn(LocalDate.now());
        when(session.getTime()).thenReturn(LocalTime.now());
        final ByteArrayOutputStream stream = ticketPdfService.formPDFTicket(ticket, locale);
        Assertions.assertNotNull(stream);
    }

    @Test
    void writePdfToResponse() throws IOException {
        ByteArrayOutputStream stream = mock(ByteArrayOutputStream.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        doNothing().when(stream).writeTo(servletOutputStream);
        ticketPdfService.writePdfToResponse(stream, response);
        verify(stream).writeTo(servletOutputStream);
        verify(stream).flush();
        verify(stream).close();
    }

    @Test
    void writePdfToResponseThrowPdfException() throws IOException {
        ByteArrayOutputStream stream = mock(ByteArrayOutputStream.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        doThrow(IOException.class).when(stream).writeTo(servletOutputStream);
        try {
            ticketPdfService.writePdfToResponse(stream, response);
            fail("PdfException should be thrown");
        } catch (PdfException e) {
        }
        verify(stream).flush();
        verify(stream).close();
    }
}