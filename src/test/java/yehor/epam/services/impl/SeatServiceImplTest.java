package yehor.epam.services.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import yehor.epam.dao.FilmDao;
import yehor.epam.dao.SeatDao;
import yehor.epam.dao.factories.DaoFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Genre;
import yehor.epam.entities.Seat;
import yehor.epam.exceptions.DaoException;
import yehor.epam.exceptions.EmptyArrayException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.SeatService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class SeatServiceImplTest {
    private static SeatService seatService;
    private static SeatDao seatDao;
    private static MockedStatic<DaoFactoryDeliver> factoryDeliver;


    @BeforeAll
    static void setup() {
        seatService = spy(SeatServiceImpl.class);
        factoryDeliver = mockStatic(DaoFactoryDeliver.class);
        DaoFactoryDeliver daoFactoryDeliver = mock(DaoFactoryDeliver.class);
        DaoFactory daoFactory = mock(DaoFactory.class);
        seatDao = mock(SeatDao.class);

        factoryDeliver.when(DaoFactoryDeliver::getInstance).thenReturn(daoFactoryDeliver);
        factoryDeliver.when(() -> DaoFactoryDeliver.getInstance().getFactory()).thenReturn(daoFactory);
        when(daoFactoryDeliver.getFactory()).thenReturn(daoFactory);
        when(daoFactory.getSeatDao()).thenReturn(seatDao);
    }

    @AfterAll
    static void clean() {
        factoryDeliver.close();
    }

    @Test
    void getSeatListByIdArray() throws DaoException, ServiceException {
        String[] seatIds = {"2"};
        Seat seat = mock(Seat.class);
        when(seatDao.findById(anyInt())).thenReturn(seat);
        final List<Seat> list = seatService.getSeatListByIdArray(seatIds);
        assertFalse(list.isEmpty());
    }

    @Test
    void getSeatListByIdArrayIsNull() throws DaoException, ServiceException {
        String[] seatIds = null;
        try {
            seatService.getSeatListByIdArray(seatIds);
            fail("EmptyArrayException should be thrown");
        } catch (EmptyArrayException e) {}
        verify(seatDao, never()).findById(anyInt());
    }

    @Test
    void getFreeSeatsBySessionId() throws DaoException, ServiceException {
        int sessionId = 1;
        List<Seat> seatList = mock(List.class);
        when(seatDao.findAllFreeSeatBySessionId(sessionId)).thenReturn(seatList);
        final List<Seat> list = seatService.getFreeSeatsBySessionId(sessionId);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void getAll() throws DaoException, ServiceException {
        List<Seat> seatList = mock(List.class);
        when(seatDao.findAll()).thenReturn(seatList);
        final List<Seat> all = seatService.getAll();
        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    void isSeatFreeBySessionIdIsTrue() throws DaoException, ServiceException {
        int sessionId = 1;
        int seatId = 1;
        when(seatDao.isSeatFree(seatId, sessionId)).thenReturn(true);
        final boolean seatFreeBySessionId = seatService.isSeatFreeBySessionId(seatId, sessionId);
        Assertions.assertTrue(seatFreeBySessionId);
    }
    @Test
    void isSeatFreeBySessionIdIsFalse() throws DaoException, ServiceException {
        int sessionId = 1;
        int seatId = 1;
        when(seatDao.isSeatFree(seatId, sessionId)).thenReturn(false);
        final boolean seatFreeBySessionId = seatService.isSeatFreeBySessionId(seatId, sessionId);
        Assertions.assertFalse(seatFreeBySessionId);
    }
}
