package yehor.epam.dao;

import yehor.epam.entities.Session;
import yehor.epam.exceptions.DAOException;

import java.util.List;
import java.util.Map;

public interface SessionDAO extends DAO<Session> {
    /**
     * Get SessionList after filtration and sorting on schedule page
     *
     * @param map request's parameterMap contains only filter and sorter params
     * @return sessionList
     */
    List<Session> getFilteredAndSortedSessionList(Map<String, String> map);

    /**
     * Get free seats amount of Session
     *
     * @param session Session
     * @return free seats amount
     */
    int getFreeSeatAmount(Session session);

    /**
     * Get free seats amount of Session
     *
     * @param sessionId Session id
     * @return free seat amount
     */
    int getFreeSeatAmount(int sessionId) throws DAOException;

    /**
     * Decrement Session's free seat amount
     *
     * @param sessionId session id
     * @return true if amount was > 0 and false if is equal to 0
     */
    boolean decrementFreeSeatsAmount(int sessionId) throws DAOException;

    /**
     * Delete session by session id
     *
     * @param sessionId session id
     * @return true if session was deleted and false if not
     */
    boolean delete(int sessionId) throws DAOException;

}
