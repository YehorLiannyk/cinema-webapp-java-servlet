package yehor.epam.dao.mysql;

import org.apache.log4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.FilmDAO;
import yehor.epam.exceptions.DAOException;
import yehor.epam.entities.Film;
import yehor.epam.entities.Genre;
import yehor.epam.utilities.LoggerManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MySQLFilmDAO extends BaseDAO implements FilmDAO {
    private static final Logger logger = LoggerManager.getLogger(MySQLFilmDAO.class);
    private static final String SELECT_ALL = "SELECT * FROM films ORDER BY film_id DESC";
    private static final String SELECT_BY_ID = "SELECT * FROM films WHERE film_id=?";
    private static final String INSERT_FILM = "INSERT INTO films VALUES(film_id, ?,?,?,?)";
    private static final String DELETE_BY_FILM_ID = "DELETE  FROM films WHERE film_id=?";


    @Override
    public boolean insert(Film film) {
        boolean inserted = false;
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_FILM, Statement.RETURN_GENERATED_KEYS)) {
            setFilmToStatement(film, statement);
            filmInsertTransaction(film, statement);
            inserted = true;
        } catch (SQLException e) {
            logger.error("Couldn't insert Film to DataBase");
            throw new DAOException("Couldn't insert Film to DataBase");
        }
        return inserted;
    }

    /**
     * Transaction method for preventing Film writing to Database without writing its Genres
     *
     * @param film      Film item
     * @param statement PreparedStatement
     */
    private void filmInsertTransaction(Film film, PreparedStatement statement) throws SQLException {
        getConnection().setAutoCommit(false);
        statement.executeUpdate();
        int filmId = getLastGeneratedKey(statement);
        final boolean insertFilmGenres = getGenresDAO().insertFilmGenres(filmId, film.getGenreList());
        if (insertFilmGenres) {
            getConnection().commit();
        } else {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
            logger.debug("rollback and setAutoCommit(true)");

            throw new DAOException("Film and genres were not inserted");
        }
        getConnection().setAutoCommit(true);
        logger.debug("getConnection().setAutoCommit(true)");

    }

    private int getLastGeneratedKey(PreparedStatement statement) throws SQLException {
        int key = -1;
        final ResultSet generatedKeys = statement.getGeneratedKeys();
        while (generatedKeys.next())
            key = generatedKeys.getInt(1);
        return key;
    }

    private void setFilmToStatement(Film film, PreparedStatement statement) throws SQLException {
        try {
            statement.setString(1, film.getName());
            statement.setString(2, film.getDescription());
            statement.setString(3, film.getPosterUrl());
            statement.setInt(4, Math.toIntExact(film.getDurationInMinutes()));
        } catch (SQLException e) {
            logger.error("Couldn't set Film to Statement", e);
            throw new SQLException(e);
        }
    }

    @Override
    public Film findById(int id) {
        Film film = null;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                film = getFilmFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Couldn't find film by id in Database", e);
            throw new DAOException("Couldn't find film by id in Database");
        }
        return film;
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
            logger.error("Couldn't get list of all films from Database", e);
            throw new DAOException("Couldn't get list of all films from Database");
        }
        return films;
    }

    @Override
    public Film update(Film element) {
        return null;
    }

    @Override
    public boolean delete(Film element) {
        return delete(element.getId());
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
            final List<Genre> genreList = getGenresDAO().getGenreListOfFilm(film.getId());
            film.setGenreList(genreList);
        } catch (SQLException e) {
            logger.error("Couldn't get film from ResultSet", e);
            throw new DAOException("Couldn't get film from ResultSet");
        }
        return film;
    }

    private MySQLGenreDAO getGenresDAO() {
        final MySQLGenreDAO mySQLGenreDAO = new MySQLGenreDAO();
        mySQLGenreDAO.setConnection(getConnection());
        return mySQLGenreDAO;
    }

    @Override
    public boolean delete(int filmId) {
        boolean isDeleted = false;
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_BY_FILM_ID)) {
            statement.setInt(1, filmId);
            final int row = statement.executeUpdate();
            if (row > 1) throw new DAOException("Statement removed more than one row");
            isDeleted = true;
        } catch (SQLException e) {
            logger.error("Couldn't delete film", e);
            throw new DAOException("Couldn't delete film");
        }
        return isDeleted;
    }
}
