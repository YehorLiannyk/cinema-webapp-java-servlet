package yehor.epam.utilities.exception;

/**
 * PDF exception class
 */
public class PDFException extends RuntimeException {
    public PDFException(String message) {
        super(message);
    }

    public PDFException(String message, Throwable cause) {
        super(message, cause);
    }

    public PDFException(Throwable cause) {
        super(cause);
    }
}
