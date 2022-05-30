package yehor.epam.dao;

import yehor.epam.entities.Ticket;

import java.util.List;

public interface TicketDAO extends DAO<Ticket> {
    /**
     * Get List of all User's tickets
     * @param userId id of User
     * @return list of tickets
     */
    List<Ticket> findAllByUserId(int userId);
}
