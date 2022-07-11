package yehor.epam.services;

import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.entities.Ticket;
import yehor.epam.entities.User;
import yehor.epam.exceptions.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface TicketService {
    /**
     * Set seat service
     *
     * @param seatService seat service
     */
    void setSeatService(SeatService seatService);

    /**
     * Save ticket list
     *
     * @param ticketList ticket list
     * @throws ServiceException
     */
    void saveAll(List<Ticket> ticketList) throws ServiceException;

    /**
     * Save ticket
     *
     * @param ticket ticket
     * @throws ServiceException
     */
    void save(Ticket ticket) throws ServiceException;

    /**
     * Form Ticket List for User by chosen Session and Seats
     *
     * @param session  chosen Session
     * @param seatList chosen Seats
     * @param user     other-words customer
     * @return formed TicketList or throw an exception if no tickets added
     */
    List<Ticket> formTicketList(Session session, List<Seat> seatList, User user);

    /**
     * Count total cost of all tickets
     *
     * @param ticketList chosen tickets
     * @return total cost of tickets
     */
    BigDecimal countTotalCostOfTicketList(List<Ticket> ticketList);

    /**
     * Get ticket list by user id
     *
     * @param userId use rid
     * @param page   page number
     * @param size   page size
     * @return ticket list
     * @throws ServiceException
     */
    List<Ticket> getAllByUserId(int userId, int page, int size) throws ServiceException;

    /**
     * Count page amount of all user tickets
     *
     * @param userId user id
     * @param size   page size
     * @return page amount
     * @throws ServiceException
     */
    int countTotalPagesByUserId(int userId, int size) throws ServiceException;

    /**
     * Get ticket by id
     *
     * @param id ticket id
     * @return Ticket
     * @throws ServiceException
     */
    Ticket getById(int id) throws ServiceException;
}
