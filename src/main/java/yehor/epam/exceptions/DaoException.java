package yehor.epam.exceptions;

/**
 * Exception is used in DAO classes. Thrown when got trouble getting the T object from Database
 */
public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
