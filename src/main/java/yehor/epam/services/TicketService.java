package yehor.epam.services;

import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.entities.Ticket;
import yehor.epam.entities.User;
import yehor.epam.exceptions.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface TicketService {
    void saveTicketList(List<Ticket> ticketList) throws ServiceException;

    void saveTicket(Ticket ticket) throws ServiceException;

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

    List<Ticket> findAllByUserId(int userId) throws ServiceException;

    Ticket getById(int id) throws ServiceException;
}
