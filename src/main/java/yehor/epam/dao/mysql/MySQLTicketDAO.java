package yehor.epam.dao.mysql;

import org.apache.log4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.TicketDAO;
import yehor.epam.dao.exception.DAOException;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.entities.Ticket;
import yehor.epam.entities.User;
import yehor.epam.utilities.LoggerManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLTicketDAO extends BaseDAO implements TicketDAO {
    private static final Logger logger = LoggerManager.getLogger(MySQLTicketDAO.class);
    private static final String INSERT = "INSERT INTO tickets VALUES (ticket_id, ?,?,?,?)";
    private static final String SELECT_BY_USER_ID = "SELECT * FROM tickets WHERE user_id=?";

    @Override
    public boolean insert(Ticket ticket) {
        boolean inserted = false;
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            setTicketToStatement(ticket, statement);
            logger.debug("setTicketToStatement: " + statement.toString());
            ticketInsertTransaction(ticket, statement);
            inserted = true;
        } catch (SQLException e) {
            logger.error("Couldn't insert Ticket to DataBase");
            throw new DAOException("Couldn't insert Ticket to DataBase");
        }
        return inserted;
    }

    /**
     * Transaction method for preventing Ticket writing to DB without writing its to reserved_seats
     *
     * @param ticket    Ticket item
     * @param statement PreparedStatement
     */
    private void ticketInsertTransaction(Ticket ticket, PreparedStatement statement) throws SQLException {
        getConnection().setAutoCommit(false);
        logger.debug("getConnection().setAutoCommit(false)");
        statement.executeUpdate();
        logger.debug("statement.executeUpdate() for ticket only");
        final boolean insertReservedSeats = getSeatDAO().insertReservedSeat(ticket.getSession(), ticket.getSeat());
        if (insertReservedSeats) {
            getConnection().commit();
        } else {
            getConnection().rollback();
            throw new DAOException("Ticket and reserved seat were not inserted");
        }
        getConnection().setAutoCommit(true);
        logger.debug("getConnection().setAutoCommit(true)");
    }

   /* private int getLastGeneratedKey(PreparedStatement statement) throws SQLException {
        int key = -1;
        final ResultSet generatedKeys = statement.getGeneratedKeys();
        while (generatedKeys.next())
            key = generatedKeys.getInt(1);
        return key;
    }*/

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
    public Ticket findById(int id) {
        /*Seat seat = null;
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
        return seat;*/
        return null;
    }

    @Override
    public List<Ticket> findAll() {
        /*List<Seat> seats = new ArrayList<>();
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
        return seats;*/
        return null;
    }

    @Override
    public Ticket update(Ticket element) {
        return null;
    }

    @Override
    public boolean delete(Ticket element) {
        return false;
    }

    private Ticket getTicketFromResultSet(ResultSet rs) {
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
            throw new DAOException("Couldn't get ticket from ResultSet");
        }
        return ticket;
    }

    private MySQLSeatDAO getSeatDAO() {
        final MySQLSeatDAO mySQLSeatDAO = new MySQLSeatDAO();
        mySQLSeatDAO.setConnection(getConnection());
        return mySQLSeatDAO;
    }

    private MySQLSessionDAO getSessionDAO() {
        final MySQLSessionDAO mySQLSessionDAO = new MySQLSessionDAO();
        mySQLSessionDAO.setConnection(getConnection());
        return mySQLSessionDAO;
    }

    private MySQLUserDAO getUserDAO() {
        final MySQLUserDAO mySQLUserDAO = new MySQLUserDAO();
        mySQLUserDAO.setConnection(getConnection());
        return mySQLUserDAO;
    }


    @Override
    public List<Ticket> findAllByUserId(int userId) {
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Ticket ticket = getTicketFromResultSet(resultSet);
                ticketList.add(ticket);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get list of all films from DB", e);
            throw new DAOException("Couldn't get list of all films from DB");
        }
        return ticketList;
    }
}
