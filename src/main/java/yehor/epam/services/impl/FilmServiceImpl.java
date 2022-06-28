package yehor.epam.services.impl;

import org.slf4j.Logger;
import yehor.epam.dao.FilmDao;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Film;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.FilmService;
import yehor.epam.utilities.LoggerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Film
 */
public class FilmServiceImpl implements FilmService {
    private static final Logger logger = LoggerManager.getLogger(FilmServiceImpl.class);
    private static final String CLASS_NAME = FilmServiceImpl.class.getName();

    @Override
    public List<Film> getAll() throws ServiceException {
        List<Film> filmList = new ArrayList<>();
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDao filmDAO = factory.getFilmDAO();
            filmList = filmDAO.findAll();
        } catch (Exception e) {
            throwServiceException("Couldn't get film list", e);
        }
        return filmList;
    }

    @Override
    public List<Film> getAll(int page, int size) throws ServiceException {
        List<Film> filmList = new ArrayList<>();
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDao filmDAO = factory.getFilmDAO();
            int start = page;
            if (page > 1) {
                start--;
                start = start * size + 1;
            }
            filmList = filmDAO.findAll(start, size);
        } catch (Exception e) {
            throwServiceException("Couldn't get paginated film list", e);
        }
        return filmList;
    }

    @Override
    public int countTotalPages(int size) throws ServiceException {
        int amount = 0;
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDao filmDAO = factory.getFilmDAO();
            final int count = filmDAO.countTotalRow();
            amount = count / size;
            amount = count % size == 0 ? amount : amount + 1;
        } catch (Exception e) {
            throwServiceException("Couldn't get paginated film list", e);
        }
        return amount;
    }



    @Override
    public void saveFilm(Film film) throws ServiceException {
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDao filmDAO = factory.getFilmDAO();
            filmDAO.insert(film);
        } catch (Exception e) {
            throwServiceException("Couldn't save film", e);
        }
    }

    @Override
    public void deleteFilm(int id) throws ServiceException {
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDao filmDAO = factory.getFilmDAO();
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
            final FilmDao filmDAO = factory.getFilmDAO();
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
