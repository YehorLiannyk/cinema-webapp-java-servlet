package yehor.epam.dao;

import yehor.epam.entities.Seat;

import java.util.List;

public interface SeatDAO extends DAO<Seat> {
    List<Seat> findAllReservedBySession(int sessionId);
}
