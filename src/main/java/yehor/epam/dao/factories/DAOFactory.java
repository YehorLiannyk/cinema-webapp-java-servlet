package yehor.epam.dao.factories;

import yehor.epam.dao.DAO;
import yehor.epam.dao.FilmDAO;
import yehor.epam.dao.GenreDAO;
import yehor.epam.entities.BaseEntity;
import yehor.epam.entities.Film;

public interface DAOFactory extends AutoCloseable{
    FilmDAO getFilmDAO();
    GenreDAO getGenreDAO();
}
