package yehor.epam.dao;

import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;

import java.sql.SQLException;
import java.util.List;

public interface SeatDAO extends DAO<Seat> {
    List<Seat> findAllReservedBySession(int sessionId);
    boolean insertReservedSeat(final Session session, final Seat seat) throws SQLException;

    boolean isSeatReserved(int seatId, int sessionId);
}
