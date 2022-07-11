package yehor.epam.services.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import yehor.epam.dao.TicketDao;
import yehor.epam.dao.factories.DaoFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.entities.Ticket;
import yehor.epam.entities.User;
import yehor.epam.exceptions.DaoException;
import yehor.epam.exceptions.EmptyListException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.SeatService;
import yehor.epam.services.TicketService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class TicketServiceImplTest {

    private static TicketService ticketService;
    private static SeatService seatService;
    private static TicketDao ticketDao;
    private static MockedStatic<DaoFactoryDeliver> factoryDeliver;

    @BeforeAll
    static void setup() {
        ticketService = spy(TicketServiceImpl.class);
        seatService = mock(SeatServiceImpl.class);
        ticketService.setSeatService(seatService);
        factoryDeliver = mockStatic(DaoFactoryDeliver.class);
        DaoFactoryDeliver daoFactoryDeliver = mock(DaoFactoryDeliver.class);
        DaoFactory daoFactory = mock(DaoFactory.class);
        ticketDao = mock(TicketDao.class);

        factoryDeliver.when(DaoFactoryDeliver::getInstance).thenReturn(daoFactoryDeliver);
        factoryDeliver.when(() -> DaoFactoryDeliver.getInstance().getFactory()).thenReturn(daoFactory);
        when(daoFactoryDeliver.getFactory()).thenReturn(daoFactory);
        when(daoFactory.getTicketDao()).thenReturn(ticketDao);
    }

    @AfterAll
    static void clean() {
        factoryDeliver.close();
    }

    @Test
    void saveTicketList() throws ServiceException, DaoException {
        final Ticket ticket = mock(Ticket.class);
        List<Ticket> ticketList = List.of(ticket, ticket);
        Seat seat = mock(Seat.class);
        Session session = mock(Session.class);

        when(ticket.getSeat()).thenReturn(seat);
        when(ticket.getSession()).thenReturn(session);
        when(seatService.isSeatFreeBySessionId(seat.getId(), session.getId())).thenReturn(true);
        ticketService.saveAll(ticketList);
        verify(ticketService, times(ticketList.size())).save(ticket);

    }

    @Test
    void saveTicketListThrowEmptyListException() throws ServiceException, DaoException {
        List<Ticket> ticketList = null;
        try {
            ticketService.saveAll(ticketList);
            fail("EmptyListException should be thrown");
        } catch (EmptyListException e) {
        }
    }

    @Test
    void saveTicket() throws DaoException, ServiceException {
        Ticket ticket = mock(Ticket.class);
        Seat seat = mock(Seat.class);
        Session session = mock(Session.class);

        when(ticket.getSeat()).thenReturn(seat);
        when(ticket.getSession()).thenReturn(session);
        when(seatService.isSeatFreeBySessionId(seat.getId(), session.getId())).thenReturn(true);

        ticketService.save(ticket);
        verify(ticketDao).insert(ticket);
    }

    @Test
    void saveTicketThrowServiceException() throws DaoException, ServiceException {
        Ticket ticket = mock(Ticket.class);
        Seat seat = mock(Seat.class);
        Session session = mock(Session.class);

        when(ticket.getSeat()).thenReturn(seat);
        when(ticket.getSession()).thenReturn(session);
        when(seatService.isSeatFreeBySessionId(seat.getId(), session.getId())).thenReturn(false);
        try {
            ticketService.save(ticket);
            fail("ServiceException should be thrown");
        } catch (ServiceException e) {
        }
        verifyNoInteractions(ticketDao);
    }

    @Test
    void formTicketList() {
        Session session = mock(Session.class);
        Seat seat = mock(Seat.class);
        List<Seat> seatList = List.of(seat, seat);
        User user = mock(User.class);
        final List<Ticket> ticketList = ticketService.formTicketList(session, seatList, user);
        Assertions.assertFalse(ticketList.isEmpty());
    }

    @Test
    void formTicketListThrowEmptyListException() {
        Session session = mock(Session.class);
        List<Seat> seatList = List.of();
        User user = mock(User.class);
        List<Ticket> ticketList = null;
        try {
            ticketList = ticketService.formTicketList(session, seatList, user);
            fail("EmptyListException should be thrown");
        } catch (EmptyListException e) {
        }
        Assertions.assertNull(ticketList);
    }


    @Test
    void countTotalCostOfTicketList() {
        Ticket ticket = mock(Ticket.class);
        List<Ticket> ticketList = List.of(ticket, ticket);
        when(ticket.getTicketPrice()).thenReturn(BigDecimal.TEN);
        final BigDecimal actual = ticketService.countTotalCostOfTicketList(ticketList);
        Assertions.assertEquals(BigDecimal.valueOf(20), actual);
    }

    @Test
    void countTotalCostOfTicketListThrowEmptyListException() {
        List<Ticket> ticketList = List.of();
        try {
            ticketService.countTotalCostOfTicketList(ticketList);
            fail("EmptyListException should be thrown");
        } catch (EmptyListException e) {
        }
    }

    @Test
    void getAllByUserId() throws DaoException, ServiceException {
        List<Ticket> ticketList = mock(List.class);
        when(ticketDao.findAllByUserId(anyInt(), anyInt(), anyInt())).thenReturn(ticketList);
        final List<Ticket> all = ticketService.getAllByUserId(anyInt(), anyInt(), anyInt());
        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    void getAllByUserIdPageBiggerOne() throws DaoException, ServiceException {
        List<Ticket> ticketList = mock(List.class);
        when(ticketDao.findAllByUserId(1, 2, 1)).thenReturn(ticketList);
        final List<Ticket> all = ticketService.getAllByUserId(1, 2, 1);
        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    void countTotalPagesByUserId() throws DaoException, ServiceException {
        when(ticketDao.countTotalRowByUserId(1)).thenReturn(4);
        final int countTotalPages = ticketService.countTotalPagesByUserId(1, 2);
        assertEquals(2, countTotalPages);
    }

    @Test
    void getById() throws DaoException, ServiceException {
        Ticket ticket = mock(Ticket.class);
        when(ticketDao.findById(1)).thenReturn(ticket);
        final Ticket ticketServiceById = ticketService.getById(1);
        assertEquals(ticket, ticketServiceById);
    }
}