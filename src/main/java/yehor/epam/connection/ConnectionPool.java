package yehor.epam.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static final Logger logger = LoggerManager.getLogger(ConnectionPool.class);

    private static final String PROPERTIES_FILE = "/Database.properties";
    private static final BasicDataSource ds = new BasicDataSource();

    private static ConnectionPool connectionPool;

    private String driverClassName;
    private String user;
    private String password;
    private String url;
    private int minIdle;
    private int maxIdle;
    private long maxWait;

    private ConnectionPool() {
        init();
        logger.debug("Connection pool was created");
    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            synchronized (ConnectionPool.class) {
                if (connectionPool == null) {
                    connectionPool = new ConnectionPool();
                }
            }
        }
        logger.debug("ConnectionPool.getInstance() was called");
        return connectionPool;
    }

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
