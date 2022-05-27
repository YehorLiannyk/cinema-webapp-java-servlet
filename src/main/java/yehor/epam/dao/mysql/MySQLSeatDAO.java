package yehor.epam.dao.mysql;

import org.apache.log4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.SeatDAO;
import yehor.epam.dao.exception.DAOException;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.utilities.LoggerManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLSeatDAO extends BaseDAO implements SeatDAO {
    private static final Logger logger = LoggerManager.getLogger(MySQLSeatDAO.class);
    private static final String SELECT_ALL = "SELECT * FROM seats";
    private static final String SELECT_BY_ID = "SELECT * FROM seats WHERE seat_id=?";
    private static final String SELECT_RECEIVED_SEAT_BY_ID = "SELECT * FROM reserved_seats WHERE seat_id=? AND session_id=?";

    private static final String SELECT_RECEIVED_SEATS_BY_SESSION_ID = "SELECT * FROM reserved_seats r_s JOIN seats s ON r_s.seat_id=s.seat_id WHERE session_id=?";
    private static final String SELECT_FREE_SEATS_BY_SESSION_ID = "SELECT * FROM reserved_seats r_s RIGHT JOIN seats s ON r_s.seat_id=s.seat_id WHERE session_id=? AND r_s.seat_id IS NULL";
    private static final String INSERT_RESERVED_SEAT = "INSERT INTO reserved_seats VALUES (session_seat_id, ?,?)";

    @Override
    public boolean insert(Seat seat) {
        return false;
    }

    @Override
    public Seat findById(int id) {
        Seat seat = null;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                seat = getSeatFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Couldn't find seat by id in DB", e);
            throw new DAOException("Couldn't find seat by id in DB");
        }
        return seat;
    }

    @Override
    public List<Seat> findAll() {
        List<Seat> seats = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Seat seat = getSeatFromResultSet(resultSet);
                seats.add(seat);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get list of all seats from DB", e);
            throw new DAOException("Couldn't get list of all seats from DB");
        }
        return seats;
    }

    @Override
    public Seat update(Seat element) {
        return null;
    }

    @Override
    public boolean delete(Seat element) {
        return false;
    }

    private Seat getSeatFromResultSet(ResultSet rs) {
        Seat seat = null;
        try {
            seat = new Seat(
                    rs.getInt("seat_id"),
                    rs.getInt("row_number"),
                    rs.getInt("place_number")
            );
        } catch (SQLException e) {
            logger.error("Couldn't get seat from ResultSet", e);
            throw new DAOException("Couldn't get seat from ResultSet");
        }
        return seat;
    }

    @Override
    public List<Seat> findAllReservedBySession(int sessionId) {
        List<Seat> reservedSeats = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_RECEIVED_SEATS_BY_SESSION_ID)) {
            statement.setInt(1, sessionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Seat seat = getSeatFromResultSet(resultSet);
                reservedSeats.add(seat);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get list of all reservedSeats from DB", e);
            throw new DAOException("Couldn't get list of all reservedSeats from DB");
        }
        return reservedSeats;
    }

    @Override
    public boolean insertReservedSeat(final Session session, final Seat seat) throws SQLException {
        boolean inserted = false;
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_RESERVED_SEAT, Statement.RETURN_GENERATED_KEYS)) {
            if (seat == null || session == null) {
                logger.error("Received Seat or Session is null");
                throw new DAOException("Received Seat or Session is null");
            }
            setReservedSeatToStatement(session, seat, statement);
            final int row = seat.getRowNumber();
            statement.executeUpdate();
            if (row < 1) throw new DAOException("Statement inserted nothing");
            inserted = true;
        } catch (SQLException e) {
            logger.error("Couldn't insert reserved seat to DataBase", e);
            throw new DAOException("Couldn't insert  reserved seat to DataBase", e);
        }
        return inserted;
    }

    @Override
    public boolean isSeatReserved(int seatId, int sessionId) {
        boolean isReserved = true;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_RECEIVED_SEAT_BY_ID)) {
            statement.setInt(1, seatId);
            statement.setInt(2, sessionId);
            logger.debug("isSeatReserved statement:" + statement.toString());
            final ResultSet resultSet = statement.executeQuery();
            isReserved = resultSet.next();
            logger.debug("isReserved = resultSet.next(): " + resultSet.next());
        } catch (SQLException e) {
            logger.error("Couldn't check is seat reserved", e);
            throw new DAOException("Couldn't check is seat reserved");
        }
        return isReserved;
    }

    @Override
    public int getFreeSeatsAmountBySessionId(int sessionId) {
       int freeAmount = 0;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_FREE_SEATS_BY_SESSION_ID)) {
            statement.setInt(1, sessionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                freeAmount++;
            }
        } catch (SQLException e) {
            logger.error("Couldn't count free seats", e);
            throw new DAOException("Couldn't count free seats");
        }
        return freeAmount;
    }

    private void setReservedSeatToStatement(Session session, Seat seat, PreparedStatement statement) throws SQLException {
        statement.setInt(1, session.getId());
        statement.setInt(2, seat.getId());
        logger.debug("statement.toString()" + statement.toString());
    }

}
