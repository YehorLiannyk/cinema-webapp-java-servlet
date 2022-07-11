package yehor.epam.services.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import yehor.epam.dao.FilmDao;
import yehor.epam.dao.factories.DaoFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Film;
import yehor.epam.exceptions.DaoException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.FilmService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FilmServiceImplTest {
    private static FilmService filmService;
    private static FilmDao filmDao;
    private static MockedStatic<DaoFactoryDeliver> factoryDeliver;


    @BeforeAll
    static void setup() {
        filmService = spy(FilmServiceImpl.class);
         factoryDeliver = mockStatic(DaoFactoryDeliver.class);
        DaoFactoryDeliver daoFactoryDeliver = mock(DaoFactoryDeliver.class);
        DaoFactory daoFactory = mock(DaoFactory.class);
        filmDao = mock(FilmDao.class);

        factoryDeliver.when(DaoFactoryDeliver::getInstance).thenReturn(daoFactoryDeliver);
        factoryDeliver.when(() -> DaoFactoryDeliver.getInstance().getFactory()).thenReturn(daoFactory);
        when(daoFactoryDeliver.getFactory()).thenReturn(daoFactory);
        when(daoFactory.getFilmDAO()).thenReturn(filmDao);
    }

    @AfterAll
    static void clean() {
        factoryDeliver.close();
    }

    @Test
    void getAll() throws ServiceException, DaoException {
        List<Film> filmList = mock(List.class);
        when(filmDao.findAll()).thenReturn(filmList);
        final List<Film> all = filmService.getAll();
        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    void getAllPaginated() throws DaoException, ServiceException {
        List<Film> filmList = mock(List.class);

        when(filmDao.findAll(anyInt(), anyInt())).thenReturn(filmList);
        final List<Film> all = filmService.getAll(anyInt(), anyInt());

        Assertions.assertNotNull(all);
        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    void getAllPaginatedPageBiggerOne() throws DaoException, ServiceException {
        List<Film> filmList = mock(List.class);
        when(filmDao.findAll(2, 1)).thenReturn(filmList);
        final List<Film> all = filmService.getAll(2, 1);
        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    void countTotalPages() throws DaoException, ServiceException {
        when(filmDao.countTotalRow()).thenReturn(4);
        final int countTotalPages = filmService.countTotalPages(2);
        assertEquals(2, countTotalPages);
    }

    @Test
    void saveFilm() throws DaoException, ServiceException {
        Film film = mock(Film.class);
        filmService.save(film);
        verify(filmDao).insert(film);
    }

    @Test
    void deleteFilmById() throws DaoException, ServiceException {
        Film film = mock(Film.class);
        filmService.delete(film.getId());
        verify(filmDao).delete(film.getId());
    }

    @Test
    void getFilmById() throws ServiceException, DaoException {
        Film film = mock(Film.class);
        when(filmDao.findById(1)).thenReturn(film);
        final Film filmById = filmService.getById(1);
        assertEquals(film, filmById);
    }
}