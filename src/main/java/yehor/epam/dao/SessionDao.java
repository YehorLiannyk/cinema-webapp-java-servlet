package yehor.epam.dao;

import yehor.epam.entities.Session;
import yehor.epam.exceptions.DaoException;

import java.util.List;
import java.util.Map;

public interface SessionDao extends DAO<Session>, PaginatableDao<Session> {
    /**
     * Get SessionList after filtration and sorting on schedule page
     *
     * @param map request's parameterMap contains only filter and sorter params
     * @return sessionList
     */
    List<Session> findFilteredAndSortedSessionList(Map<String, String> map, int start, int size) throws DaoException;

    /**
     * Get free seats amount of Session
     *
     * @param session Session
     * @return free seats amount
     */
    int getFreeSeatAmount(Session session) throws DaoException;

    /**
     * Get free seats amount of Session
     *
     * @param sessionId Session id
     * @return free seat amount
     */
    int getFreeSeatAmount(int sessionId) throws DaoException;

    /**
     * Decrement Session's free seat amount
     *
     * @param sessionId session id
     * @return true if amount was > 0 and false if is equal to 0
     */
    boolean decrementFreeSeatsAmount(int sessionId) throws DaoException;

    /**
     * Delete session by session id
     *
     * @param sessionId session id
     * @return true if session was deleted and false if not
     */
    boolean delete(int sessionId) throws DaoException;

}
