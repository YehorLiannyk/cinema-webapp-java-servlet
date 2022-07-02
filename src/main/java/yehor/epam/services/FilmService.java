package yehor.epam.services;

import yehor.epam.entities.Film;
import yehor.epam.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface FilmService {
    List<Film> getAll() throws ServiceException;

    List<Film> getAll(int page, int size) throws ServiceException;


    int countTotalPages(int size) throws ServiceException;

    void saveFilm(Film film) throws ServiceException;


    void deleteFilm(int id) throws ServiceException;

    Film getFilmById(int id) throws ServiceException;

    List<String> getFilmValidErrorList(Map<String, String> filmParamMap, String[] genreIds);
}
