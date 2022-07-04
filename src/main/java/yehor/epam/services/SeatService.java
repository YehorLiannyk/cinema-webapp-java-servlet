package yehor.epam.services;

import yehor.epam.entities.Seat;
import yehor.epam.exceptions.ServiceException;

import java.util.List;

public interface SeatService {
    List<Seat> getSeatListByIdArray(String[] seatIds) throws ServiceException;

    List<Seat> getFreeSeatsBySessionId(int id) throws ServiceException;

    List<Seat> getAll() throws ServiceException;

    boolean isSeatFreeBySessionId(int seatId, int sessionId) throws ServiceException;
}
