package yehor.epam.dao;

import yehor.epam.entities.Film;

public interface FilmDAO extends DAO<Film> {
    /**
     * Delete film from DB
     *
     * @param filmId film id
     * @return true if film was deleted and false if not
     */
    boolean delete(final int filmId);
}
