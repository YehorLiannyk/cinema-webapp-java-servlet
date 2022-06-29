package yehor.epam.dao.mysql;

import org.slf4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.TicketDao;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.entities.Ticket;
import yehor.epam.entities.User;
import yehor.epam.exceptions.DaoException;
import yehor.epam.utilities.LoggerManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLTicketDao extends BaseDAO implements TicketDao {
    private static final Logger logger = LoggerManager.getLogger(MySQLTicketDao.class);
    private static final String INSERT = "INSERT INTO tickets VALUES (ticket_id, ?,?,?,?)";
    private static final String SELECT_BY_USER_ID = "SELECT * FROM tickets WHERE user_id=?";
    private static final String SELECT_BY_ID = "SELECT * FROM tickets t WHERE ticket_id=?";
    private static final String COUNT_TOTAL_ROWS = "SELECT COUNT(*) FROM tickets WHERE user_id=?";
    private static final String LIMIT = " LIMIT ?, ?";

    @Override
    public boolean insert(Ticket ticket) throws DaoException {
        boolean inserted = false;
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            setTicketToStatement(ticket, statement);
            ticketInsertTransaction(ticket, statement);
            inserted = true;
        } catch (SQLException e) {
            logger.error("Couldn't insert Ticket to DataBase");
            throw new DaoException("Couldn't insert Ticket to DataBase");
        }
        return inserted;
    }

    /**
     * Transaction method for preventing Ticket writing to Database without writing its to reserved_seats
     *
     * @param ticket    Ticket item
     * @param statement PreparedStatement
     */
    private void ticketInsertTransaction(Ticket ticket, PreparedStatement statement) throws SQLException, DaoException {
        getConnection().setAutoCommit(false);
        statement.executeUpdate();
        final boolean freeSeatsAmount = getSessionDAO().decrementFreeSeatsAmount(ticket.getSession().getId());
        if (!freeSeatsAmount) {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
            logger.debug("rollback and setAutoCommit(true)");
            throw new DaoException("Ticket and reserved seat were not inserted, cause there is no free seats");
        }
        final boolean insertReservedSeats = getSeatDAO().reserveSeatBySession(ticket.getSeat(), ticket.getSession());
        if (insertReservedSeats) {
            getConnection().commit();
        } else {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
            logger.debug("rollback and setAutoCommit(true), Ticket and reserved seat were not inserted");
            throw new DaoException("Ticket and reserved seat were not inserted");
        }
        getConnection().setAutoCommit(true);
    }

    private void setTicketToStatement(Ticket ticket, PreparedStatement statement) throws SQLException {
        try {
            statement.setInt(1, ticket.getSession().getId());
            statement.setInt(2, ticket.getUser().getId());
            statement.setInt(3, ticket.getSeat().getId());
            statement.setBigDecimal(4, ticket.getTicketPrice());
        } catch (SQLException e) {
            logger.error("Couldn't set Ticket to Statement", e);
            throw new SQLException(e);
        }
    }

    @Override
    public Ticket findById(int id) throws DaoException {
        Ticket ticket = null;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ticket = getTicketFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Couldn't find ticket by id in Database", e);
            throw new DaoException("Couldn't find ticket by id in Database");
        }
        return ticket;
    }

    @Override
    public List<Ticket> findAll() throws DaoException {
        return new ArrayList<>();
    }


    @Override
    public Ticket update(Ticket element) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Ticket element) throws DaoException {
        return false;
    }

    private Ticket getTicketFromResultSet(ResultSet rs) throws DaoException {
        Ticket ticket = null;
        try {
            User user = getUserDAO().findById(rs.getInt("user_id"));
            Session session = getSessionDAO().findById(rs.getInt("session_id"));
            Seat seat = getSeatDAO().findById(rs.getInt("seat_id"));
            ticket = new Ticket(
                    rs.getInt("ticket_id"),
                    session, user, seat,
                    rs.getBigDecimal("ticket_price")
            );
        } catch (SQLException e) {
            logger.error("Couldn't get ticket from ResultSet", e);
            throw new DaoException("Couldn't get ticket from ResultSet");
        }
        return ticket;
    }

    private MySQLSeatDao getSeatDAO() {
        final MySQLSeatDao mySQLSeatDAO = new MySQLSeatDao();
        mySQLSeatDAO.setConnection(getConnection());
        return mySQLSeatDAO;
    }

    private MySQLSessionDao getSessionDAO() {
        final MySQLSessionDao mySQLSessionDAO = new MySQLSessionDao();
        mySQLSessionDAO.setConnection(getConnection());
        return mySQLSessionDAO;
    }

    private MySQLUserDao getUserDAO() {
        final MySQLUserDao mySQLUserDAO = new MySQLUserDao();
        mySQLUserDAO.setConnection(getConnection());
        return mySQLUserDAO;
    }

    @Override
    public List<Ticket> findAllByUserId(int userId, int start, int size) throws DaoException {
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_USER_ID + LIMIT)) {
            statement.setInt(1, userId);
            statement.setInt(2, start - 1);
            statement.setInt(3, size);
            ResultSet resultSet = statement.executeQuery();
            logger.debug("userId = {}, start={}, size = {}", userId, start, size);
            logger.debug("Statement: {}", statement);
            while (resultSet.next()) {
                final Ticket ticket = getTicketFromResultSet(resultSet);
                ticketList.add(ticket);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get paginated user ticket list from Database", e);
            throw new DaoException("Couldn't get paginated user ticket list from Database");
        }
        return ticketList;
    }

    @Override
    public int countTotalRowByUserId(int userId) throws DaoException {
        int amount = 0;
        try (PreparedStatement statement = getConnection().prepareStatement(COUNT_TOTAL_ROWS)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Couldn't count total row amount of tickets from Database", e);
            throw new DaoException("Couldn't count total row amount of tickets from Database");
        }
        return amount;
    }
}
