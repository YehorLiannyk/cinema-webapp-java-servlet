package yehor.epam.dao.mysql;

import org.apache.log4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.SeatDAO;
import yehor.epam.dao.exception.DAOException;
import yehor.epam.entities.Seat;
import yehor.epam.utilities.LoggerManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLSeatDAO extends BaseDAO implements SeatDAO {
    private static final Logger logger = LoggerManager.getLogger(MySQLSeatDAO.class);
    private static final String SELECT_ALL = "SELECT * FROM seats";
    private static final String SELECT_BY_ID = "SELECT * FROM seats WHERE seat_id=?";
    private static final String SELECT_RECEIVED_SEATS_BY_SESSION_ID = "SELECT * FROM reserved_seats r_s JOIN seats s ON r_s.seat_id=s.seat_id WHERE session_id=?";
    //private static final String INSERT = "INSERT INTO sessions VALUES (session_id, ?,?,?,?)";

    @Override
    public boolean insert(Seat seat) {
        return false;
    }

    private void setSessionToStatement(Seat seat, PreparedStatement statement) throws SQLException {
        /*try {
            statement.setInt(1, seat.getFilm().getId());
            final LocalDate date = seat.getDate();
            final LocalTime time = seat.getTime();
            statement.setDate(2, Date.valueOf(date));
            statement.setTime(3, Time.valueOf(time));
            statement.setBigDecimal(4, seat.getTicketPrice());
        } catch (SQLException e) {
            logger.error("Couldn't set seat to Statement", e);
            throw new SQLException("Couldn't set seat to Statement", e);
        }*/
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


}
