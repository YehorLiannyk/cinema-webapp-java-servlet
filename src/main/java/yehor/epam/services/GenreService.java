package yehor.epam.services;

import yehor.epam.entities.Genre;
import yehor.epam.exceptions.ServiceException;

import java.util.List;

public interface GenreService {
    /**
     * Get genres by its ids
     *
     * @param genreIds array of genre ids
     * @return list of genres
     * @throws ServiceException
     */
    List<Genre> getGenreListByIdArray(String[] genreIds) throws ServiceException;

    /**
     * Get all genres
     * @return list of all genres
     * @throws ServiceException
     */
    List<Genre> getAll() throws ServiceException;

}
