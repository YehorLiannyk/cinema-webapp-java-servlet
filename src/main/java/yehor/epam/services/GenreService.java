package yehor.epam.services;

import jakarta.servlet.http.HttpServletRequest;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.entities.Genre;
import yehor.epam.exceptions.DAOException;
import yehor.epam.exceptions.ServiceException;

import java.util.List;

public interface GenreService {
    List<Genre> getGenreListByIdArray(String[] genresId) throws ServiceException;

    List<Genre> getGenreList() throws ServiceException;

}
