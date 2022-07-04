package yehor.epam.actions.commands.films;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Film;
import yehor.epam.services.FilmService;
import yehor.epam.services.PaginationService;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.impl.FilmServiceImpl;
import yehor.epam.services.impl.PaginationServiceImpl;
import yehor.epam.utilities.LoggerManager;

import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.JspPagePathConstants.FILMS_SETTING_PAGE_PATH;
import static yehor.epam.utilities.constants.OtherConstants.PAGE_NO_PARAM;
import static yehor.epam.utilities.constants.OtherConstants.PAGE_SIZE_PARAM;

/**
 * Admin film setting page
 */
public class FilmsSettingPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(FilmsSettingPageCommand.class);
    private static final String CLASS_NAME = FilmsSettingPageCommand.class.getName();
    private final FilmService filmService;
    private final PaginationService paginationService;

    public FilmsSettingPageCommand() {
        filmService = new FilmServiceImpl();
        paginationService = new PaginationServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final Map<String, Integer> paginationMap = paginationService.getPaginationParamsFromRequest(request);
            int page = paginationMap.get(PAGE_NO_PARAM);
            int size = paginationMap.get(PAGE_SIZE_PARAM);
            final int totalPages = filmService.countTotalPages(size);

            final List<Film> all = filmService.getAll(page, size);
            request.setAttribute("totalPages", totalPages);
            request.getSession().setAttribute("filmList", all);
            request.getRequestDispatcher(FILMS_SETTING_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
