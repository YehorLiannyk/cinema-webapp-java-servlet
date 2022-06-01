package yehor.epam.services.exceptions;

/**
 * Google Recaptcha verify exception
 */
public class VerifyException extends RuntimeException {
    public VerifyException(String message) {
        super(message);
    }

    public VerifyException(String message, Throwable cause) {
        super(message, cause);
    }

}
