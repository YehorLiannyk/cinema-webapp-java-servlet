package yehor.epam.dao;

import yehor.epam.entities.Ticket;
import yehor.epam.exceptions.DAOException;

import java.util.List;

public interface TicketDao extends DAO<Ticket> {
    /**
     * Get List of all User's tickets
     * @param userId id of User
     * @return list of tickets
     */
    List<Ticket> findAllByUserId(int userId) throws DAOException;
}
