package yehor.epam.dao.mysql;

import org.apache.log4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.exception.DAOException;
import yehor.epam.entities.Film;
import yehor.epam.entities.Session;
import yehor.epam.utilities.LoggerManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLSessionDAO extends BaseDAO implements SessionDAO {
    private static final Logger logger = LoggerManager.getLogger(MySQLSessionDAO.class);
    private final String SELECT_ALL = "SELECT * FROM sessions s JOIN films f on s.film_id = f.film_id";

    @Override
    public boolean insert(Session element) {
        return false;
    }

    @Override
    public Session findById(int id) {
        return null;
    }

    @Override
    public List<Session> findAll() {
        List<Session> sessions = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                final Session session = getSessionFromResultSet(resultSet);
                sessions.add(session);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get list of all sessions from DB", e);
            throw new DAOException("Couldn't get list of all sessions from DB");
        }
        return sessions;
    }

    @Override
    public Session update(Session element) {
        return null;
    }

    @Override
    public boolean delete(Session element) {
        return false;
    }

    private Session getSessionFromResultSet(ResultSet rs) {
        Session session = null;
        try {
            session = new Session(
                    rs.getInt("session_id"),
                    rs.getBigDecimal("ticket_price"),
                    rs.getDate("date_time").toLocalDate(),
                    rs.getTime("date_time").toLocalTime()
            );
            final Film film = getFilmById(rs.getInt("film_id"));
            session.setFilm(film);
        } catch (SQLException e) {
            logger.error("Couldn't get session from ResultSet", e);
            throw new DAOException("Couldn't get session from ResultSet");
        }
        return session;
    }

    private Film getFilmById(int filmId) {
        final MySQLFilmDAO mySQLFilmDAO = new MySQLFilmDAO();
        mySQLFilmDAO.setConnection(getConnection());
        return mySQLFilmDAO.findById(filmId);
    }
}
