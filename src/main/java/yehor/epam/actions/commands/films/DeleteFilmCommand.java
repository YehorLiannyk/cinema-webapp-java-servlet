package yehor.epam.actions.commands.films;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.FilmService;
import yehor.epam.services.impl.FilmServiceImpl;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.RedirectManager;

import java.io.IOException;

import static yehor.epam.utilities.constants.CommandConstants.COMMAND_VIEW_FILMS_SETTING_PAGE;

/**
 * Admin delete film command
 */
public class DeleteFilmCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(DeleteFilmCommand.class);
    private static final String CLASS_NAME = DeleteFilmCommand.class.getName();
    private final FilmService filmService;

    public DeleteFilmCommand() {
        filmService = new FilmServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try{
            final int filmId = Integer.parseInt(request.getParameter("filmId"));
            filmService.delete(filmId);
            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_FILMS_SETTING_PAGE));
        } catch (ServiceException | IOException e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

}
