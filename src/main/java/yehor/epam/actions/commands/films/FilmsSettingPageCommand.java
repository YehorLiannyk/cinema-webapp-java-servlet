package yehor.epam.actions.commands.films;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.services.ErrorService;
import yehor.epam.services.FilmService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.JspPagePathConstants.FILMS_SETTING_PAGE_PATH;

/**
 * Admin film setting page
 */
public class FilmsSettingPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(FilmsSettingPageCommand.class);
    private static final String CLASS_NAME = FilmsSettingPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.info("Created DAOFactory in " + CLASS_NAME + " execute command");
            FilmService filmService = new FilmService();
            filmService.setFilmListToSession(request, factory);
            request.getRequestDispatcher(FILMS_SETTING_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);

        }
    }
}
