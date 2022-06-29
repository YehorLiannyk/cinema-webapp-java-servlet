package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Film;
import yehor.epam.entities.Session;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.impl.ErrorServiceImpl;
import yehor.epam.services.FilmService;
import yehor.epam.services.SessionService;
import yehor.epam.services.impl.FilmServiceImpl;
import yehor.epam.services.impl.SessionServiceImpl;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.RedirectManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static yehor.epam.utilities.constants.CommandConstants.COMMAND_VIEW_SESSIONS_SETTING_PAGE;

/**
 * Admin add Session Command
 */
public class AddSessionCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(AddSessionCommand.class);
    private static final String CLASS_NAME = AddSessionCommand.class.getName();
    private final SessionService sessionService;
    private final FilmService filmService;

    public AddSessionCommand() {
        sessionService = new SessionServiceImpl();
        filmService = new FilmServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final Session session = getSessionFromRequest(request);
            final Film film = getFilmFromRequest(request);
            session.setFilm(film);
            sessionService.saveSession(session);
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_SESSIONS_SETTING_PAGE));
        } catch (Exception e) {
            ErrorServiceImpl.handleException(request, response, CLASS_NAME, e);
        }
    }

    private Session getSessionFromRequest(HttpServletRequest request) {
        return new Session(
                new BigDecimal(request.getParameter("ticketPrice")),
                LocalDate.parse(request.getParameter("date")),
                LocalTime.parse(request.getParameter("time"))
        );
    }

    private Film getFilmFromRequest(HttpServletRequest request) throws ServiceException {
        final int filmId = Integer.parseInt(request.getParameter("filmId"));
        return filmService.getFilmById(filmId);
    }

}
