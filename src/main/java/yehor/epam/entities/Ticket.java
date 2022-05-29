package yehor.epam.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Ticket extends BaseEntity {
    private Session session;
    private User user;
    private Seat seat;
    private BigDecimal ticketPrice;

    public Ticket(int id, Session session, User user, Seat seat, BigDecimal ticketPrice) {
        super(id);
        this.session = session;
        this.user = user;
        this.seat = seat;
        this.ticketPrice = ticketPrice;
    }

    public Ticket(Session session, User user, Seat seat, BigDecimal ticketPrice) {
        this.session = session;
        this.user = user;
        this.seat = seat;
        this.ticketPrice = ticketPrice;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "session=" + session +
                ", user=" + user +
                ", seat=" + seat +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
