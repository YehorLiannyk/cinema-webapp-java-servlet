package yehor.epam.dao.factories;

import yehor.epam.dao.*;

public interface DaoFactory extends AutoCloseable {
    /**
     * Get Film DAO
     *
     * @return Film DAO
     */
    FilmDao getFilmDAO();

    /**
     * Get Genre DAO
     *
     * @return Genre DAO
     */
    GenreDAO getGenreDAO();

    /**
     * Get Seat DAO
     *
     * @return Seat DAO
     */
    SeatDao getSeatDao();

    /**
     * Get Session DAO
     *
     * @return Session DAO
     */
    SessionDao getSessionDao();

    /**
     * Get Ticket DAO
     *
     * @return Ticket DAO
     */
    TicketDao getTicketDao();

    /**
     * Get User DAO
     *
     * @return User DAO
     */
    UserDao getUserDao();


}
