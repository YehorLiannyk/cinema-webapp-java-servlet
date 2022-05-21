package yehor.epam.connection;

public class ConnectionException extends Exception{
    public ConnectionException() {
        this("ConnectionException was occurred");
    }

    public ConnectionException(String message) {
        super(message);
    }
}
