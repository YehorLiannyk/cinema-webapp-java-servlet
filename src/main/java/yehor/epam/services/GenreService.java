package yehor.epam.services;

import yehor.epam.entities.Genre;
import yehor.epam.exceptions.ServiceException;

import java.util.List;

public interface GenreService {
    List<Genre> getGenreListByIdArray(String[] genreIds) throws ServiceException;

    List<Genre> getGenreList() throws ServiceException;

}
