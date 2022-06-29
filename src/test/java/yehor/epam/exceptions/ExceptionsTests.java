package yehor.epam.exceptions;

import org.junit.Test;
import yehor.epam.exceptions.TicketException;
import yehor.epam.exceptions.ConnectionException;
import yehor.epam.exceptions.AuthException;
import yehor.epam.exceptions.DaoException;
import yehor.epam.exceptions.RegisterException;
import yehor.epam.exceptions.VerifyException;
import yehor.epam.exceptions.PdfException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ExceptionsTests {
    @Test
    public void ticketExceptionTest() {
        final String msg = "msg";
        TicketException ticketException = new TicketException(msg);
        assertEquals(msg, ticketException.getMessage());

        final Throwable cause = mock(Throwable.class);
        ticketException = new TicketException(msg, cause);
        assertEquals(msg, ticketException.getMessage());
        assertEquals(cause, ticketException.getCause());
    }

    @Test
    public void connectionExceptionTest() {
        final String msg = "msg";
        ConnectionException connectionException = new ConnectionException(msg);
        assertEquals(msg, connectionException.getMessage());

        final Throwable cause = mock(Throwable.class);
        connectionException = new ConnectionException();
        assertEquals("ConnectionException was occurred", connectionException.getMessage());
    }

    @Test
    public void authExceptionTest() {
        final String msg = "msg";
        AuthException authException = new AuthException(msg);
        assertEquals(msg, authException.getMessage());

        final Throwable cause = mock(Throwable.class);
        authException = new AuthException(msg, cause);
        assertEquals(msg, authException.getMessage());
        assertEquals(cause, authException.getCause());
    }

    @Test
    public void daoExceptionTest() {
        final String msg = "msg";
        DaoException daoException = new DaoException(msg);
        assertEquals(msg, daoException.getMessage());

        final Throwable cause = mock(Throwable.class);
        daoException = new DaoException(msg, cause);
        assertEquals(msg, daoException.getMessage());
        assertEquals(cause, daoException.getCause());
    }

    @Test
    public void registerExceptionTest() {
        final String msg = "msg";
        RegisterException registerException = new RegisterException(msg);
        assertEquals(msg, registerException.getMessage());

        final Throwable cause = mock(Throwable.class);
        registerException = new RegisterException(msg, cause);
        assertEquals(msg, registerException.getMessage());
        assertEquals(cause, registerException.getCause());
    }

    @Test
    public void verifyExceptionTest() {
        final String msg = "msg";
        VerifyException verifyException = new VerifyException(msg);
        assertEquals(msg, verifyException.getMessage());

        final Throwable cause = mock(Throwable.class);
        verifyException = new VerifyException(msg, cause);
        assertEquals(msg, verifyException.getMessage());
        assertEquals(cause, verifyException.getCause());
    }

    @Test
    public void pdfExceptionTest() {
        final String msg = "msg";
        PdfException pdfException = new PdfException(msg);
        assertEquals(msg, pdfException.getMessage());

        final Throwable cause = mock(Throwable.class);
        pdfException = new PdfException(msg, cause);
        assertEquals(msg, pdfException.getMessage());
        assertEquals(cause, pdfException.getCause());
    }
}
