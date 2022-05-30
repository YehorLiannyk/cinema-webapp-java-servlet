package yehor.epam.actions.commands.films;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.FilmDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Film;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.JspPagePathConstants.FILM_INFO_PAGE_PATH;

/**
 * Film info page command
 */
public class FilmInfoPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(FilmInfoPageCommand.class);
    private static final String CLASS_NAME = FilmInfoPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + CLASS_NAME + " execute command");

            final int filmId = Integer.parseInt(request.getParameter("filmId"));

            final FilmDAO filmDAO = factory.getFilmDAO();
            final Film film = filmDAO.findById(filmId);
            request.setAttribute("film", film);

            request.getRequestDispatcher(FILM_INFO_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

}
