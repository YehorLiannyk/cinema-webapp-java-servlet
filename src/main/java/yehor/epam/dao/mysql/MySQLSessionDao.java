package yehor.epam.dao.mysql;

import org.slf4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.SessionDao;
import yehor.epam.entities.Film;
import yehor.epam.entities.Session;
import yehor.epam.exceptions.DaoException;
import yehor.epam.utilities.LoggerManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.OtherConstants.*;

public class MySQLSessionDao extends BaseDAO implements SessionDao {
    private static final Logger logger = LoggerManager.getLogger(MySQLSessionDao.class);
    private static final String INSERT = "INSERT INTO sessions VALUES (session_id, ?,?,?,?,?)";
    private static final String DECREMENT_FREE_SEATS = "UPDATE sessions SET free_seats = free_seats - 1 WHERE session_id=? AND free_seats > 0";
    private static final String SELECT_ALL = "SELECT * FROM sessions s JOIN films f on s.film_id = f.film_id";
    private static final String SELECT_BY_ID = "SELECT * FROM sessions s JOIN films f on s.film_id = f.film_id WHERE s.session_id=?";
    private static final String SELECT_FREE_SEATS_BY_ID = "SELECT free_seats FROM sessions WHERE session_id=?";
    private static final String DELETE_BY_SESSION_ID = "DELETE FROM sessions WHERE session_id=?";

    private static final String WHERE_DEFAULT = " WHERE s.date>=? AND IF (s.date=?, s.time>=?, s.time>=?)";
    private static final String AND_FREE_SEATS = " AND s.free_seats>0";
    private static final String ORDER_BY_DATETIME_ASC = " ORDER BY s.date, s.time";
    private static final String ORDER_BY_DATETIME_DESC = " ORDER BY s.date DESC, s.time DESC";
    private static final String ORDER_BY_FILM_NAME = " ORDER BY f.film_name ";
    private static final String ORDER_BY_FREE_SEATS = " ORDER BY s.free_seats";
    private static final String DESCENDING = " DESC";
    private static final String COUNT_TOTAL_ROWS = "SELECT COUNT(*) FROM sessions";
    private static final String LIMIT = " LIMIT ?, ?";

