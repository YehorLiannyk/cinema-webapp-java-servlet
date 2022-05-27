package yehor.epam.dao.factories;

import org.apache.log4j.Logger;
import yehor.epam.connection.ConnectionException;
import yehor.epam.connection.ConnectionPool;
import yehor.epam.dao.*;
import yehor.epam.dao.mysql.*;
import yehor.epam.utilities.LoggerManager;

import java.sql.Connection;

public class MySQLFactory implements DAOFactory {
    private static final Logger logger = LoggerManager.getLogger(MySQLFactory.class);
    Connection connection;

    public MySQLFactory() {
        try {
            this.connection = ConnectionPool.getInstance().getConnection();
            logger.debug("Set connection to MySQLFactory via constructor");
        } catch (ConnectionException e) {
            logger.error("Can't get connection in MySQLFactory constructor", e);
        }
    }

    @Override
    public BasketDAO getBasketDao() {
        return null;
    }

    @Override
    public UserDAO getUserDao() {
        final MySQLUserDAO mySQLUserDAO = new MySQLUserDAO();
        mySQLUserDAO.setConnection(connection);
        return mySQLUserDAO;
    }

    @Override
    public FilmDAO getFilmDAO() {
        final MySQLFilmDAO mySQLFilmDAO = new MySQLFilmDAO();
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
    public SessionDAO getSessionDao() {
        final MySQLSessionDAO mySQLSessionDAO = new MySQLSessionDAO();
        mySQLSessionDAO.setConnection(connection);
        return mySQLSessionDAO;
    }

    @Override
    public TicketDAO getTicketDao() {
        final MySQLTicketDAO mySQLTicketDAO = new MySQLTicketDAO();
        mySQLTicketDAO.setConnection(connection);
        return mySQLTicketDAO;
    }

    @Override
    public void close() throws Exception {
        if (!connection.isClosed()) connection.close();
    }

}
