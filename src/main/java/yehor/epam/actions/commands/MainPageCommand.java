package yehor.epam.actions.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.ActionCommand;
import yehor.epam.dao.FilmDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Film;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;
import java.util.List;

import static yehor.epam.utilities.JspPagePathConstants.MAIN_PAGE_PATH;

public class MainPageCommand implements ActionCommand {
    private static final Logger logger = LoggerManager.getLogger(MainPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + MainPageCommand.class.getSimpleName() + " execute command");
            final FilmDAO filmDAO = factory.getFilmDAO();
            final List<Film> filmList = filmDAO.findAll();
            request.getSession().setAttribute("filmList", filmList);
            request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            logger.error("Couldn't execute " + MainPageCommand.class.getSimpleName() + " command", e);
        }
    }
}
