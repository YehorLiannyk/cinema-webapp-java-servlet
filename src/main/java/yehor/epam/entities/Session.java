package yehor.epam.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Session extends BaseEntity {
    private BigDecimal ticketPrice;
    private LocalDate date;
    private LocalTime time;
    private Film film;

    public Session(int id, BigDecimal ticketPrice, LocalDate date, LocalTime time, Film film) {
        super(id);
        this.ticketPrice = ticketPrice;
        this.date = date;
        this.time = time;
        this.film = film;
    }

    public Session(int id, BigDecimal ticketPrice, LocalDate date, LocalTime time) {
        this(id, ticketPrice, date, time, null);
    }

    public Session(BigDecimal ticketPrice, LocalDate date, LocalTime time) {
        this.ticketPrice = ticketPrice;
        this.date = date;
        this.time = time;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "Session{" +
                "ticketPrice=" + ticketPrice +
                ", date=" + date +
                ", time=" + time +
                ", film=" + film +
                '}';
    }
}
