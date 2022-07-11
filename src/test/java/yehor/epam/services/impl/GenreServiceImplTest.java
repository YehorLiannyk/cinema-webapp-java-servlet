package yehor.epam.services.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import yehor.epam.dao.GenreDAO;
import yehor.epam.dao.factories.DaoFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Genre;
import yehor.epam.exceptions.DaoException;
import yehor.epam.exceptions.EmptyArrayException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.GenreService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class GenreServiceImplTest {

    private static GenreService genreService;
    private static GenreDAO genreDao;
    private static MockedStatic<DaoFactoryDeliver> factoryDeliver;

    @BeforeAll
    static void setup() {
        genreService = spy(GenreServiceImpl.class);
        factoryDeliver = mockStatic(DaoFactoryDeliver.class);
        DaoFactoryDeliver daoFactoryDeliver = mock(DaoFactoryDeliver.class);
        DaoFactory daoFactory = mock(DaoFactory.class);
        genreDao = mock(GenreDAO.class);

        factoryDeliver.when(DaoFactoryDeliver::getInstance).thenReturn(daoFactoryDeliver);
        factoryDeliver.when(() -> DaoFactoryDeliver.getInstance().getFactory()).thenReturn(daoFactory);
        when(daoFactoryDeliver.getFactory()).thenReturn(daoFactory);
        when(daoFactory.getGenreDAO()).thenReturn(genreDao);
    }

    @AfterAll
    static void clean() {
        factoryDeliver.close();
    }

    @Test
    void getGenreListByIdArrayIsNull() throws DaoException, ServiceException {
        String[] genreIds = null;
        try {
            genreService.getGenreListByIdArray(genreIds);
            fail("EmptyArrayException should be thrown");
        } catch (EmptyArrayException e) {
        }
        verifyNoInteractions(genreDao.findById(anyInt()));
    }

    @Test
    void getGenreListByIdArray() throws DaoException, ServiceException {
        String[] genreIds = {"2"};
        Genre genre = mock(Genre.class);
        when(genreDao.findById(anyInt())).thenReturn(genre);
        final List<Genre> list = genreService.getGenreListByIdArray(genreIds);
        assertFalse(list.isEmpty());
    }


    @Test
    void getGenreList() throws DaoException, ServiceException {
        List<Genre> genreList = mock(List.class);
        when(genreDao.findAll()).thenReturn(genreList);
        final List<Genre> all = genreService.getAll();
        Assertions.assertNotNull(all);
        Assertions.assertFalse(all.isEmpty());
    }
}