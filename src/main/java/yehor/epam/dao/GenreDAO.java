package yehor.epam.dao;

import yehor.epam.entities.Genre;

import java.util.List;

public interface GenreDAO extends DAO<Genre> {
    /**
     * Get all genres of the film
     *
     * @param filmId id of the film
     * @return Genre list by Film id
     */
    List<Genre> getGenreListOfFilm(int filmId);
}
