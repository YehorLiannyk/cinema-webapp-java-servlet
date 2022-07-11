package yehor.epam.actions.commands.films;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Genre;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.GenreService;
import yehor.epam.services.impl.GenreServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.constants.JspPagePathConstants.ADD_FILM_PAGE_PATH;

/**
 * Admin film page command
 */
public class AddFilmPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(AddFilmPageCommand.class);
    private static final String CLASS_NAME = AddFilmPageCommand.class.getName();
    private final GenreService genreService;

    public AddFilmPageCommand() {
        genreService = new GenreServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final List<Genre> genreList = genreService.getAll();
            request.setAttribute("genreList", genreList);
            logger.debug("Forwarded to admin add film page");
            request.getRequestDispatcher(ADD_FILM_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
