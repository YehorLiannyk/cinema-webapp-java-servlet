package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Film;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.FilmService;
import yehor.epam.services.impl.FilmServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.constants.JspPagePathConstants.ADD_SESSION_PAGE_PATH;

/**
 * Admin add Session page command
 */
public class AddSessionPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(AddSessionPageCommand.class);
    private static final String CLASS_NAME = AddSessionPageCommand.class.getName();
    private final FilmService filmService;

    public AddSessionPageCommand() {
        filmService = new FilmServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final List<Film> filmList = filmService.getAll();
            request.setAttribute("filmList", filmList);
            logger.debug("Forward to add session page");
            request.getRequestDispatcher(ADD_SESSION_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
