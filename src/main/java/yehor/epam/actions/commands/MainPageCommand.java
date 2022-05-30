package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.services.ErrorService;
import yehor.epam.services.FilmService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.JspPagePathConstants.MAIN_PAGE_PATH;

/**
 * Command preparing and forward to Main page
 */
public class MainPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(MainPageCommand.class);
    private static final String CLASS_NAME = MainPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.info("Created DAOFactory in " + CLASS_NAME + " execute command");
            FilmService filmService = new FilmService();
            filmService.setFilmListToSession(request, factory);
            request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
