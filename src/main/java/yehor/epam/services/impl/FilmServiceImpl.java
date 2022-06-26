package yehor.epam.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import yehor.epam.dao.FilmDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Film;
import yehor.epam.entities.Genre;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.FilmService;
import yehor.epam.services.GenreService;
import yehor.epam.utilities.LoggerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Film
 */
public class FilmServiceImpl implements FilmService {
    private static final Logger logger = LoggerManager.getLogger(FilmServiceImpl.class);
    private static final String CLASS_NAME = FilmServiceImpl.class.getName();

    private final GenreService genreService;

    public FilmServiceImpl() {
        genreService = new GenreServiceImpl();
    }

    @Override
    public List<Film> getAll() throws ServiceException {
        List<Film> filmList = new ArrayList<>();
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDAO filmDAO = factory.getFilmDAO();
            filmList = filmDAO.findAll();
        } catch (Exception e) {
            throwServiceException("Couldn't get genre list", e);
        }
        return filmList;
    }


    @Override
    public void addFilm(Film film) throws ServiceException {
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDAO filmDAO = factory.getFilmDAO();
            filmDAO.insert(film);
        } catch (Exception e) {
            throwServiceException("Couldn't add film", e);
        }
    }

    @Override
    public void deleteFilm(int id) throws ServiceException {
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDAO filmDAO = factory.getFilmDAO();
            filmDAO.delete(id);
        } catch (Exception e) {
            throwServiceException("Couldn't delete film", e);
        }
    }

    @Override
    public Film getFilmById(int id) throws ServiceException {
        Film film = null;
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDAO filmDAO = factory.getFilmDAO();
            film = filmDAO.findById(id);
        } catch (Exception e) {
            throwServiceException("Couldn't find film", e);
        }
        return film;
    }

    private void logCreatingDaoFactory() {
        logger.debug("Created DAOFactory in " + CLASS_NAME);
    }

    private void throwServiceException(String message, Exception e) throws ServiceException {
        logger.error(message, e);
        throw new ServiceException(message, e);
    }
}
