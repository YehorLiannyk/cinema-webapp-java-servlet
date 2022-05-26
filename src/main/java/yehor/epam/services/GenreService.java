package yehor.epam.services;

import yehor.epam.dao.GenreDAO;
import yehor.epam.dao.exception.DAOException;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.entities.Genre;

import java.util.List;

/**
 * Class-service for Genre entity
 */
public class GenreService {
    public GenreService() {
    }

    /**
     * Get Genre list from DAOFactory
     *
     * @param factory DAOFactory object
     * @return Genre list
     * @throws DAOException
     */
    public List<Genre> getGenreListFromFactory(DAOFactory factory) throws DAOException {
        final GenreDAO genreDAO = factory.getGenreDAO();
        return genreDAO.findAll();
    }
}
