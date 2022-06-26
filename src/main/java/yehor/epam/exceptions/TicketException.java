package yehor.epam.exceptions;

/**
 * Ticket Exception Class. Occurred while buying ticket, in particular when seat was reserved by another one
 * while this user waits on buy ticket page
 */
public class TicketException extends RuntimeException {
    public TicketException(String message) {
        super(message);
    }

    public TicketException(String message, Throwable cause) {
        super(message, cause);
    }
}
