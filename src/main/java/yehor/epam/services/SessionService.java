package yehor.epam.services;

import yehor.epam.entities.Session;
import yehor.epam.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface SessionService {
    /**
     * Get session by id
     *
     * @param id session id
     * @return Session
     * @throws ServiceException
     */
    Session getById(int id) throws ServiceException;

    /**
     * Delete session by its id
     *
     * @param id session id
     * @throws ServiceException
     */
    void delete(int id) throws ServiceException;

    /**
     * Count total page amount by received size
     *
     * @param size page size
     * @return amount of page
     * @throws ServiceException
     */
    int countTotalPages(int size) throws ServiceException;

    /**
     * Save session
     *
     * @param session session
     * @throws ServiceException
     */
    void save(Session session) throws ServiceException;

    /**
     * Get all paginated session list
     *
     * @param page page number
     * @param size page suze
     * @return list of sessions
     * @throws ServiceException
     */
    List<Session> getAll(int page, int size) throws ServiceException;

    /**
     * Get paginated and filtered list of session by filterSortMap
     *
     * @param filterSortMap map of params for filtering and sorting
     * @param page          page nubmer
     * @param size          page size
     * @return list of sessions
     * @throws ServiceException
     */
    List<Session> getFilteredAndSorted(Map<String, String> filterSortMap, int page, int size) throws ServiceException;

    /**
     * Clean off received parameterMap of non filter/sort parameters
     *
     * @param parameterMap request's parameterMap
     * @return map containing only filter/sort parameters
     */
    Map<String, String> getFilterSortMapFromParams(Map<String, String[]> parameterMap);

    /**
     * Get error list after session validation
     *
     * @param sessionParamMap map with session params
     * @return List of validate errors
     */
    List<String> getSessionValidErrorList(Map<String, String> sessionParamMap);
}
