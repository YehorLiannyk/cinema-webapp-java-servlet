package yehor.epam.services;

import yehor.epam.entities.User;
import yehor.epam.exceptions.AuthException;
import yehor.epam.exceptions.ServiceException;

public interface UserService {
    void authenticateUser(String login, String password) throws ServiceException, AuthException;

    User getUserByLogin(String login) throws ServiceException;

    User getById(int id) throws ServiceException;

    int getMaxId() throws ServiceException;

    boolean save(User user) throws ServiceException;
}
