package yehor.epam.dao;

import yehor.epam.entities.Genre;
import yehor.epam.exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface GenreDAO extends DAO<Genre> {
    /**
     * Get all genres of the film
     *
     * @param filmId id of the film
     * @return Genre list by Film id
     */
    List<Genre> getGenreListOfFilm(int filmId) throws DaoException;

    /**
     * Insert Films Genres to Database
     * @param filmId id of Film
     * @param genreList List of Films Genres
     */
    boolean insertFilmGenres(final int filmId, List<Genre> genreList) throws SQLException, DaoException;
}
