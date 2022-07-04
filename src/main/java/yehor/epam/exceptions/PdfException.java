package yehor.epam.exceptions;

/**
 * PDF exception class
 */
public class PdfException extends RuntimeException {
    public PdfException(String message) {
        super(message);
    }

    public PdfException(String message, Throwable cause) {
        super(message, cause);
    }

}
