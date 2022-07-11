package yehor.epam.services.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import yehor.epam.dao.SessionDao;
import yehor.epam.dao.factories.DaoFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Session;
import yehor.epam.exceptions.DaoException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.SessionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static yehor.epam.utilities.constants.OtherConstants.*;

class SessionServiceImplTest {
    private static SessionService sessionService;
    private static SessionDao sessionDao;
    private static MockedStatic<DaoFactoryDeliver> factoryDeliver;

    @BeforeAll
    static void setup() {
        sessionService = spy(SessionServiceImpl.class);
        factoryDeliver = mockStatic(DaoFactoryDeliver.class);
        DaoFactoryDeliver daoFactoryDeliver = mock(DaoFactoryDeliver.class);
        DaoFactory daoFactory = mock(DaoFactory.class);
        sessionDao = mock(SessionDao.class);

        factoryDeliver.when(DaoFactoryDeliver::getInstance).thenReturn(daoFactoryDeliver);
        factoryDeliver.when(() -> DaoFactoryDeliver.getInstance().getFactory()).thenReturn(daoFactory);
        when(daoFactoryDeliver.getFactory()).thenReturn(daoFactory);
        when(daoFactory.getSessionDao()).thenReturn(sessionDao);
    }

    @AfterAll
    static void clean() {
        factoryDeliver.close();
    }

    @Test
    void getById() throws DaoException, ServiceException {
        Session session = mock(Session.class);
        when(sessionDao.findById(1)).thenReturn(session);
        final Session byId = sessionService.getById(1);
        assertEquals(session, byId);
    }

    @Test
    void deleteSession() throws ServiceException, DaoException {
        Session session = mock(Session.class);
        sessionService.delete(session.getId());
        verify(sessionDao).delete(session.getId());
    }

    @Test
    void countTotalPages() throws DaoException, ServiceException {
        when(sessionDao.countTotalRow()).thenReturn(4);
        final int countTotalPages = sessionService.countTotalPages(2);
        assertEquals(2, countTotalPages);
    }

    @Test
    void addSession() throws DaoException, ServiceException {
        Session session = mock(Session.class);
        sessionService.save(session);
        verify(sessionDao).insert(session);
    }

    @Test
    void getAll() throws ServiceException, DaoException {
        List<Session> sessionList = mock(List.class);
        when(sessionDao.findAll(anyInt(), anyInt())).thenReturn(sessionList);
        final List<Session> all = sessionService.getAll(anyInt(), anyInt());
        Assertions.assertNotNull(all);
        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    void getFilteredAndSortedSessionList() throws DaoException, ServiceException {
        List<Session> sessionList = mock(List.class);
        Map<String, String> filterSortMap = mock(Map.class);
        when(sessionDao.findFilteredAndSortedSessionList(filterSortMap, 1, 1)).thenReturn(sessionList);
        final List<Session> list = sessionService.getFilteredAndSorted(filterSortMap, 1, 1);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void getFilteredAndSortedSessionListPageBiggerOne() throws DaoException, ServiceException {
        List<Session> sessionList = mock(List.class);
        Map<String, String> filterSortMap = mock(Map.class);
        when(sessionDao.findFilteredAndSortedSessionList(filterSortMap, 2, 1)).thenReturn(sessionList);
        final List<Session> list = sessionService.getFilteredAndSorted(filterSortMap, 2, 1);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void getFilterSortMapFromParams() {
        Map<String, String[]> paramMap = new HashMap<>();
        paramMap.put(SESSION_SORT_BY_PARAM_NAME, new String[1]);
        paramMap.put(SESSION_SORT_METHOD_PARAM_NAME, new String[1]);
        paramMap.put(SESSION_FILTER_SHOW_PARAM_NAME, new String[1]);
        final Map<String, String> map = sessionService.getFilterSortMapFromParams(paramMap);
        Assertions.assertFalse(map.isEmpty());
    }
}