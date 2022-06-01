package yehor.epam.dao;

import java.sql.Connection;

/**
 * Base of DAO class stored the connection
 */
public abstract class BaseDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public BaseDAO() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
