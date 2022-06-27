package yehor.epam.dao.mysql;

import org.apache.log4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.SeatDAO;
import yehor.epam.exceptions.DAOException;
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

    private static final String INSERT_FREE_SEAT = "INSERT INTO free_seats VALUES (session_seat_id, ?,?)";
    private static final String SELECT_FREE_SEATS_BY_SESSION_ID = "SELECT * FROM free_seats JOIN seats s on free_seats.seat_id = s.seat_id WHERE session_id=?";
    private static final String SELECT_FREE_SEAT_BY_ID_AND_SESSION = "SELECT * FROM free_seats WHERE seat_id=? AND session_id=?";
    private static final String REMOVE_FREE_SEAT = "DELETE FROM free_seats WHERE seat_id=? AND session_id=?";


    @Override
    public boolean insert(Seat seat) {
        return false;
    }

    @Override
    public Seat findById(int id) throws DAOException {
        Seat seat = null;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                seat = getSeatFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Couldn't find seat by id in Database", e);
            throw new DAOException("Couldn't find seat by id in Database");
        }
        return seat;
    }

    @Override
    public List<Seat> findAll() throws DAOException {
        List<Seat> seats = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Seat seat = getSeatFromResultSet(resultSet);
                seats.add(seat);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get list of all seats from Database", e);
            throw new DAOException("Couldn't get list of all seats from Database");
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

    private Seat getSeatFromResultSet(ResultSet rs) throws DAOException {
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
    public List<Seat> findAllFreeSeatBySessionId(int sessionId) throws DAOException {
        List<Seat> freeSeatList = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_FREE_SEATS_BY_SESSION_ID)) {
            statement.setInt(1, sessionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Seat seat = getSeatFromResultSet(resultSet);
                freeSeatList.add(seat);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get list of all freeSeatList from Database", e);
            throw new DAOException("Couldn't get list of all freeSeatList from Database");
        }
        return freeSeatList;
    }

    @Override
    public boolean reserveSeatBySession(final Seat seat, final Session session) throws DAOException {
        boolean isReserved = false;
        try (PreparedStatement statement = getConnection().prepareStatement(REMOVE_FREE_SEAT)) {
            if (seat == null || session == null) {
                logger.error("Received Seat or Session is null");
                throw new DAOException("Received Seat or Session is null");
            }
            setFreeSeatToStatement(session, seat, statement);
            final int row = statement.executeUpdate();
            if (row > 1) throw new DAOException("Statement removed more than one row");
            isReserved = true;
        } catch (SQLException e) {
            logger.error("Couldn't reserve seat", e);
            throw new DAOException("Couldn't reserve seat", e);
        }
        return isReserved;
    }

    @Override
    public boolean isSeatFree(int seatId, int sessionId) throws DAOException {
        boolean isFree = false;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_FREE_SEAT_BY_ID_AND_SESSION)) {
            statement.setInt(1, seatId);
            statement.setInt(2, sessionId);
            final ResultSet resultSet = statement.executeQuery();
            isFree = resultSet.next();
        } catch (SQLException e) {
            logger.error("Couldn't check is seat reserved", e);
            throw new DAOException("Couldn't check is seat reserved");
        }
        return isFree;
    }

    @Override
    public int getFreeSeatsAmountBySessionId(int sessionId) throws DAOException {
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

    @Override
    public void insertFreeSeatsForSession(Session session) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_FREE_SEAT, Statement.RETURN_GENERATED_KEYS)) {
            final List<Seat> seatList = findAll();
            final int sessionId = session.getId();
            for (Seat seat : seatList) {
                statement.setInt(1, sessionId);
                statement.setInt(2, seat.getId());
                statement.addBatch();
            }
            final int[] rows = statement.executeBatch();
            if (rows.length < 1) throw new DAOException("Statement inserted nothing");
        } catch (SQLException e) {
            logger.error("Couldn't insert free seats to DataBase", e);
            throw new DAOException("Couldn't insert free seats to DataBase", e);
        }
    }

    @Override
    public int getAllSeatsAmount() throws DAOException {
        int amount = 0;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount++;
            }
        } catch (SQLException e) {
            logger.error("Couldn't count seats", e);
            throw new DAOException("Couldn't count seats");
        }
        return amount;
    }

    private void setFreeSeatToStatement(Session session, Seat seat, PreparedStatement statement) throws SQLException {
        statement.setInt(1, seat.getId());
        statement.setInt(2, session.getId());
    }

}
