package yehor.epam.actions.commands.exceptions;

public class TicketException extends RuntimeException {
    public TicketException(String message) {
        super(message);
    }

    public TicketException(String message, Throwable cause) {
        super(message, cause);
    }
}
