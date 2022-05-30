package yehor.epam.dao;

import yehor.epam.dao.exception.AuthException;
import yehor.epam.entities.User;

import java.util.Map;

public interface UserDAO extends DAO<User> {
    /**
     * Get user by login. Use ONLY after password checking
     *
     * @param login User's login/email
     * @return User
     * @throws AuthException throw if there is no user with this login, in case if password check is skipped
     */
    User getUser(String login) throws AuthException;

    /**
     * Get max if of user table for setting User's id to user object
     *
     * @return
     */
    int getMaxId();

    /**
     * Get Map containing User's salt value and encrypted password by received login
     *
     * @param login User's login/email
     * @return Map containing User's salt value and encrypted password
     * @throws AuthException throw if there is no user with the login or password is incorrect
     */
    Map<String, String> getSaltAndPassByLogin(String login) throws AuthException;
}
