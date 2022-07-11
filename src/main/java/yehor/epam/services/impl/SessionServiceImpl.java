package yehor.epam.services.impl;

import org.slf4j.Logger;
import yehor.epam.dao.SessionDao;
import yehor.epam.dao.factories.DaoFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Session;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.SessionService;
import yehor.epam.services.ValidService;
import yehor.epam.utilities.LoggerManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.OtherConstants.*;

/**
 * Class service of Session
 */
public class SessionServiceImpl implements SessionService {
    private static final Logger logger = LoggerManager.getLogger(SessionServiceImpl.class);
    private static final String CLASS_NAME = SessionServiceImpl.class.getName();
    private final ValidService validService;

    public SessionServiceImpl() {
        this.validService = new ValidServiceImpl();
    }

    public SessionServiceImpl(ValidService validService) {
        this.validService = validService;
    }

    @Override
    public Session getById(int id) throws ServiceException {
        Session session = null;
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDao sessionDao = factory.getSessionDao();
            session = sessionDao.findById(id);
        } catch (Exception e) {
            throwServiceException("Couldn't find session", e);
        }
        return session;
    }

    @Override
    public void delete(int id) throws ServiceException {
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
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
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDao sessionDao = factory.getSessionDao();
            final int count = sessionDao.countTotalRow();
            amount = count / size;
            amount = count % size == 0 ? amount : amount + 1;
        } catch (Exception e) {
            throwServiceException("Couldn't get paginated session list", e);
        }
        return amount;
    }

    @Override
    public void save(Session session) throws ServiceException {
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDao sessionDAO = factory.getSessionDao();
            sessionDAO.insert(session);
        } catch (Exception e) {
            throwServiceException("Couldn't add session", e);
        }
    }

    @Override
    public List<Session> getAll(int page, int size) throws ServiceException {
        List<Session> sessionList = new ArrayList<>();
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
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
    public List<Session> getFilteredAndSorted(Map<String, String> filterSortMap, int page, int size) throws ServiceException {
        List<Session> sessionList = new ArrayList<>();
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            int start = page;
            if (page > 1) {
                start--;
                start = start * size + 1;
            }
            final SessionDao sessionDAO = factory.getSessionDao();
            sessionList = sessionDAO.findFilteredAndSortedSessionList(filterSortMap, start, size);
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

    @Override
    public List<String> getSessionValidErrorList(Map<String, String> sessionParamMap) {
        List<String> errorList = new ArrayList<>();
        //time
        String time = sessionParamMap.get(SESSION_TIME_PARAM);
        validService.validTimeField(errorList, time, MIN_SESSION_TIME, MAX_SESSION_TIME, VALID_TIME_EMPTY, VALID_TIME_INVALID,
                VALID_TIME_RANGE);
        //date
        String date = sessionParamMap.get(SESSION_DATE_PARAM);
        validService.validDateField(errorList, date, LocalDate.now(), LocalDate.now().plusYears(1), VALID_DATE_EMPTY,
                VALID_DATE_INVALID, VALID_DATE_RANGE);
        //ticket price
        String price = sessionParamMap.get(SESSION_PRICE_PARAM);
        validService.validDigitsField(errorList, price, MIN_TICKET_COST, MAX_TICKET_COST,
                VALID_PRICE_EMPTY, VALID_PRICE_INVALID, VALID_PRICE_RANGE);
        // film
        if (sessionParamMap.get(FILM_ID_PARAM) == null)
            errorList.add(VALID_FILM_EMPTY);
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
