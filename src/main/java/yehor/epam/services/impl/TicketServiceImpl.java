package yehor.epam.services.impl;

import org.slf4j.Logger;
import yehor.epam.dao.TicketDao;
import yehor.epam.dao.factories.DaoFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.entities.Ticket;
import yehor.epam.entities.User;
import yehor.epam.exceptions.EmptyListException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.exceptions.TicketException;
import yehor.epam.services.SeatService;
import yehor.epam.services.TicketService;
import yehor.epam.utilities.LoggerManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Film
 */
public class TicketServiceImpl implements TicketService {
    private static final Logger logger = LoggerManager.getLogger(TicketServiceImpl.class);
    private static final String CLASS_NAME = TicketServiceImpl.class.getName();
    private SeatService seatService;

    public TicketServiceImpl() {
        seatService = new SeatServiceImpl();
    }

    @Override
    public void setSeatService(SeatService seatService) {
        this.seatService = seatService;
    }

    @Override
    public void saveAll(List<Ticket> ticketList) throws ServiceException {
        if (ticketList == null || ticketList.isEmpty()) {
            logger.warn("Received ticket list is null or empty");
            throw new EmptyListException("Received ticket list is null or empty");
        }
        try {
            for (Ticket ticket : ticketList) {
                save(ticket);
            }
        } catch (Exception e) {
            throwServiceException("Couldn't save ticket List", e);
        }
    }

    @Override
    public void save(Ticket ticket) throws ServiceException {
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final int seatId = ticket.getSeat().getId();
            final int sessionId = ticket.getSession().getId();
            TicketDao ticketDao = factory.getTicketDao();
            if (seatService.isSeatFreeBySessionId(seatId, sessionId)) {
                logger.debug("Seat is free, id: " + seatId + " and sessionId: " + sessionId);
                ticketDao.insert(ticket);
            } else {
                logger.warn("Seat is already reserved");
                throw new TicketException("Seat is already reserved, choose another one");
            }
        } catch (Exception e) {
            throwServiceException("Couldn't save ticket", e);
        }
    }

    @Override
    public List<Ticket> formTicketList(Session session, List<Seat> seatList, User user) {
        final List<Ticket> ticketList = new ArrayList<>();
        for (Seat seat : seatList) {
            final Ticket ticket = new Ticket(session, user, seat, session.getTicketPrice());
            ticketList.add(ticket);
        }
        if (ticketList.isEmpty()) {
            logger.error("Couldn't form TicketList, it is Empty");
            throw new EmptyListException("Couldn't form TicketList, it is Empty");
        }
        return ticketList;
    }


    @Override
    public BigDecimal countTotalCostOfTicketList(final List<Ticket> ticketList) {
        if (ticketList == null || ticketList.isEmpty()) {
            logger.error("seatList is null or empty, can't count total cost");
            throw new EmptyListException("seatList is null or empty, can't count total cost");
        }
        BigDecimal totalCost = new BigDecimal(0);
        for (Ticket ticket : ticketList) {
            totalCost = totalCost.add(ticket.getTicketPrice());
        }
        return totalCost;
    }

    @Override
    public List<Ticket> getAllByUserId(int userId, int page, int size) throws ServiceException {
        List<Ticket> ticketList = new ArrayList<>();
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final TicketDao ticketDao = factory.getTicketDao();
            int start = page;
            if (page > 1) {
                start--;
                start = start * size + 1;
            }
            ticketList = ticketDao.findAllByUserId(userId, start, size);
        } catch (Exception e) {
            throwServiceException("Couldn't get ticket list by user id", e);
        }
        return ticketList;
    }

    @Override
    public int countTotalPagesByUserId(int userId, int size) throws ServiceException {
        int amount = 0;
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final TicketDao ticketDao = factory.getTicketDao();
            final int count = ticketDao.countTotalRowByUserId(userId);
            amount = count / size;
            amount = count % size == 0 ? amount : amount + 1;
        } catch (Exception e) {
            throwServiceException("Couldn't get paginated user's ticket list", e);
        }
        return amount;
    }

    @Override
    public Ticket getById(int id) throws ServiceException {
        Ticket ticket = null;
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final TicketDao ticketDao = factory.getTicketDao();
            ticket = ticketDao.findById(id);
        } catch (Exception e) {
            throwServiceException("Couldn't find ticket", e);
        }
        return ticket;
    }

    private void logCreatingDaoFactory() {
        logger.debug("Created DAOFactory in " + CLASS_NAME);
    }

    private void throwServiceException(String message, Exception e) throws ServiceException {
        logger.error(message, e);
        throw new ServiceException(message, e);
    }
}
