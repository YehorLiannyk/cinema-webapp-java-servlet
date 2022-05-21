package yehor.epam.actions.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import yehor.epam.actions.ActionCommand;
import yehor.epam.dao.FilmDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Film;

import java.io.IOException;
import java.util.List;

import static yehor.epam.utilities.JspPagePathConstants.MAIN_PAGE_PATH;

public class MainPageCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory factory = new MySQLFactory()) {
            final FilmDAO filmDAO = factory.getFilmDAO();
            final List<Film> filmList = filmDAO.findAll();
            request.setAttribute("filmList", filmList);
            request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
