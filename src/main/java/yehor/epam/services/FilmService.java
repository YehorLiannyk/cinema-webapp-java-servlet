package yehor.epam.services;

import jakarta.servlet.http.HttpServletRequest;
import yehor.epam.dao.FilmDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.entities.Film;

import java.util.List;

public class FilmService {
    public void setFilmListToSession(HttpServletRequest request, DAOFactory factory) {
        final FilmDAO filmDAO = factory.getFilmDAO();
        final List<Film> filmList = filmDAO.findAll();
        request.getSession().setAttribute("filmList", filmList);
    }
}
