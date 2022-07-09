package yehor.epam.services;

import yehor.epam.entities.User;
import yehor.epam.exceptions.AuthException;
import yehor.epam.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * Authenticate user by its login and plain password
     *
     * @param login    email
     * @param password plain password
     * @throws ServiceException
     * @throws AuthException    throws when couldn't authenticate user
     */
    void authenticateUser(String login, String password) throws ServiceException, AuthException;

    /**
     * Get user by its login
     *
     * @param login user email
     * @return User
     * @throws ServiceException
     */
    User getUserByLogin(String login) throws ServiceException;

    /**
     * Get error list after user validation
     *
     * @param userParamMap map with user params
     * @return List of validate errors
     */
    List<String> getUserValidErrorList(Map<String, String> userParamMap);

    /**
     * Validate user email
     *
     * @param email email
     * @return error param name or blank string when there is no errors
     */
    String validateUserEmail(String email);

    /**
     * Get User by its id
     *
     * @param id user id
     * @return User
     * @throws ServiceException
     */
    User getById(int id) throws ServiceException;

    /**
     * Get max id of user table
     *
     * @return max user id of table
     * @throws ServiceException
     */
    int getMaxId() throws ServiceException;

    /**
     * Save user
     *
     * @param user user
     * @return true if saved, otherwise false
     * @throws ServiceException
     */
    boolean save(User user) throws ServiceException;
}
