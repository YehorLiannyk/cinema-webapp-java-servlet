package yehor.epam.dao.mysql;

import org.apache.log4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.exception.DAOException;
import yehor.epam.entities.Film;
import yehor.epam.entities.Session;
import yehor.epam.utilities.LoggerManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MySQLSessionDAO extends BaseDAO implements SessionDAO {
    private static final Logger logger = LoggerManager.getLogger(MySQLSessionDAO.class);
    private static final String SELECT_ALL = "SELECT * FROM sessions s JOIN films f on s.film_id = f.film_id";
    private static final String INSERT = "INSERT INTO sessions VALUES (session_id, ?,?,?,?)";

    @Override
    public boolean insert(Session session) {
        boolean inserted = false;
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            setSessionToStatement(session, statement);
            final int row = statement.executeUpdate();
            if (row > 1) throw new DAOException("More than one row was inserted to DB");
            inserted = true;
        } catch (SQLException e) {
            logger.error("Couldn't insert Session to DB", e);
            throw new DAOException("Couldn't insert Session to DB");
        }
        return inserted;
    }

    private void setSessionToStatement(Session session, PreparedStatement statement) throws SQLException {
        try {
            statement.setInt(1, session.getFilm().getId());
            final LocalDate date = session.getDate();
            final LocalTime time = session.getTime();
            statement.setDate(2, Date.valueOf(date));
            statement.setTime(3, Time.valueOf(time));
            statement.setBigDecimal(4, session.getTicketPrice());
        } catch (SQLException e) {
            logger.error("Couldn't set session to Statement", e);
            throw new SQLException("Couldn't set session to Statement", e);
        }
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
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("time").toLocalTime()
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
