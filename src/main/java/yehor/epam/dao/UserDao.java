package yehor.epam.dao;

import yehor.epam.exceptions.AuthException;
import yehor.epam.entities.User;
import yehor.epam.exceptions.DaoException;

import java.util.Map;

public interface UserDao extends DAO<User> {
    /**
     * Get user by login. Use ONLY after password checking
     *
     * @param login User's login/email
     * @return User
     * @throws AuthException throw if there is no user with this login, in case if password check is skipped
     */
    User getUserByLogin(String login) throws AuthException, DaoException;

    /**
     * Get max if of user table for setting User's id to user object
     *
     * @return max id of user's table
     */
    int getMaxId() throws DaoException;

    /**
     * Get Map containing User's salt value and encrypted password by received login
     *
     * @param login User's login/email
     * @return Map containing User's salt value and encrypted password
     * @throws AuthException throw if there is no user with the login or password is incorrect
     */
    Map<String, String> getSaltAndPassByLogin(String login) throws AuthException, DaoException;
}
