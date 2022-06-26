package yehor.epam.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import yehor.epam.dao.SeatDAO;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.SessionService;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

/**
 * Class service of Session
 */
public class SessionServiceImpl implements SessionService {
    private static final Logger logger = LoggerManager.getLogger(SessionServiceImpl.class);
    private static final String CLASS_NAME = FilmServiceImpl.class.getName();

    private final SessionService sessionService;

    public SessionServiceImpl() {
        sessionService = new SessionServiceImpl();
    }

    @Override
    public Session getById(int id) throws ServiceException {
        Session session = null;
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDAO sessionDao = factory.getSessionDao();
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
            final SessionDAO sessionDAO = factory.getSessionDao();
            sessionDAO.delete(id);
        } catch (Exception e) {
            throwServiceException("Couldn't delete session", e);
        }
    }

    @Override
    public void addSession(Session session) throws ServiceException {
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SessionDAO sessionDAO = factory.getSessionDao();
            sessionDAO.insert(session);
        } catch (Exception e) {
            throwServiceException("Couldn't add session", e);
        }
    }

    private void logCreatingDaoFactory() {
        logger.debug("Created DAOFactory in " + CLASS_NAME);
    }

    private void throwServiceException(String message, Exception e) throws ServiceException {
        logger.error(message, e);
        throw new ServiceException(message, e);
    }


}
