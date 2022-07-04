package yehor.epam.actions.commands;

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

import static yehor.epam.utilities.constants.JspPagePathConstants.MAIN_PAGE_PATH;
import static yehor.epam.utilities.constants.OtherConstants.*;

/**
 * Command preparing and forward to Main page
 */
public class MainPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(MainPageCommand.class);
    private static final String CLASS_NAME = MainPageCommand.class.getName();
    private final FilmService filmService;
    private final PaginationService paginationService;

    public MainPageCommand() {
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

            final List<Film> all = filmService.getAll(page, size);
            final int totalPages = filmService.countTotalPages(size);

            request.setAttribute("totalPages", totalPages);
            request.getSession().setAttribute("filmList", all);
            request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

}
