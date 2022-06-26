package yehor.epam.exceptions;


public class EmptyArrayException extends RuntimeException {
    public EmptyArrayException(String message) {
        super(message);
    }

    public EmptyArrayException(String message, Throwable cause) {
        super(message, cause);
    }

}
