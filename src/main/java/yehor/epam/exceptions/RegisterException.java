package yehor.epam.exceptions;

/**
 * Exception thrown when register is unsuccessful. Common reason: this email is used already
 */
public class RegisterException extends RuntimeException {
    public RegisterException(String message) {
        super(message);
    }

    public RegisterException(String message, Throwable cause) {
        super(message, cause);
    }
}
