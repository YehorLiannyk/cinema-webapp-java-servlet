package yehor.epam.actions.commands.films;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Film;
import yehor.epam.entities.Genre;
import yehor.epam.services.FilmService;
import yehor.epam.services.GenreService;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.impl.FilmServiceImpl;
import yehor.epam.services.impl.GenreServiceImpl;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.RedirectManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.CommandConstants.COMMAND_VIEW_FILMS_SETTING_PAGE;
import static yehor.epam.utilities.constants.OtherConstants.*;

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
            final Map<String, String> filmParamMap = getFilmParamMap(request);
            final String[] genreIds = request.getParameterMap().get(GENRE_IDS_PARAM);
            final List<String> errorList = filmService.getFilmValidErrorList(filmParamMap, genreIds);
            if (errorList.isEmpty()) {
                Film film = getFilm(filmParamMap);
                final List<Genre> genreList = genreService.getGenreListByIdArray(genreIds);
                film.setGenreList(genreList);
                filmService.save(film);
                response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_FILMS_SETTING_PAGE));
            } else {
                forwardWithErrors(request, response, errorList);
            }
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Forward to add film page with error list
     *
     * @param request   {@link HttpServletRequest}
     * @param response  {@link HttpServletResponse}
     * @param errorList received error List from validation service
     */
    private void forwardWithErrors(HttpServletRequest request, HttpServletResponse response, List<String> errorList) {
        VALID_ERROR_FILM_PARAM_LIST.stream()
                .filter(error -> request.getAttribute(error) != null)
                .forEach(error -> request.setAttribute(error, false));
        errorList.forEach(error -> request.setAttribute(error, true));
        new AddFilmPageCommand().execute(request, response);
    }

    /**
     * Get film from params Map
     *
     * @param filmParamMap film parameters map
     * @return formed Film object
     */
    private Film getFilm(Map<String, String> filmParamMap) {
        final long filmDuration = Long.parseLong(filmParamMap.get(FILM_DURATION_PARAM));
        return new Film(
                filmParamMap.get(FILM_NAME_PARAM),
                filmParamMap.get(FILM_DESCR_PARAM),
                filmParamMap.get(POSTER_URL_PARAM),
                Duration.ofMinutes(filmDuration)
        );
    }

    /**
     * Get Map of needed parameters for film creation
     *
     * @param request {@link HttpServletRequest}
     * @return map of needed parameters for film creation
     */
    private Map<String, String> getFilmParamMap(HttpServletRequest request) {
        Map<String, String> filmParamMap = new HashMap<>();
        filmParamMap.put(FILM_NAME_PARAM, request.getParameter(FILM_NAME_PARAM));
        filmParamMap.put(FILM_DESCR_PARAM, request.getParameter(FILM_DESCR_PARAM));
        filmParamMap.put(POSTER_URL_PARAM, request.getParameter(POSTER_URL_PARAM));
        filmParamMap.put(FILM_DURATION_PARAM, request.getParameter(FILM_DURATION_PARAM));
        return filmParamMap;
    }
}
