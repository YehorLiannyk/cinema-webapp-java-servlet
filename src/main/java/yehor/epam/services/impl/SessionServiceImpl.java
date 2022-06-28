package yehor.epam.services.impl;

import org.slf4j.Logger;
import yehor.epam.dao.FilmDao;
import yehor.epam.dao.SessionDao;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Session;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.SessionService;
import yehor.epam.utilities.LoggerManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.OtherConstants.*;
import static yehor.epam.utilities.constants.OtherConstants.SESSION_FILTER_SHOW_PARAM_NAME;

/**
 * Class service of Session
 */
public class SessionServiceImpl implements SessionService {
    private static final Logger logger = LoggerManager.getLogger(SessionServiceImpl.class);
    private static final String CLASS_NAME = SessionServiceImpl.class.getName();

    @Override
    public Session getById(int id) throws ServiceException {
        Session session = null;
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDao sessionDao = factory.getSessionDao();
            session = sessionDao.findById(id);
        } catch (Exception e) {
            throwServiceException("Couldn't find session", e);
        }
        return session;
    }

    @Override
    public void deleteSession(int id) throws ServiceException {
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDao sessionDAO = factory.getSessionDao();
            sessionDAO.delete(id);
        } catch (Exception e) {
            throwServiceException("Couldn't delete session", e);
        }
    }

    @Override
    public int countTotalPages(int size) throws ServiceException {
        int amount = 0;
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDao sessionDao = factory.getSessionDao();
            amount = sessionDao.countTotalRow() / size + 1;
        } catch (Exception e) {
            throwServiceException("Couldn't get paginated session list", e);
        }
        return amount;
    }

    @Override
    public void addSession(Session session) throws ServiceException {
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDao sessionDAO = factory.getSessionDao();
            sessionDAO.insert(session);
        } catch (Exception e) {
            throwServiceException("Couldn't add session", e);
        }
    }

    @Override
    public List<Session> getAll() throws ServiceException {
        List<Session> sessionList = new ArrayList<>();
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDao sessionDAO = factory.getSessionDao();
            sessionList = sessionDAO.findAll();
        } catch (Exception e) {
            throwServiceException("Couldn't get session list", e);
        }
        return sessionList;
    }

    @Override
    public List<Session> getAll(int page, int size) throws ServiceException {
        List<Session> sessionList = new ArrayList<>();
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDao sessionDao = factory.getSessionDao();
            int start = page;
            if (page > 1) {
                start--;
                start = start * size + 1;
            }
            sessionList = sessionDao.findAll(start, size);
        } catch (Exception e) {
            throwServiceException("Couldn't get paginated session list", e);
        }
        return sessionList;
    }

    @Override
    public List<Session> getFilteredAndSortedSessionList(Map<String, String> filterSortMap) throws ServiceException {
        List<Session> sessionList = new ArrayList<>();
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDao sessionDAO = factory.getSessionDao();
            sessionList = sessionDAO.findFilteredAndSortedSessionList(filterSortMap);
        } catch (Exception e) {
            throwServiceException("Couldn't get session list", e);
        }
        return sessionList;
    }

   @Override
    public Map<String, String> getFilterSortMapFromParams(Map<String, String[]> parameterMap) {
        final Map<String, String> filterSortMap = new HashMap<>();

        final boolean hasSortBy = parameterMap.containsKey(SESSION_SORT_BY_PARAM_NAME);
        final boolean hasSortMethod = parameterMap.containsKey(SESSION_SORT_METHOD_PARAM_NAME);
        final boolean hasFilterShow = parameterMap.containsKey(SESSION_FILTER_SHOW_PARAM_NAME);

        if (hasSortBy && hasSortMethod && hasFilterShow) {
            filterSortMap.put(SESSION_SORT_BY_PARAM_NAME, parameterMap.get(SESSION_SORT_BY_PARAM_NAME)[0]);
            filterSortMap.put(SESSION_SORT_METHOD_PARAM_NAME, parameterMap.get(SESSION_SORT_METHOD_PARAM_NAME)[0]);
            filterSortMap.put(SESSION_FILTER_SHOW_PARAM_NAME, parameterMap.get(SESSION_FILTER_SHOW_PARAM_NAME)[0]);
        }
        logger.info("Finished to form filterSort parameter map");
        return filterSortMap;
    }

    private void logCreatingDaoFactory() {
        logger.debug("Created DAOFactory in " + CLASS_NAME);
    }

    private void throwServiceException(String message, Exception e) throws ServiceException {
        logger.error(message, e);
        throw new ServiceException(message, e);
    }


}
