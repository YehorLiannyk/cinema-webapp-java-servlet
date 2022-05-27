package yehor.epam.dao;

import yehor.epam.entities.Ticket;

import java.util.List;

public interface TicketDAO extends DAO<Ticket> {
    List<Ticket> findAllByUserId(int userId);
}
