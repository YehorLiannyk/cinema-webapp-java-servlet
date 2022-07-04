package yehor.epam.services.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import yehor.epam.dao.FilmDao;
import yehor.epam.dao.UserDao;
import yehor.epam.dao.factories.DaoFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Film;
import yehor.epam.entities.User;
import yehor.epam.exceptions.AuthException;
import yehor.epam.exceptions.DaoException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.UserService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    private static UserService userService;
    private static UserDao userDao;
    private static MockedStatic<DaoFactoryDeliver> factoryDeliver;


    @BeforeAll
    static void setup() {
        userService = spy(UserServiceImpl.class);
        factoryDeliver = mockStatic(DaoFactoryDeliver.class);
        DaoFactoryDeliver daoFactoryDeliver = mock(DaoFactoryDeliver.class);
        DaoFactory daoFactory = mock(DaoFactory.class);
        userDao = mock(UserDao.class);

        factoryDeliver.when(DaoFactoryDeliver::getInstance).thenReturn(daoFactoryDeliver);
        factoryDeliver.when(() -> DaoFactoryDeliver.getInstance().getFactory()).thenReturn(daoFactory);
        when(daoFactoryDeliver.getFactory()).thenReturn(daoFactory);
        when(daoFactory.getUserDao()).thenReturn(userDao);
    }

    @AfterAll
    static void clean() {
        factoryDeliver.close();
    }

    @Test
    void authenticateUser() throws ServiceException, AuthException, DaoException {
        String login = "login";
        String salt = "salt";
        String password = "pass";
        Map<String, String> saltAndPass = mock(Map.class);
        when(userDao.getSaltAndPassByLogin(login)).thenReturn(saltAndPass);
        when(saltAndPass.get("salt")).thenReturn(salt);
        when(saltAndPass.get("password")).thenReturn(password);
        try {
            userService.authenticateUser(login, password);
            fail("AuthException should be thrown");
        } catch (AuthException e) {}
    }

    @Test
    void getUserByLogin() throws ServiceException, AuthException, DaoException {
        User user = mock(User.class);
        when(userDao.getUserByLogin(anyString())).thenReturn(user);
        final User userByLogin = userService.getUserByLogin(anyString());
        Assertions.assertEquals(user, userByLogin);
    }

    @Test
    void getById() throws DaoException, ServiceException {
        User user = mock(User.class);
        when(userDao.findById(1)).thenReturn(user);
        final User us = userService.getById(1);
        assertEquals(user, us);
    }

    @Test
    void getMaxId() throws DaoException, ServiceException {
        when(userDao.getMaxId()).thenReturn(2);
        final int maxId = userService.getMaxId();
        assertEquals(2, maxId);
    }

    @Test
    void save() throws ServiceException, DaoException {
        User user = mock(User.class);
        userService.save(user);
        verify(userDao).insert(user);
    }
}