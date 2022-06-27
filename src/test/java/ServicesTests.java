import jakarta.mail.MessagingException;
import org.junit.Test;
import yehor.epam.entities.Ticket;
import yehor.epam.services.impl.MailService;
import yehor.epam.utilities.PassEncryptionManager;
import yehor.epam.services.impl.TicketPdfServiceImpl;
import yehor.epam.exceptions.PdfException;

import java.io.ByteArrayOutputStream;

import static org.mockito.Mockito.*;

public class ServicesTests {

    @Test
    public void passEncryptionManagerTest() {
        PassEncryptionManager manager = mock(PassEncryptionManager.class);
        when(manager.generateSecurePassword("pass", "salt")).thenReturn("encryptPass");
        when(manager.getSaltValue(10)).thenReturn("saltValue");
        when(manager.verifyUserPassword("pass", "encryptPass", "salt")).thenReturn(true);
        when(manager.verifyUserPassword("pass", "encryptPass", "salt")).thenReturn(false);
        when(manager.hash(new char[0], new byte[0])).thenReturn(new byte[0]);
        when(manager.hash(new char[0], new byte[0])).thenThrow(AssertionError.class);

        manager = spy(PassEncryptionManager.class);
        manager.generateSecurePassword("pass", "salt");
        verify(manager, times(1)).generateSecurePassword("pass", "salt");
        manager.getSaltValue(10);
        verify(manager, times(1)).getSaltValue(10);
        manager.verifyUserPassword("pass", "encryptPass", "salt");
        verify(manager, times(1)).verifyUserPassword("pass", "encryptPass", "salt");
        final byte[] salt = new byte[1];
        final char[] password = new char[1];
        manager.hash(password, salt);
        verify(manager, times(1)).hash(password, salt);
    }

    @Test
    public void ticketServiceTest() {
        TicketPdfServiceImpl ticketService = mock(TicketPdfServiceImpl.class);
        final Ticket ticket = mock(Ticket.class);
        when(ticketService.formPDFTicket(ticket)).thenReturn(new ByteArrayOutputStream());
        when(ticketService.formPDFTicket(ticket)).thenThrow(PdfException.class);
        doThrow(PdfException.class).when(ticketService).formPDFTicket(ticket);
    }

    @Test
    public void mailServiceTest() throws MessagingException {
        MailService mailService = spy(MailService.class);
        mailService.sendEmail("", "", "", "", "", "", "");
        verify(mailService, times(1)).sendEmail("", "", "", "", "", "", "");
        doThrow(MessagingException.class).when(mailService).sendEmail("", "", "", "", "", "", "");
    }

}
