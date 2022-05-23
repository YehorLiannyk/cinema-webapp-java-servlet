/*
package yehor.epam.dao.mysql;

import org.apache.log4j.Logger;
import yehor.epam.dao.BaseDAO;
import yehor.epam.dao.UserRoleDAO;
import yehor.epam.dao.exception.DAOException;
import yehor.epam.entities.User;
import yehor.epam.entities.UserRole;
import yehor.epam.utilities.LoggerManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLUserRoleDAO extends BaseDAO implements UserRoleDAO {
    private static final Logger logger = LoggerManager.getLogger(MySQLUserRoleDAO.class);
    private String SELECT_USER_ROLE = "SELECT r.role_id, r.role_name FROM users u JOIN roles r ON u.role_id=r.role_id WHERE u.user_id=?";

    @Override
    public boolean insert(UserRole element) {
        return false;
    }

    @Override
    public UserRole findById(int id) {
        return null;
    }

    @Override
    public List<UserRole> findAll() {
        return null;
    }

    @Override
    public UserRole update(UserRole element) {
        return null;
    }

    @Override
    public boolean delete(UserRole element) {
        return false;
    }

    @Override
    public UserRole getUserRole(int userId) {
        User.Role userRole = null;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_USER_ROLE)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userRole = getUserRoleFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get user's role from DB", e);
            throw new DAOException("Couldn't get user's role from DB");
        }
        return userRole;
    }

    */
/*public UserRole getUserRole(int userId) {
        UserRole userRole = null;
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_USER_ROLE)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userRole = getUserRoleFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Couldn't get user's role from DB", e);
            throw new DAOException("Couldn't get user's role from DB");
        }
        return userRole;
    }*//*


    private UserRole getUserRoleFromResultSet(ResultSet rs) {
        UserRole userRole = null;
        try {
            userRole = new UserRole(
                    rs.getInt("role_id"),
                    rs.getString("role_name")
            );
        } catch (SQLException e) {
            logger.error("Couldn't get userRole from ResultSet", e);
            throw new DAOException("Couldn't get userRole from ResultSet");
        }
        return userRole;
    }
}
*/
