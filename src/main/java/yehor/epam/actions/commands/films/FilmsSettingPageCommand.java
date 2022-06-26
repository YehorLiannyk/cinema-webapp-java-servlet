package yehor.epam.actions.commands.films;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Film;
import yehor.epam.services.impl.ErrorServiceImpl;
import yehor.epam.services.FilmService;
import yehor.epam.services.impl.FilmServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.constants.JspPagePathConstants.FILMS_SETTING_PAGE_PATH;

/**
 * Admin film setting page
 */
public class FilmsSettingPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(FilmsSettingPageCommand.class);
    private static final String CLASS_NAME = FilmsSettingPageCommand.class.getName();
    private final FilmService filmService;

    public FilmsSettingPageCommand() {
        filmService = new FilmServiceImpl();
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final List<Film> all = filmService.getAll();
            request.getSession().setAttribute("filmList", all);
            request.getRequestDispatcher(FILMS_SETTING_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorServiceImpl.handleException(request, response, CLASS_NAME, e);
        }
    }
}
