package yehor.epam.exceptions;

/**
 * Exception occurred when couldn't write ByteArrayOutputStream to HttpResponse
 */
public class WritePdfToResponseException extends RuntimeException {
    public WritePdfToResponseException(String message) {
        super(message);
    }

    public WritePdfToResponseException(String message, Throwable cause) {
        super(message, cause);
    }

}
