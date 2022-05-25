package yehor.epam.dao.factories;

import yehor.epam.dao.*;

public interface DAOFactory extends AutoCloseable {
    BasketDAO getBasketDao();

    FilmDAO getFilmDAO();

    GenreDAO getGenreDAO();

    SeatDAO getSeatDao();

    SessionDAO getSessionDao();

    TicketDAO getTicketDao();

    UserDAO getUserDao();


}
