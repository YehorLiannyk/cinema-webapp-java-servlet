package yehor.epam.services;

import yehor.epam.entities.Film;
import yehor.epam.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface FilmService {
    /**
     * Get all films
     *
     * @return list of films
     * @throws ServiceException
     */
    List<Film> getAll() throws ServiceException;

    /**
     * Get all paginated films
     *
     * @param page page number
     * @param size page size
     * @return list of paginated film
     * @throws ServiceException
     */
    List<Film> getAll(int page, int size) throws ServiceException;

    /**
     * Count total page amount by received size
     *
     * @param size page size
     * @return amount of page
     * @throws ServiceException
     */
    int countTotalPages(int size) throws ServiceException;

    /**
     * Save film
     *
     * @param film film
     * @throws ServiceException
     */
    void save(Film film) throws ServiceException;

    /**
     * Delete film by id
     *
     * @param id film id
     * @throws ServiceException
     */
    void delete(int id) throws ServiceException;

    /**
     * Get film by id
     *
     * @param id film id
     * @return film
     * @throws ServiceException
     */
    Film getById(int id) throws ServiceException;

    /**
     * Get error list after film validation
     *
     * @param filmParamMap map with film params
     * @param genreIds     array of genre ids
     * @return List of validate errors
     */
    List<String> getFilmValidErrorList(Map<String, String> filmParamMap, String[] genreIds);
}
