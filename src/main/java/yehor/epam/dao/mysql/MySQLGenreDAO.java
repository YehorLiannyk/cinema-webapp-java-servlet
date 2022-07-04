package yehor.epam.dao.mysql;

import org.slf4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.GenreDAO;
import yehor.epam.exceptions.DaoException;
import yehor.epam.entities.Genre;
import yehor.epam.utilities.LoggerManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLGenreDAO extends BaseDAO implements GenreDAO {
    private static final Logger logger = LoggerManager.getLogger(MySQLGenreDAO.class);
    private static final String SELECT_ALL_BY_FILM_ID = "SELECT G.genre_id, G.genre_name FROM genres AS G JOIN films_genres as F_G ON G.genre_id = F_G.genre_id WHERE F_G.film_id = ?;";
    private static final String SELECT_ALL = "SELECT * FROM genres";
    private static final String SELECT_BY_ID = "SELECT * FROM genres WHERE genre_id=?";
    private static final String INSERT_GENRES_OF_FILM = "INSERT INTO films_genres VALUES(?,?)";

    @Override
    public boolean insert(Genre element) {
        return false;
    }

    @Override
    public Genre findById(int id) throws DaoException {
        Genre genre = null;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                genre = getGenreFromResultSet(resultSet);
            }
            if (genre == null) throw new DaoException("Couldn't find Genre with id: " + id);
        } catch (SQLException e) {
            logger.error("Couldn't get genre with id: " + id, e);
            throw new DaoException("Couldn't get genre with id: " + id);
        }
        return genre;
    }

    @Override
    public List<Genre> findAll() throws DaoException {
        List<Genre> genreList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                final Genre genre = getGenreFromResultSet(resultSet);
                genreList.add(genre);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get list of genres from ResultSet", e);
            throw new DaoException("Couldn't get list of genres from ResultSet");
        }
        return genreList;
    }

    private Genre getGenreFromResultSet(ResultSet resultSet) throws SQLException {
        final Genre genre;
        try {
            genre = new Genre(resultSet.getInt("genre_id"), resultSet.getString("genre_name"));
        } catch (SQLException e) {
            logger.error("Couldn't get Genre from ResultSet", e);
            throw new SQLException("Couldn't get Genre from ResultSet", e);
        }
        return genre;
    }

    @Override
    public Genre update(Genre element) {
        return null;
    }

    @Override
    public boolean delete(Genre element) {
        return false;
    }

    @Override
    public boolean insertFilmGenres(final int filmId, List<Genre> genreList) throws SQLException, DaoException {
        boolean inserted = false;
        if (genreList.isEmpty()) throw new DaoException("Received genreList is empty");
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_GENRES_OF_FILM)) {
            for (Genre genre : genreList) {
                if (genre == null) throw new DaoException("Received Genre from genreList is null");
                statement.setInt(1, filmId);
                statement.setInt(2, genre.getId());
                statement.addBatch();
            }
            final int[] rows = statement.executeBatch();
            if (rows.length < 1) throw new DaoException("Statement inserted nothing");
            inserted = true;
        } catch (SQLException e) {
            logger.error("Couldn't insert Film Genres to DataBase", e);
            throw new SQLException("Couldn't insert Film Genres to DataBase", e);
        }
        return inserted;
    }

    @Override
    public List<Genre> getGenreListOfFilm(int filmId) throws DaoException {
        List<Genre> genreList = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL_BY_FILM_ID)) {
            statement.setInt(1, filmId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Genre genre = getGenreFromResultSet(resultSet);
                genreList.add(genre);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get list of genres from Database", e);
            throw new DaoException("Couldn't get list of genres from Database");
        }
        return genreList;
    }
}
