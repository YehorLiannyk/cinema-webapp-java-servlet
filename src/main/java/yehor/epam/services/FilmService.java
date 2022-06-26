package yehor.epam.services;

import yehor.epam.entities.Film;
import yehor.epam.exceptions.ServiceException;

import java.util.List;

public interface FilmService {
    List<Film> getAll() throws ServiceException;

    void addFilm(Film film) throws ServiceException;


    void deleteFilm(int id) throws ServiceException;

    Film getFilmById(int id) throws ServiceException;
}
