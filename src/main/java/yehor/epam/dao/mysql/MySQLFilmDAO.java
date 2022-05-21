package yehor.epam.dao.mysql;

import org.apache.log4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.exception.DAOException;
import yehor.epam.dao.FilmDAO;
import yehor.epam.entities.Film;
import yehor.epam.entities.Genre;
import yehor.epam.utilities.LoggerManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MySQLFilmDAO extends BaseDAO implements FilmDAO {
    private static final Logger logger = LoggerManager.getLogger(MySQLFilmDAO.class);
    private final String SELECT_ALL = "SELECT * FROM films";

    @Override
    public boolean insert(Film element) {
        return false;
    }

    @Override
    public Film findById(int id) {
        return null;
    }

    @Override
    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                final Film film = getFilmFromResultSet(resultSet);
                films.add(film);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get list of all films from DB", e);
            throw new DAOException("Couldn't get list of all films from DB");
        }
        return films;
    }

    @Override
    public Film update(Film element) {
        return null;
    }

    @Override
    public boolean delete(Film element) {
        return false;
    }

    private Film getFilmFromResultSet(ResultSet rs) {
        Film film = null;
        try {
            film = new Film(
                    rs.getInt("film_id"),
                    rs.getString("film_name"),
                    rs.getString("description"),
                    rs.getString("poster_url"),
                    Duration.ofMinutes(rs.getInt("duration"))
            );
            final List<Genre> genreList = getGenresByFilm(film);
            film.setGenreList(genreList);
        } catch (SQLException e) {
            logger.error("Couldn't get film from ResultSet", e);
            throw new DAOException("Couldn't get film from ResultSet");
        }
        return film;
    }

    private List<Genre> getGenresByFilm(Film film) {
        final MySQLGenreDAO mySQLGenreDAO = new MySQLGenreDAO();
        mySQLGenreDAO.setConnection(getConnection());
        return mySQLGenreDAO.getGenreListOfFilm(film.getId());
    }
}
