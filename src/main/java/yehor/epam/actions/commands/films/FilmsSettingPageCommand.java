package yehor.epam.actions.commands.films;

import jakarta.servlet.ServletException;
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

import java.io.IOException;
import java.util.List;

import static yehor.epam.utilities.JspPagePathConstants.*;

public class FilmsSettingPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(FilmsSettingPageCommand.class);
    private String className = FilmsSettingPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + className + " execute command");
            final FilmDAO filmDAO = factory.getFilmDAO();
            final List<Film> filmList = filmDAO.findAll();
            request.getSession().setAttribute("filmList", filmList);
            request.getRequestDispatcher(FILMS_SETTING_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, className, e);

        }
    }
}
