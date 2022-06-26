package yehor.epam.actions.commands.films;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Genre;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.ErrorService;
import yehor.epam.services.GenreService;
import yehor.epam.services.impl.GenreServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;
import java.util.List;

import static yehor.epam.utilities.JspPagePathConstants.ADD_FILM_PAGE_PATH;

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
            final List<Genre> genreList = genreService.getGenreList();
            request.setAttribute("genreList", genreList);
            logger.debug("Forwarded to admin add film page");
            request.getRequestDispatcher(ADD_FILM_PAGE_PATH).forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
