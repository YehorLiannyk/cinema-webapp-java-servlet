package yehor.epam.services.impl;

import org.slf4j.Logger;
import yehor.epam.dao.SeatDao;
import yehor.epam.dao.factories.DaoFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Seat;
import yehor.epam.exceptions.EmptyArrayException;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.SeatService;
import yehor.epam.utilities.LoggerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Film
 */
public class SeatServiceImpl implements SeatService {
    private static final Logger logger = LoggerManager.getLogger(SeatServiceImpl.class);
    private static final String CLASS_NAME = SeatServiceImpl.class.getName();

    @Override
    public List<Seat> getSeatListByIdArray(String[] seatIds) throws ServiceException {
        if (seatIds == null || seatIds.length == 0) {
            logger.error("Seat Array is null or empty");
            throw new EmptyArrayException("Seat Array is null or empty");
        }
        List<Seat> seatList = new ArrayList<>();
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SeatDao seatDao = factory.getSeatDao();
            for (String genreId : seatIds) {
                final int id = Integer.parseInt(genreId);
                final Seat seat = seatDao.findById(id);
                seatList.add(seat);
            }
        } catch (Exception e) {
            throwServiceException("Couldn't get seat list", e);
        }
        return seatList;
    }

    @Override
    public List<Seat> getFreeSeatsBySessionId(int id) throws ServiceException {
        List<Seat> seatList = new ArrayList<>();
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SeatDao seatDAO = factory.getSeatDao();
            seatList = seatDAO.findAllFreeSeatBySessionId(id);
        } catch (Exception e) {
            throwServiceException("Couldn't get seat list", e);
        }
        return seatList;
    }

    @Override
    public List<Seat> getAll() throws ServiceException {
        List<Seat> seatList = new ArrayList<>();
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SeatDao seatDAO = factory.getSeatDao();
            seatList = seatDAO.findAll();
        } catch (Exception e) {
            throwServiceException("Couldn't get seat list", e);
        }
        return seatList;
    }

    @Override
    public boolean isSeatFreeBySessionId(int seatId, int sessionId) throws ServiceException {
        boolean isFree = false;
        try (DaoFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SeatDao seatDAO = factory.getSeatDao();
            isFree = seatDAO.isSeatFree(seatId, sessionId);
        } catch (Exception e) {
            throwServiceException("Couldn't get seat list", e);
        }
        return isFree;
    }

    private void logCreatingDaoFactory() {
        logger.debug("Created DAOFactory in " + CLASS_NAME);
    }

    private void throwServiceException(String message, Exception e) throws ServiceException {
        logger.error(message, e);
        throw new ServiceException(message, e);
    }

}
