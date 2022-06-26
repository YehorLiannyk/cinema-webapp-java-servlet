package yehor.epam.exceptions;

/**
 * Exception user in DAO classes. Thrown when got trouble getting the T object from Database
 */
public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
