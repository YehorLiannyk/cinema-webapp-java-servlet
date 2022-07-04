package yehor.epam.entities;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EntitiesTests {
    @Test
    public void baseEntityTest() {
        BaseEntity entity = spy(BaseEntity.class);
        entity.setId(1);
        assertEquals(1, entity.getId());
        verify(entity, times(1)).setId(1);
        when(entity.getId()).thenReturn(1);
    }

    @Test
    public void filmTest() {
        Film film = spy(Film.class);

        film.setPosterUrl("url");
        assertEquals("url", film.getPosterUrl());

        film.setName("name");
        verify(film, times(1)).setName("name");
        assertEquals("name", film.getName());

        film.setDescription("description");
        verify(film, times(1)).setDescription("description");
        assertEquals("description", film.getDescription());

        final Duration duration = Duration.ofMinutes(10);
        film.setDuration(duration);
        verify(film, times(1)).setDuration(duration);
        assertEquals(duration, film.getDuration());
        assertEquals(10, film.getDurationInMinutes());
        verify(film, times(1)).getDurationInMinutes();

        final List genreList = mock(List.class);
        film.setGenreList(genreList);
        assertEquals(genreList, film.getGenreList());
    }

    @Test
    public void GenreTest() {
        Genre genre = spy(Genre.class);
        genre.setName("name");
        verify(genre, times(1)).setName("name");
        assertEquals("name", genre.getName());
    }

    @Test
    public void seatTest() {
        Seat seat = spy(Seat.class);

        seat.setPlaceNumber(1);
        assertEquals(1, seat.getPlaceNumber());

        seat.setRowNumber(2);
        verify(seat, times(1)).setRowNumber(2);
        assertEquals(2, seat.getRowNumber());
    }

    @Test
    public void sessionTest() {
        Session session = spy(Session.class);

        session.setTicketPrice(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, session.getTicketPrice());

        session.setSeatsAmount(10);
        verify(session, times(1)).setSeatsAmount(10);
        assertEquals(10, session.getSeatsAmount());

        session.setTime(LocalTime.NOON);
        verify(session, times(1)).setTime(LocalTime.NOON);
        assertEquals(LocalTime.NOON, session.getTime());

        session.setDate(LocalDate.EPOCH);
        verify(session, times(1)).setDate(LocalDate.EPOCH);
        assertEquals(LocalDate.EPOCH, session.getDate());

        final Film film = mock(Film.class);
        session.setFilm(film);
        verify(session, times(1)).setFilm(film);
        assertEquals(film, session.getFilm());
    }

    @Test
    public void ticketTest() {
        Ticket ticket = spy(Ticket.class);
        Session session = mock(Session.class);
        User user = mock(User.class);
        Seat seat = mock(Seat.class);

        ticket.setTicketPrice(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, ticket.getTicketPrice());

        ticket.setUser(user);
        verify(ticket, times(1)).setUser(user);
        assertEquals(user, ticket.getUser());

        ticket.setSession(session);
        verify(ticket, times(1)).setSession(session);
        assertEquals(session, ticket.getSession());

        ticket.setSeat(seat);
        verify(ticket, times(1)).setSeat(seat);
        assertEquals(seat, ticket.getSeat());
    }

    @Test
    public void userTest() {
        User user = spy(User.class);

        final String fName = "fName";
        user.setFirstName(fName);
        verify(user, times(1)).setFirstName(fName);
        assertEquals(fName, user.getFirstName());

        final String sName = "sName";
        user.setLastName(sName);
        verify(user, times(1)).setLastName(sName);
        assertEquals(sName, user.getLastName());

        final String salt = "salt";
        user.setSalt(salt);
        verify(user, times(1)).setSalt(salt);
        assertEquals(salt, user.getSalt());

        final String pass = "pass";
        user.setPassword(pass);
        verify(user, times(1)).setPassword(pass);
        assertEquals(pass, user.getPassword());

        user.setNotification(true);
        verify(user, times(1)).setNotification(true);
        assertTrue(user.getNotification());

        final String email = "email";
        user.setEmail(email);
        verify(user, times(1)).setEmail(email);
        assertEquals(email, user.getEmail());

        final String number = "number";
        user.setPhoneNumber(number);
        verify(user, times(1)).setPhoneNumber(number);
        assertEquals(number, user.getPhoneNumber());

        User.Role role = User.Role.USER;
        user.setUserRole(role);
        verify(user, times(1)).setUserRole(role);
        assertEquals(role, user.getUserRole());
        assertEquals("USER", role.toString());

        role = User.Role.GUEST;
        user.setUserRole(role);
        verify(user, times(1)).setUserRole(role);
        assertEquals(role, user.getUserRole());
        assertEquals("GUEST", role.toString());

        role = User.Role.ADMIN;
        user.setUserRole(role);
        verify(user, times(1)).setUserRole(role);
        assertEquals(role, user.getUserRole());
        assertEquals("ADMIN", role.toString());
    }

    @Test
    public void userConstructorsTest() {
        final int id = 1;
        final String fName = "fName";
        final String sName = "sName";
        final String salt = "salt";
        final String email = "email";
        final String number = "number";
        final String pass = "pass";
        final boolean notification = true;
        User.Role role = User.Role.USER;


        User user = new User(id, fName, sName, email, pass, number, role, notification, salt);
        assertNotNull(user);

        user = new User(id, fName, sName, email, pass, notification, salt);
        assertNotNull(user);

        user = new User(fName, sName, email, pass, number, notification, salt);
        assertNotNull(user);
    }

    @Test
    public void ticketConstructorsTest() {
        final int id = 1;
        final Session session = mock(Session.class);
        final User user = mock(User.class);
        final Seat seat = mock(Seat.class);
        final BigDecimal price = BigDecimal.TEN;


        Ticket ticket = new Ticket(id, session, user, seat, price);
        assertNotNull(ticket);
        String text = "Ticket{" +
                "session=" + session +
                ", user=" + user +
                ", seat=" + seat +
                ", ticketPrice=" + price +
                '}';
        assertEquals(text, ticket.toString());

        ticket = new Ticket(session, user, seat, price);
        assertNotNull(ticket);
        assertEquals(text, ticket.toString());
    }

    @Test
    public void sessionConstructorsTest() {
        final int id = 1;
        final Film film = mock(Film.class);
        final LocalDate date = LocalDate.EPOCH;
        final LocalTime time = LocalTime.NOON;
        final BigDecimal ticketPrice = BigDecimal.TEN;
        final int seatsAmount = 2;


        Session session = new Session(id, ticketPrice, date, time, film, seatsAmount);
        assertNotNull(session);
        String text = "Session{" +
                "ticketPrice=" + ticketPrice +
                ", date=" + date +
                ", time=" + time +
                ", film=" + film +
                '}';
        assertEquals(text, session.toString());

        session = new Session(id, ticketPrice, date, time, seatsAmount);
        assertNotNull(session);

        session = new Session(ticketPrice, date, time);
        assertNotNull(session);
    }

    @Test
    public void seatConstructorsTest() {
        final int id = 1;
        final int rowNumber = 2;
        final int placeNumber = 4;


        Seat seat = new Seat(id, rowNumber, placeNumber);
        assertNotNull(seat);
        String text = "Seat{" +
                "rowNumber=" + rowNumber +
                ", placeNumber=" + placeNumber +
                '}';
        assertEquals(text, seat.toString());

        seat = new Seat(rowNumber, placeNumber);
        assertNotNull(seat);
    }

    @Test
    public void genreConstructorsTest() {
        final int id = 1;
        final String name = "genre";
        Genre seat = new Genre(id, name);
        assertNotNull(seat);
    }

    @Test
    public void filmConstructorsTest() {
        final int id = 1;
        final String name = "name";
        final String descr = "descr";
        final String poster = "poster";
        final Duration duration = Duration.ZERO;
        final List<Genre> genreList = mock(List.class);

        String text = "Film{" +
                "duration=" + duration +
                ", genreList=" + genreList +
                ", name='" + name + '\'' +
                ", description='" + descr + '\'' +
                ", posterUrl='" + poster + '\'' +
                '}';
        Film film = new Film(id, name, descr, poster, duration, genreList);
        assertNotNull(film);
        assertEquals(text, film.toString());

        film = new Film(id, name, descr, poster, duration);
        assertNotNull(film);

        film = new Film(name, descr, poster, duration);
        assertNotNull(film);
    }

}
