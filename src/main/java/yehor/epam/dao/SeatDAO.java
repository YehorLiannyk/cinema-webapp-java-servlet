package yehor.epam.dao;

import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.exceptions.DAOException;

import java.util.List;

public interface SeatDAO extends DAO<Seat> {
    /**
     * Find free seats amount of session
     *
     * @param sessionId session id
     * @return free seatList
     */
    List<Seat> findAllFreeSeatBySessionId(int sessionId) throws DAOException;

    /**
     * Reserve seat for session
     *
     * @param seat    reserved seat
     * @param session session
     * @return true if seat was reserved
     */
    boolean reserveSeatBySession(final Seat seat, final Session session) throws DAOException;

    /**
     * Check if seat is unreserved
     *
     * @param seatId    seat id
     * @param sessionId session id
     * @return true if seat is free and false if not
     */
    boolean isSeatFree(int seatId, int sessionId) throws DAOException;

    /**
     * Get amount of free seats by session
     *
     * @param sessionId session id
     * @return free seats amount
     */
    int getFreeSeatsAmountBySessionId(int sessionId) throws DAOException;

    /**
     * Insert free seats to DB when session is creating
     *
     * @param session session
     */
    void insertFreeSeatsForSession(Session session) throws DAOException;

    /**
     * Get common seat amount
     *
     * @return seat amount
     */
    int getAllSeatsAmount() throws DAOException;

}
