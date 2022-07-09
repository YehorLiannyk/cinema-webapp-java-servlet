package yehor.epam.services.impl;

import org.slf4j.Logger;
import yehor.epam.dao.FilmDao;
import yehor.epam.dao.factories.DaoFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Film;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.FilmService;
import yehor.epam.services.ValidService;
import yehor.epam.utilities.LoggerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.OtherConstants.*;

/**
 * Service class for Film
 */
public class FilmServiceImpl implements FilmService {
    private static final Logger logger = LoggerManager.getLogger(FilmServiceImpl.class);
    private static final String CLASS_NAME = FilmServiceImpl.class.getName();
    private final ValidService validService;

    public FilmServiceImpl() {
        this.validService = new ValidServiceImpl();
    }

    public FilmServiceImpl(ValidService validService) {
        this.validService = validService;
    }

    @Override
    public List<Film> getAll() throws ServiceException {
        List<Film> filmList = new ArrayList<>();
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
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
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
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
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
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
    public void save(Film film) throws ServiceException {
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDao filmDAO = factory.getFilmDAO();
            filmDAO.insert(film);
        } catch (Exception e) {
            throwServiceException("Couldn't save film", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDao filmDAO = factory.getFilmDAO();
            filmDAO.delete(id);
        } catch (Exception e) {
            throwServiceException("Couldn't delete film", e);
        }
    }

    @Override
    public Film getById(int id) throws ServiceException {
        Film film = null;
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final FilmDao filmDAO = factory.getFilmDAO();
            film = filmDAO.findById(id);
        } catch (Exception e) {
            throwServiceException("Couldn't find film", e);
        }
        return film;
    }

    @Override
    public List<String> getFilmValidErrorList(Map<String, String> filmParamMap, final String[] genreIds) {
        List<String> errorList = new ArrayList<>();
        //name
        String name = filmParamMap.get(FILM_NAME_PARAM);
        validService.validStringField(errorList, name, 1, MAX_FILM_NAME_LENGTH, VALID_FILM_NAME_EMPTY,
                VALID_FILM_DESC_LENGTH);
        //description
        String description = filmParamMap.get(FILM_DESCR_PARAM);
        validService.validNullableStringField(errorList, description, MAX_FILM_DESC_LENGTH, VALID_FILM_DESC_LENGTH);
        //poster url
        String posterUrl = filmParamMap.get(POSTER_URL_PARAM);
        validService.validStringField(errorList, posterUrl, 1, MAX_URL_LENGTH, VALID_URL_EMPTY,
                VALID_URL_LENGTH);
        //duration
        String duration = filmParamMap.get(FILM_DURATION_PARAM);
        validService.validDigitsField(errorList, duration, MIN_FILM_DURATION_IN_MINUTE, MAX_FILM_DURATION_IN_MINUTE,
                VALID_DURATION_EMPTY, ONLY_DIGITS_PATTERN, VALID_DURATION_RANGE);
        // genre list
        if (genreIds == null || genreIds.length == 0)
            errorList.add(VALID_GENRE_LIST_EMPTY);
        return errorList;
    }

    private void logCreatingDaoFactory() {
        logger.debug("Created DAOFactory in " + CLASS_NAME);
    }

    private void throwServiceException(String message, Exception e) throws ServiceException {
        logger.error(message, e);
        throw new ServiceException(message, e);
    }
}
