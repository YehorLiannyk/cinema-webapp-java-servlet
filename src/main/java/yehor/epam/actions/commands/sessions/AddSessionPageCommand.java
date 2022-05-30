package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.FilmDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Film;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.JspPagePathConstants.ADD_SESSION_PAGE_PATH;

/**
 * Admin add Session page command
 */
public class AddSessionPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(AddSessionPageCommand.class);
    private static final String CLASS_NAME = AddSessionPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + CLASS_NAME + " execute command");
            final FilmDAO filmDAO = factory.getFilmDAO();
            final List<Film> filmList = filmDAO.findAll();
            request.setAttribute("filmList", filmList);
            logger.debug("Forward to add session page");
            request.getRequestDispatcher(ADD_SESSION_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}
