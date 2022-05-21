package yehor.epam.dao.mysql;

import org.apache.log4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.exception.DAOException;
import yehor.epam.dao.GenreDAO;
import yehor.epam.entities.Genre;
import yehor.epam.utilities.LoggerManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLGenreDAO extends BaseDAO implements GenreDAO {
    private static final Logger logger = LoggerManager.getLogger(MySQLGenreDAO.class);
    private final String SELECT_ALL_BY_FILM_ID = "SELECT G.genre_id, G.genre_name FROM genres AS G JOIN films_genres as F_G ON G.genre_id = F_G.genre_id WHERE F_G.film_id = ?;";

    @Override
    public boolean insert(Genre element) {
        return false;
    }

    @Override
    public Genre findById(int id) {
        return null;
    }

    @Override
    public List<Genre> findAll() {
        return null;
    }

    @Override
    public Genre update(Genre element) {
        return null;
    }

    @Override
    public boolean delete(Genre element) {
        return false;
    }

    public List<Genre> getGenreListOfFilm(int filmId) {
        List<Genre> genreList = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL_BY_FILM_ID)) {
            statement.setInt(1, filmId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Genre genre = new Genre(resultSet.getInt("genre_id"), resultSet.getString("genre_name"));
                genreList.add(genre);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get list of genres from DB", e);
            throw new DAOException("Couldn't get list of genres from DB");
        }
        return genreList;
    }
}
