package yehor.epam.actions.commands.films;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Film;
import yehor.epam.entities.Genre;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.ErrorService;
import yehor.epam.services.FilmService;
import yehor.epam.services.GenreService;
import yehor.epam.services.impl.FilmServiceImpl;
import yehor.epam.services.impl.GenreServiceImpl;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.RedirectManager;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.CommandConstants.COMMAND_VIEW_FILMS_SETTING_PAGE;

/**
 * Admin adding film command
 */
public class AddFilmCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(AddFilmCommand.class);
    private static final String CLASS_NAME = AddFilmCommand.class.getName();
    private final FilmService filmService;
    private final GenreService genreService;

    public AddFilmCommand() {
        filmService = new FilmServiceImpl();
        genreService = new GenreServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            Film film = getFilmFromRequest(request);
            final String[] genresId = request.getParameterMap().get("genresId");
            final List<Genre> genreList = genreService.getGenreListByIdArray(genresId);
            film.setGenreList(genreList);
            filmService.addFilm(film);
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_FILMS_SETTING_PAGE));
        } catch (ServiceException | IOException e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    private Film getFilmFromRequest(HttpServletRequest request) {
        final long filmDuration = Long.parseLong(request.getParameter("filmDuration"));
        return new Film(
                request.getParameter("filmName"),
                request.getParameter("filmDescription"),
                request.getParameter("postUrl"),
                Duration.ofMinutes(filmDuration)
        );
    }





}
