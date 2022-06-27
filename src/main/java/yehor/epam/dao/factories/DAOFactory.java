package yehor.epam.dao.factories;

import yehor.epam.dao.*;

public interface DAOFactory extends AutoCloseable {
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
    SeatDAO getSeatDao();

    /**
     * Get Session DAO
     *
     * @return Session DAO
     */
    SessionDAO getSessionDao();

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
    UserDAO getUserDao();


}
