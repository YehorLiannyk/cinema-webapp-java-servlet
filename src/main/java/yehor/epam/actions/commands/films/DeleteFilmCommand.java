package yehor.epam.actions.commands.films;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.FilmDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.RedirectManager;

import static yehor.epam.utilities.CommandConstants.COMMAND_VIEW_FILMS_SETTING_PAGE;

/**
 * Admin delete film command
 */
public class DeleteFilmCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(DeleteFilmCommand.class);
    private static final String CLASS_NAME = DeleteFilmCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + CLASS_NAME + " execute command");
            final int filmId = Integer.parseInt(request.getParameter("filmId"));
            final FilmDAO filmDAO = factory.getFilmDAO();
            filmDAO.delete(filmId);
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_FILMS_SETTING_PAGE));
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

}
