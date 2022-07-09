package yehor.epam.services.impl;

import org.slf4j.Logger;
import yehor.epam.dao.GenreDAO;
import yehor.epam.dao.factories.DaoFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Genre;
import yehor.epam.exceptions.EmptyArrayException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.GenreService;
import yehor.epam.utilities.LoggerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Genre
 */
public class GenreServiceImpl implements GenreService {
    private static final Logger logger = LoggerManager.getLogger(GenreServiceImpl.class);
    private static final String CLASS_NAME = GenreServiceImpl.class.getName();

    @Override
    public List<Genre> getGenreListByIdArray(String[] genreIds) throws ServiceException {
        if (genreIds == null || genreIds.length == 0) {
            logger.error("Genre Array is null or empty");
            throw new EmptyArrayException("Genre Array is null or empty");
        }
        List<Genre> genreList = new ArrayList<>();
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final GenreDAO genreDAO = factory.getGenreDAO();
            for (String genreId : genreIds) {
                final int id = Integer.parseInt(genreId);
                final Genre genre = genreDAO.findById(id);
                genreList.add(genre);
            }
        } catch (Exception e) {
            throwServiceException("Couldn't get genre list", e);
        }
        return genreList;
    }

    @Override
    public List<Genre> getAll() throws ServiceException {
        List<Genre> genreList = new ArrayList<>();
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final GenreDAO genreDAO = factory.getGenreDAO();
            genreList = genreDAO.findAll();
        } catch (Exception e) {
            throwServiceException("Couldn't get genre list", e);
        }
        return genreList;
    }

    private void throwServiceException(String message, Exception e) throws ServiceException {
        logger.error(message, e);
        throw new ServiceException(message, e);
    }

    private void logCreatingDaoFactory() {
        logger.debug("Created DAOFactory in " + CLASS_NAME);
    }

}
