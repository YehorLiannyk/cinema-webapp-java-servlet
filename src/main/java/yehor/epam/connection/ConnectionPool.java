package yehor.epam.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import yehor.epam.exceptions.ConnectionException;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ConnectionPoll implemented with  BasicDataSource
 */
public class ConnectionPool {
    private static final Logger logger = LoggerManager.getLogger(ConnectionPool.class);

    private static final String PROPERTIES_FILE = "/db.properties";
    private static final BasicDataSource ds = new BasicDataSource();

    /**
     * Driver class name
     */
    private String driverClassName;
    /**
     * DB user
     */
    private String user;
    /**
     * DB user password
     */
    private String password;
    /**
     * DB url
     */
    private String url;
    /**
     * The minimum number of established connections that should be kept in the pool
     */
    private int minIdle;
    /**
     * The maximum number of established connections
     */
    private int maxIdle;
    /**
     * The maximum number of milliseconds that the pool will wait
     */
    private long maxWait;

    private ConnectionPool() {
        init();
        logger.info("Connection pool was created");
    }

    private static final class ConnectionPoolHolder {
        private static final ConnectionPool connectionPool = new ConnectionPool();
    }

    /**
     * Get Connection poll
     *
     * @return ConnectionPool
     */
    public static ConnectionPool getInstance() {
        logger.debug("ConnectionPool.getInstance() was called");
        return ConnectionPoolHolder.connectionPool;
    }

    /**
     * Get connection from pool
     *
     * @return Connection
     * @throws ConnectionException
     */
    public Connection getConnection() throws ConnectionException {
        logger.debug("ConnectionPool.getConnection() was called");
        Connection connection = null;
        try {
            connection = ds.getConnection();
            logger.debug("Connection from ConnectionPool was received");
        } catch (SQLException e) {
            logger.error("Can't get connection from connection pool", e);
            throw new ConnectionException("Can't get connection from connection pool");
        }
        return connection;
    }

    /**
     * Load properties
     */
    private void loadProperties() {
        Properties properties = getProperties();

        driverClassName = properties.getProperty("driverClassName");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
        minIdle = Integer.parseInt(properties.getProperty("minIdle"));
        maxIdle = Integer.parseInt(properties.getProperty("maxIdle"));
        maxWait = Long.parseLong(properties.getProperty("maxWait"));
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(ConnectionPool.class.getResourceAsStream(PROPERTIES_FILE));
        } catch (IOException e) {
            logger.error("Can't load properties file for Database", e);
        }
        return properties;
    }

    private void init() {
        loadProperties();

        ds.setDriverClassName(driverClassName);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setUrl(url);
        ds.setMinIdle(minIdle);
        ds.setMaxIdle(maxIdle);
        ds.setMaxWaitMillis(maxWait);
    }

}