    @Override
    public boolean insert(Session session) throws DaoException {
        boolean inserted = false;
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            setSessionToInsertStatement(session, statement);
            statement.executeUpdate();
            final MySQLSeatDao seatDAO = getSeatDAO();
            final int sessionId = getLastGeneratedKey(statement);
            session.setId(sessionId);
            seatDAO.insertFreeSeatsForSession(session);
            inserted = true;
        } catch (SQLException e) {
            logger.error("Couldn't insert Session to Database", e);
            throw new DaoException("Couldn't insert Session to Database");
        }
        return inserted;
    }

    private int getLastGeneratedKey(PreparedStatement statement) throws SQLException {
        int key = -1;
        final ResultSet generatedKeys = statement.getGeneratedKeys();
        while (generatedKeys.next())
            key = generatedKeys.getInt(1);
        return key;
    }

    private void setSessionToInsertStatement(Session session, PreparedStatement statement) throws DaoException {
        final MySQLSeatDao seatDAO = getSeatDAO();
        final int allSeatsAmount = seatDAO.getAllSeatsAmount();
        try {
            statement.setInt(1, session.getFilm().getId());
            final LocalDate date = session.getDate();
            final LocalTime time = session.getTime();
            statement.setDate(2, Date.valueOf(date));
            statement.setTime(3, Time.valueOf(time));
            statement.setBigDecimal(4, session.getTicketPrice());
            statement.setInt(5, allSeatsAmount);

        } catch (SQLException e) {
            logger.error("Couldn't set session to Statement", e);
            throw new DaoException("Couldn't set session to Statement", e);
        }
    }

    @Override
    public Session findById(int id) throws DaoException {
        Session session = null;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                session = getSessionFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Couldn't find session by id in Database", e);
            throw new DaoException("Couldn't find session by id in Database");
        }
        return session;
    }

    @Override
    public List<Session> findAll() throws DaoException {
        return new ArrayList<>();
    }

    @Override
    public List<Session> findAll(int start, int size) throws DaoException {
        List<Session> sessionList = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL + ORDER_BY_DATETIME_ASC + LIMIT)) {
            statement.setInt(1, start - 1);
            statement.setInt(2, size);
            logger.debug("Statement: " + statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Session session = getSessionFromResultSet(resultSet);
                sessionList.add(session);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get paginated list of sessions from Database", e);
            throw new DaoException("Couldn't get paginated list of sessions from Database");
        }
        return sessionList;
    }


    @Override
    public int countTotalRow() throws DaoException {
        int amount = 0;
        try (PreparedStatement statement = getConnection().prepareStatement(COUNT_TOTAL_ROWS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Couldn't count total row amount of sessions from Database", e);
            throw new DaoException("Couldn't count total row amount of sessions from Database");
        }
        return amount;
    }


    private List<Session> getFilteredAndSortedSessionList(String request, int start, int size) throws DaoException {
        List<Session> sessionList = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(request)) {
            final LocalDate nowDate = LocalDate.now();
            final LocalTime nowTime = LocalTime.now();
            statement.setDate(1, Date.valueOf(nowDate));
            statement.setDate(2, Date.valueOf(nowDate));
            statement.setTime(3, Time.valueOf(nowTime));
            statement.setTime(4, Time.valueOf(MIN_SESSION_TIME));
            statement.setInt(5, start - 1);
            statement.setInt(6, size);
            logger.debug("Statement: {}", statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Session session = getSessionFromResultSet(resultSet);
                sessionList.add(session);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get list of all sessionList from Database", e);
            throw new DaoException("Couldn't get list of all sessionList from Database");
        }
        return sessionList;
    }

    @Override
    public Session update(Session element) {
        return null;
    }

    @Override
    public boolean delete(Session element) throws DaoException {
        return delete(element.getId());
    }

    private Session getSessionFromResultSet(ResultSet rs) throws DaoException {
        Session session = null;
        try {
            session = new Session(
                    rs.getInt("session_id"),
                    rs.getBigDecimal("ticket_price"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("time").toLocalTime(),
                    rs.getInt("free_seats")
            );
            final int filmId = rs.getInt("film_id");
            final Film film = getFilmDAO().findById(filmId);
            session.setFilm(film);
        } catch (SQLException e) {
            logger.error("Couldn't get session from ResultSet", e);
            throw new DaoException("Couldn't get session from ResultSet");
        }
        return session;
    }

    private MySQLFilmDao getFilmDAO() {
        final MySQLFilmDao mySQLFilmDAO = new MySQLFilmDao();
        mySQLFilmDAO.setConnection(getConnection());
        return mySQLFilmDAO;
    }

    @Override
    public List<Session> findFilteredAndSortedSessionList(Map<String, String> map, int start, int size) throws DaoException {
        final String defaultRequest = SELECT_ALL + WHERE_DEFAULT;
        final String request = getRequestForFilterAndSort(map, defaultRequest);
        return getFilteredAndSortedSessionList(request, start, size);
    }

    @Override
    public int getFreeSeatAmount(Session session) throws DaoException {
        return getFreeSeatAmount(session.getId());
    }

    @Override
    public int getFreeSeatAmount(int sessionId) throws DaoException {
        int amount = 0;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_FREE_SEATS_BY_ID)) {
            statement.setInt(1, sessionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("free_seats");
            }
        } catch (SQLException e) {
            logger.error("Couldn't get free seats", e);
            throw new DaoException("Couldn't get free seats");
        }
        return amount;
    }

    @Override
    public boolean delete(int sessionId) throws DaoException {
        boolean isDeleted = false;
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_BY_SESSION_ID)) {
            statement.setInt(1, sessionId);
            final int row = statement.executeUpdate();
            if (row > 1) throw new DaoException("Statement removed more than one row");
            isDeleted = true;
        } catch (SQLException e) {
            logger.error("Couldn't delete session", e);
            throw new DaoException("Couldn't delete session", e);
        }
        return isDeleted;
    }

    @Override
    public boolean decrementFreeSeatsAmount(int sessionId) throws DaoException {
        boolean isDecremented = true;
        try (PreparedStatement statement = getConnection().prepareStatement(DECREMENT_FREE_SEATS)) {
            statement.setInt(1, sessionId);
            final int row = statement.executeUpdate();
            if (row == 0) {
                logger.warn("Couldn't decrement session: " + sessionId + ", changed rows = " + row);
                isDecremented = false;
            }
        } catch (SQLException e) {
            logger.error("Couldn't decrement free seats amount of Session", e);
            throw new DaoException("Couldn't decrement free seats amount of Session");
        }
        return isDecremented;
    }

    /**
     * Build request based on received filter and sort settings
     *
     * @param map            map containing filter and sort params only
     * @param defaultRequest default SELECT_ALL sql request
     * @return formed sql request
     */
    private String getRequestForFilterAndSort(Map<String, String> map, String defaultRequest) {
        StringBuilder orderedRequest = new StringBuilder(defaultRequest);
        //filter only available session
        if (map.containsValue(SESSION_FILTER_SHOW_ONLY_AVAILABLE)) {
            orderedRequest.append(AND_FREE_SEATS);
        }
        //sort by appropriate parameter
        if (map.containsValue(SESSION_SORT_BY_FILM_NAME)) {
            orderedRequest.append(ORDER_BY_FILM_NAME);
            if (map.containsValue(SESSION_SORT_METHOD_DESC)) {
                orderedRequest.append(DESCENDING);
            }
        } else if (map.containsValue(SESSION_SORT_BY_SEATS_REMAIN)) {
            orderedRequest.append(ORDER_BY_FREE_SEATS);
            if (map.containsValue(SESSION_SORT_METHOD_DESC)) {
                orderedRequest.append(DESCENDING);
            }
        } else { // else sort by DATETIME
            if (map.containsValue(SESSION_SORT_METHOD_DESC)) {
                orderedRequest.append(ORDER_BY_DATETIME_DESC);
            } else {
                orderedRequest.append(ORDER_BY_DATETIME_ASC);
            }
        }
        // add limit
        orderedRequest.append(LIMIT);
        return orderedRequest.toString();
    }

    private MySQLSeatDao getSeatDAO() {
        final MySQLSeatDao mySQLSeatDAO = new MySQLSeatDao();
        mySQLSeatDAO.setConnection(getConnection());
        return mySQLSeatDAO;
    }
}
