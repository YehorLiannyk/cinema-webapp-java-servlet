package yehor.epam.dao.mysql;

import org.apache.log4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.UserDAO;
import yehor.epam.dao.exception.AuthException;
import yehor.epam.dao.exception.DAOException;
import yehor.epam.entities.User;
import yehor.epam.utilities.LoggerManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class MySQLUserDAO extends BaseDAO implements UserDAO {
    private static final Logger logger = LoggerManager.getLogger(MySQLUserDAO.class);
    private String SELECT_USER = "SELECT * FROM users s JOIN roles r on s.role_id = r.role_id WHERE s.email=? AND s.password=?";
    private String INSERT = "INSERT INTO users(user_id, first_name, second_name, email, password, phone_number, notification) VALUES(user_id,?,?,?,?,?,?)";

    @Override
    public boolean insert(User user) {
        boolean inserted = false;
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setUserToStatement(user, statement);
            int rows = statement.executeUpdate();
            if (rows > 1) throw new DAOException("More than one rows were inserted to DB");
            inserted = true;
        } catch (SQLException e) {
            logger.error("Couldn't add user to DB", e);
            throw new DAOException("Couldn't add user to DB");
        }
        return inserted;
    }

    private void setUserToStatement(User user, PreparedStatement statement) throws SQLException {
        try {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhoneNumber());
            statement.setBoolean(6, user.getNotification());
        } catch (SQLException e) {
            logger.error("Couldn't set user to PreparedStatement");
            throw new DAOException("Couldn't set user to PreparedStatement");
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return Collections.emptyList();
    }

    @Override
    public User update(User element) {
        return null;
    }

    @Override
    public boolean delete(User element) {
        return false;
    }

    @Override
    public User getUser(String login, String password) throws AuthException {
        User user = null;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = gerUserFromResultSet(resultSet);
            }
            if (user == null) throw new AuthException("Couldn't find user in DB");
        } catch (SQLException e) {
            logger.error("Couldn't get user from DB", e);
        }
        return user;
    }

    private User gerUserFromResultSet(ResultSet rs) {
        User user = null;
        try {
            user = new User(
                    rs.getInt("user_id"),
                    rs.getString("first_name"),
                    rs.getString("second_name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getBoolean("notification")
            );

            final String phoneNumber = rs.getString("phone_number");
            if (phoneNumber != null)
                user.setPhoneNumber(phoneNumber);

            final User.Role role = getUserRole(rs);
            user.setUserRole(role);

        } catch (SQLException e) {
            logger.error("Couldn't get user from ResultSet", e);
            throw new DAOException("Couldn't get user from ResultSet", e);
        }
        return user;
    }

    private User.Role getUserRole(ResultSet rs) {
        User.Role role = User.Role.GUEST;
        try {
            String roleName = rs.getString("role_name");
            role = User.Role.getUserRoleFromString(roleName);
        } catch (IllegalArgumentException e) {
            logger.error("There is no such User Role", e);
        } catch (SQLException e) {
            logger.error("Couldn't get user's role from ResultSet, so set it GUEST", e);
        }
        return role;
    }
}