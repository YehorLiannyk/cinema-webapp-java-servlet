package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.FilmDAO;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Film;
import yehor.epam.entities.Session;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.RedirectManager;
import yehor.epam.utilities.LoggerManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static yehor.epam.utilities.CommandConstants.COMMAND_VIEW_SESSIONS_SETTING_PAGE;

public class AddSessionCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(AddSessionCommand.class);
    private String className = AddSessionCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + className + " execute command");
            final Session session = getSessionFromRequest(request);
            final Film film = getFilmFromRequest(request, factory);
            session.setFilm(film);
            final SessionDAO sessionDAO = factory.getSessionDao();
            sessionDAO.insert(session);
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_SESSIONS_SETTING_PAGE));
        } catch (Exception e) {
            ErrorService.handleException(request, response, className, e);
        }
    }

    private Session getSessionFromRequest(HttpServletRequest request) {
        return new Session(
                new BigDecimal(request.getParameter("ticketPrice")),
                LocalDate.parse(request.getParameter("date")),
                LocalTime.parse(request.getParameter("time"))
        );
    }

    private Film getFilmFromRequest(HttpServletRequest request, DAOFactory factory) {
        final int filmId = Integer.parseInt(request.getParameter("filmId"));
        final FilmDAO filmDAO = factory.getFilmDAO();
        return filmDAO.findById(filmId);
    }

}
