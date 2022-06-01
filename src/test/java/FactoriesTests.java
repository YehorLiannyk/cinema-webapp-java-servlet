import org.junit.Test;
import yehor.epam.dao.*;
import yehor.epam.dao.exception.AuthException;
import yehor.epam.dao.exception.DAOException;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.dao.mysql.*;
import yehor.epam.entities.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class FactoriesTests {
    @Test
    public void baseDAOTest() {
        BaseDAO baseDAO = spy(BaseDAO.class);
        Connection connection = mock(Connection.class);
        when(baseDAO.getConnection()).thenReturn(connection);
        baseDAO.setConnection(connection);
        assertEquals(connection, baseDAO.getConnection());
    }

    @Test
    public void mySQLFactoryTest() {
        MySQLFactory factory = mock(MySQLFactory.class);
        when(factory.getUserDao()).thenReturn(mock(UserDAO.class));
        when(factory.getTicketDao()).thenReturn(mock(TicketDAO.class));
        when(factory.getSessionDao()).thenReturn(mock(SessionDAO.class));
        when(factory.getSeatDao()).thenReturn(mock(SeatDAO.class));
        when(factory.getFilmDAO()).thenReturn(mock(FilmDAO.class));

        factory = spy(MySQLFactory.class);
        factory.getFilmDAO();
        verify(factory, times(1)).getFilmDAO();
        factory.getGenreDAO();
        verify(factory, times(1)).getGenreDAO();
        factory.getSessionDao();
        verify(factory, times(1)).getSessionDao();
        factory.getUserDao();
        verify(factory, times(1)).getUserDao();
        factory.getSeatDao();
        verify(factory, times(1)).getSeatDao();
        factory.getTicketDao();
        verify(factory, times(1)).getTicketDao();
    }


    @Test
    public void mySQLUserDAOTest() throws AuthException {
        MySQLUserDAO userDAO = mock(MySQLUserDAO.class);
        User user = mock(User.class);
        when(userDAO.getUser("login")).thenReturn(mock(User.class));
        when(userDAO.getUser("login")).thenThrow(DAOException.class);
        when(userDAO.findAll()).thenReturn(mock(List.class));
        when(userDAO.findAll()).thenThrow(DAOException.class);
        when(userDAO.update(user)).thenReturn(user);
        when(userDAO.update(user)).thenThrow(DAOException.class);
        when(userDAO.insert(user)).thenReturn(true);
        when(userDAO.insert(user)).thenReturn(false);
        when(userDAO.insert(user)).thenThrow(DAOException.class);
        when(userDAO.getSaltAndPassByLogin("login")).thenReturn(mock(Map.class));
        when(userDAO.getSaltAndPassByLogin("login")).thenThrow(DAOException.class);
        when(userDAO.getMaxId()).thenReturn(1);
        when(userDAO.getMaxId()).thenThrow(DAOException.class);
        when(userDAO.delete(user)).thenReturn(true);
        when(userDAO.delete(user)).thenReturn(false);
        when(userDAO.delete(user)).thenThrow(DAOException.class);
        when(userDAO.findById(1)).thenReturn(user);
        when(userDAO.findById(1)).thenThrow(DAOException.class);
        userDAO = spy(MySQLUserDAO.class);
        userDAO.findAll();
        verify(userDAO,times(1)).findAll();
        userDAO.delete(user);
        verify(userDAO,times(1)).delete(user);
        userDAO.update(user);
        verify(userDAO,times(1)).update(user);
    }

    @Test
    public void mySQLTicketDAOTest() {
        MySQLTicketDAO ticketDAO = mock(MySQLTicketDAO.class);
        Ticket ticket = mock(Ticket.class);
        when(ticketDAO.findAll()).thenReturn(mock(List.class));
        when(ticketDAO.findAll()).thenThrow(DAOException.class);
        when(ticketDAO.findAllByUserId(1)).thenReturn(mock(List.class));
        when(ticketDAO.findAllByUserId(1)).thenThrow(DAOException.class);
        when(ticketDAO.update(ticket)).thenReturn(ticket);
        when(ticketDAO.update(ticket)).thenThrow(DAOException.class);
        when(ticketDAO.insert(ticket)).thenReturn(true);
        when(ticketDAO.insert(ticket)).thenReturn(false);
        when(ticketDAO.insert(ticket)).thenThrow(DAOException.class);
        when(ticketDAO.delete(ticket)).thenReturn(true);
        when(ticketDAO.delete(ticket)).thenReturn(false);
        when(ticketDAO.delete(ticket)).thenThrow(DAOException.class);
        when(ticketDAO.findById(1)).thenReturn(ticket);
        when(ticketDAO.findById(1)).thenThrow(DAOException.class);
        ticketDAO = spy(MySQLTicketDAO.class);
        ticketDAO.findAll();
        verify(ticketDAO,times(1)).findAll();
        ticketDAO.delete(ticket);
        verify(ticketDAO,times(1)).delete(ticket);
        ticketDAO.update(ticket);
        verify(ticketDAO,times(1)).update(ticket);

    }

    @Test
    public void mySQLSessionDAOTest() {
        MySQLSessionDAO sessionDAO = mock(MySQLSessionDAO.class);
        Session session = mock(Session.class);
        when(sessionDAO.insert(session)).thenReturn(true);
        when(sessionDAO.insert(session)).thenReturn(false);
        when(sessionDAO.findById(1)).thenReturn(session);
        when(sessionDAO.findById(1)).thenThrow(DAOException.class);
        when(sessionDAO.findAll()).thenReturn(mock(List.class));
        when(sessionDAO.update(session)).thenReturn(session);
        when(sessionDAO.delete(session)).thenReturn(true);
        when(sessionDAO.delete(session)).thenReturn(false);
        when(sessionDAO.delete(1)).thenReturn(true);
        when(sessionDAO.delete(1)).thenReturn(false);
        when(sessionDAO.decrementFreeSeatsAmount(1)).thenReturn(true);
        when(sessionDAO.decrementFreeSeatsAmount(1)).thenReturn(false);
        when(sessionDAO.decrementFreeSeatsAmount(1)).thenThrow(DAOException.class);
        when(sessionDAO.getFilteredAndSortedSessionList(mock(Map.class))).thenReturn(mock(List.class));
        when(sessionDAO.getFreeSeatAmount(session)).thenReturn(1);
        when(sessionDAO.getFreeSeatAmount(session)).thenReturn(0);
        when(sessionDAO.getFreeSeatAmount(session)).thenThrow(DAOException.class);
        when(sessionDAO.getFreeSeatAmount(1)).thenReturn(1);
        when(sessionDAO.getFreeSeatAmount(0)).thenReturn(0);
        when(sessionDAO.getFreeSeatAmount(0)).thenThrow(DAOException.class);
        sessionDAO = spy(MySQLSessionDAO.class);
        sessionDAO.update(session);
        verify(sessionDAO,times(1)).update(session);
    }

    @Test
    public void mySQLSeatDAOTest() {
        MySQLSeatDAO seatDAO = mock(MySQLSeatDAO.class);
        Seat seat = mock(Seat.class);
        Session session = mock(Session.class);
        when(seatDAO.findAll()).thenReturn(mock(List.class));
        when(seatDAO.update(seat)).thenReturn(seat);
        when(seatDAO.insert(seat)).thenReturn(true);
        when(seatDAO.insert(seat)).thenReturn(false);
        when(seatDAO.delete(seat)).thenReturn(true);
        when(seatDAO.delete(seat)).thenReturn(false);
        when(seatDAO.findById(1)).thenReturn(seat);
        when(seatDAO.findById(1)).thenThrow(DAOException.class);
        when(seatDAO.getAllSeatsAmount()).thenReturn(0);
        when(seatDAO.getAllSeatsAmount()).thenReturn(10);
        when(seatDAO.getFreeSeatsAmountBySessionId(1)).thenReturn(10);
        when(seatDAO.getFreeSeatsAmountBySessionId(1)).thenReturn(0);
        when(seatDAO.isSeatFree(10, 1)).thenReturn(true);
        when(seatDAO.isSeatFree(10, 1)).thenReturn(false);
        when(seatDAO.findAllFreeSeatBySessionId(1)).thenReturn(mock(List.class));
        when(seatDAO.reserveSeatBySession(seat, session)).thenReturn(true);
        when(seatDAO.reserveSeatBySession(seat, session)).thenReturn(false);
        seatDAO = spy(MySQLSeatDAO.class);
        seatDAO.insert(seat);
        verify(seatDAO,times(1)).insert(seat);
        seatDAO.delete(seat);
        verify(seatDAO,times(1)).delete(seat);
        seatDAO.update(seat);
        verify(seatDAO,times(1)).update(seat);
    }

    @Test
    public void mySQLGenreDAOTest() throws SQLException {
        MySQLGenreDAO genreDAO = mock(MySQLGenreDAO.class);
        Genre genre = mock(Genre.class);
        when(genreDAO.findAll()).thenReturn(mock(List.class));
        when(genreDAO.update(genre)).thenReturn(genre);
        when(genreDAO.insert(genre)).thenReturn(true);
        when(genreDAO.insert(genre)).thenReturn(false);
        when(genreDAO.delete(genre)).thenReturn(true);
        when(genreDAO.delete(genre)).thenReturn(false);
        when(genreDAO.findById(1)).thenReturn(genre);
        when(genreDAO.findById(1)).thenThrow(DAOException.class);
        when(genreDAO.insertFilmGenres(1, mock(List.class))).thenReturn(true);
        when(genreDAO.insertFilmGenres(1, mock(List.class))).thenReturn(false);
        when(genreDAO.insertFilmGenres(1, mock(List.class))).thenThrow(SQLException.class);
        when(genreDAO.getGenreListOfFilm(1)).thenReturn(mock(List.class));
        when(genreDAO.getGenreListOfFilm(1)).thenThrow(DAOException.class);
        genreDAO = spy(MySQLGenreDAO.class);
        genreDAO.insert(genre);
        verify(genreDAO,times(1)).insert(genre);
        genreDAO.delete(genre);
        verify(genreDAO,times(1)).delete(genre);
        genreDAO.update(genre);
        verify(genreDAO,times(1)).update(genre);
    }

    @Test
    public void mySQLFilmDAOTest() {
        MySQLFilmDAO filmDAO = mock(MySQLFilmDAO.class);
        Film film = mock(Film.class);
        when(filmDAO.insert(film)).thenReturn(true);
        when(filmDAO.insert(film)).thenReturn(false);
        when(filmDAO.findById(1)).thenReturn(film);
        when(filmDAO.findById(1)).thenThrow(DAOException.class);
        when(filmDAO.findAll()).thenReturn(mock(List.class));
        when(filmDAO.update(film)).thenReturn(film);
        when(filmDAO.delete(film)).thenReturn(true);
        when(filmDAO.delete(film)).thenReturn(false);
        when(filmDAO.delete(1)).thenReturn(true);
        when(filmDAO.delete(1)).thenReturn(false);
        filmDAO = spy(MySQLFilmDAO.class);
        filmDAO.update(film);
        verify(filmDAO,times(1)).update(film);
    }

    @Test
    public void daoTest() {
        BaseEntity entity = mock(BaseEntity.class);
        DAO dao = mock(DAO.class);
        when(dao.findAll()).thenReturn(mock(List.class));
        when(dao.findById(anyInt())).thenReturn(entity);
        when(dao.delete(entity)).thenReturn(true);
        when(dao.delete(entity)).thenReturn(false);
        when(dao.update(entity)).thenReturn(entity);
        when(dao.insert(entity)).thenReturn(true);
        when(dao.insert(entity)).thenReturn(false);
    }

}
