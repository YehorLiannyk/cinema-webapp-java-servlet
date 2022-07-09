package yehor.epam.actions.commands.films;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Film;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.FilmService;
import yehor.epam.services.impl.FilmServiceImpl;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.constants.JspPagePathConstants.FILM_INFO_PAGE_PATH;

/**
 * Film info page command
 */
public class FilmInfoPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(FilmInfoPageCommand.class);
    private static final String CLASS_NAME = FilmInfoPageCommand.class.getName();
    private final FilmService filmService;

    public FilmInfoPageCommand() {
        filmService = new FilmServiceImpl();
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final int filmId = Integer.parseInt(request.getParameter("filmId"));
            final Film film = filmService.getById(filmId);
            request.setAttribute("film", film);
            request.getRequestDispatcher(FILM_INFO_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

}
