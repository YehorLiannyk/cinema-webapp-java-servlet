package yehor.epam.services.impl;

import org.apache.log4j.Logger;
import yehor.epam.dao.SeatDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.DaoFactoryDeliver;
import yehor.epam.entities.Seat;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.GenreService;
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

    private final GenreService genreService;

    public SeatServiceImpl() {
        genreService = new GenreServiceImpl();
    }

    @Override
    public List<Seat> getFreeSeatsBySessionId(int id) throws ServiceException {
        List<Seat> seatList = new ArrayList<>();
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SeatDAO seatDAO = factory.getSeatDao();
            seatList = seatDAO.findAllFreeSeatBySessionId(id);
        } catch (Exception e) {
            throwServiceException("Couldn't get seat list", e);
        }
        return seatList;
    }

    @Override
    public List<Seat> getAll() throws ServiceException {
        List<Seat> seatList = new ArrayList<>();
        try (DAOFactory factory = DaoFactoryDeliver.getInstance().getFactory()) {
            logCreatingDaoFactory();
            final SeatDAO seatDAO = factory.getSeatDao();
            seatList = seatDAO.findAll();
        } catch (Exception e) {
            throwServiceException("Couldn't get seat list", e);
        }
        return seatList;
    }

    private void logCreatingDaoFactory() {
        logger.debug("Created DAOFactory in " + CLASS_NAME);
    }

    private void throwServiceException(String message, Exception e) throws ServiceException {
        logger.error(message, e);
        throw new ServiceException(message, e);
    }

}
