package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Film;
import yehor.epam.services.FilmService;
import yehor.epam.services.impl.ErrorServiceImpl;
import yehor.epam.services.impl.FilmServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.constants.JspPagePathConstants.FILMS_SETTING_PAGE_PATH;
import static yehor.epam.utilities.constants.JspPagePathConstants.MAIN_PAGE_PATH;

/**
 * Command preparing and forward to Main page
 */
public class MainPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(MainPageCommand.class);
    private static final String CLASS_NAME = MainPageCommand.class.getName();
    private final FilmService filmService;

    public MainPageCommand() {
        filmService = new FilmServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final List<Film> all = filmService.getAll();
            request.getSession().setAttribute("filmList", all);
            request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorServiceImpl.handleException(request, response, CLASS_NAME, e);
        }
    }
}
