package yehor.epam.actions.commands.films;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.FilmDAO;
import yehor.epam.dao.GenreDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Film;
import yehor.epam.entities.Genre;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.RedirectManager;
import yehor.epam.utilities.LoggerManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.CommandConstants.COMMAND_VIEW_FILMS_SETTING_PAGE;

public class AddFilmCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(AddFilmCommand.class);
    private String className = AddFilmCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + className + " execute command");
            final Film film = getFilmFromRequest(request);
            final List<Genre> genreList = getGenreListFromRequest(request, factory);
            film.setGenreList(genreList);
            final FilmDAO filmDAO = factory.getFilmDAO();
            filmDAO.insert(film);
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_FILMS_SETTING_PAGE));
        } catch (Exception e) {
            ErrorService.handleException(request, response, className, e);
        }
    }

    private Film getFilmFromRequest(HttpServletRequest request) {
        logger.debug("request.getParameter(\"filmDuration\") = " + request.getParameter("filmDuration"));
        final long filmDuration = Long.parseLong(request.getParameter("filmDuration"));
        return new Film(
                request.getParameter("filmName"),
                request.getParameter("filmDescription"),
                request.getParameter("postUrl"),
                Duration.ofMinutes(filmDuration)
        );
    }

    private List<Genre> getGenreListFromRequest(HttpServletRequest request, DAOFactory factory) {
        List<Genre> genreList = new ArrayList<>();
        final Map<String, String[]> parameterMap = request.getParameterMap();
        final String[] genresId = parameterMap.get("genresId");
        final GenreDAO genreDAO = factory.getGenreDAO();
        if (genresId == null || genresId.length == 0) {
            logger.error("Genre Array is null or empty");
            throw new NullPointerException("Genre Array is null or empty");
        }
        for (String genreId : genresId) {
            final Genre genre = genreDAO.findById(Integer.parseInt(genreId));
            genreList.add(genre);
        }
        return genreList;
    }

}
