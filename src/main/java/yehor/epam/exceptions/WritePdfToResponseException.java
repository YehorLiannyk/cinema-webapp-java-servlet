package yehor.epam.exceptions;

/**
 * PDF exception class
 */
public class WritePdfToResponseException extends RuntimeException {
    public WritePdfToResponseException(String message) {
        super(message);
    }

    public WritePdfToResponseException(String message, Throwable cause) {
        super(message, cause);
    }

}
