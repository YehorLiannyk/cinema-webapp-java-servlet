package yehor.epam.dao.factories;

import org.slf4j.Logger;
import yehor.epam.exceptions.ConnectionException;
import yehor.epam.connection.ConnectionPool;
import yehor.epam.dao.*;
import yehor.epam.dao.mysql.*;
import yehor.epam.utilities.LoggerManager;

import java.sql.Connection;

/**
 * MySQL Factory from AbstractFactory pattern.
 * Create DAOs
 */
public class MySQLFactory implements DAOFactory {
    private static final Logger logger = LoggerManager.getLogger(MySQLFactory.class);
    private Connection connection;

    public MySQLFactory() {
        try {
            this.connection = ConnectionPool.getInstance().getConnection();
            logger.debug("Set connection to MySQLFactory via constructor");
        } catch (ConnectionException e) {
            logger.error("Can't get connection in MySQLFactory constructor", e);
        }
    }


    @Override
    public UserDAO getUserDao() {
        final MySQLUserDAO mySQLUserDAO = new MySQLUserDAO();
        mySQLUserDAO.setConnection(connection);
        return mySQLUserDAO;
    }

    @Override
    public FilmDao getFilmDAO() {
        final MySQLFilmDao mySQLFilmDAO = new MySQLFilmDao();
        mySQLFilmDAO.setConnection(connection);
        return mySQLFilmDAO;
    }

    @Override
    public GenreDAO getGenreDAO() {
        final MySQLGenreDAO mySQLGenreDAO = new MySQLGenreDAO();
        mySQLGenreDAO.setConnection(connection);
        return mySQLGenreDAO;
    }

    @Override
    public SeatDAO getSeatDao() {
        final MySQLSeatDAO mySQLSeatDAO = new MySQLSeatDAO();
        mySQLSeatDAO.setConnection(connection);
        return mySQLSeatDAO;
    }

    @Override
    public SessionDao getSessionDao() {
        MySQLSessionDao mySQLSessionDAO = new MySQLSessionDao();
        mySQLSessionDAO.setConnection(connection);
        return mySQLSessionDAO;
    }

    @Override
    public TicketDao getTicketDao() {
        final MySQLTicketDao mySQLTicketDAO = new MySQLTicketDao();
        mySQLTicketDAO.setConnection(connection);
        return mySQLTicketDAO;
    }

    @Override
    public void close() throws Exception {
        if (!connection.isClosed()) connection.close();
    }

}
