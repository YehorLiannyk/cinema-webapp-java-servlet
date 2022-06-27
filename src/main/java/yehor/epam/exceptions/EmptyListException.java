package yehor.epam.exceptions;


public class EmptyListException extends RuntimeException {
    public EmptyListException(String message) {
        super(message);
    }

    public EmptyListException(String message, Throwable cause) {
        super(message, cause);
    }

}
