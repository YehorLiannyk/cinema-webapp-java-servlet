package yehor.epam.dao;

import yehor.epam.entities.Film;
import yehor.epam.exceptions.DaoException;

public interface FilmDao extends DAO<Film>, PaginatableDao<Film> {
    /**
     * Delete film from DB
     *
     * @param filmId film id
     * @return true if film was deleted and false if not
     */
    boolean delete(final int filmId) throws DaoException;
}
