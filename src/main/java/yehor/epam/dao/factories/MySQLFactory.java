package yehor.epam.dao.factories;

import org.apache.log4j.Logger;
import yehor.epam.connection.ConnectionException;
import yehor.epam.connection.ConnectionPool;
import yehor.epam.dao.FilmDAO;
import yehor.epam.dao.GenreDAO;
import yehor.epam.dao.mysql.MySQLFilmDAO;
import yehor.epam.dao.mysql.MySQLGenreDAO;
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
    public FilmDAO getFilmDAO() {
        final MySQLFilmDAO mySQLFilmDAO = new MySQLFilmDAO();
        mySQLFilmDAO.setConnection(connection);
        return mySQLFilmDAO;
    }

    @Override
    public GenreDAO getGenreDAO() {
        return new MySQLGenreDAO();
    }

    @Override
    public void close() throws Exception {
        if (!connection.isClosed()) connection.close();
    }
}
