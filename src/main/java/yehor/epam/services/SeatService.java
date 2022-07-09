package yehor.epam.services;

import yehor.epam.entities.Seat;
import yehor.epam.exceptions.ServiceException;

import java.util.List;

public interface SeatService {
    /**
     * Get seat list by its ids
     *
     * @param seatIds array of ids of seats
     * @return list of seats
     * @throws ServiceException
     */
    List<Seat> getSeatListByIdArray(String[] seatIds) throws ServiceException;

    /**
     * Get list of free seats by session id
     *
     * @param id session id
     * @return list of free seats
     * @throws ServiceException
     */
    List<Seat> getFreeSeatsBySessionId(int id) throws ServiceException;

    /**
     * Get all seats of cinema hall
     *
     * @return seat list
     * @throws ServiceException
     */
    List<Seat> getAll() throws ServiceException;

    /**
     * Checks if seat is free for the sesion
     *
     * @param seatId    seat id
     * @param sessionId session id
     * @return true - is free, otherwise - false
     * @throws ServiceException
     */
    boolean isSeatFreeBySessionId(int seatId, int sessionId) throws ServiceException;
}
