package yehor.epam.dao;

import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;

import java.util.List;

public interface SeatDAO extends DAO<Seat> {
    List<Seat> findAllFreeSeatBySessionId(int sessionId);

    boolean reserveSeatBySession(final Seat seat, final Session session);

    boolean isSeatFree(int seatId, int sessionId);

    int getFreeSeatsAmountBySessionId(int sessionId);

    void insertFreeSeatsForSession(Session session);

    int getAllSeatsAmount();

}
