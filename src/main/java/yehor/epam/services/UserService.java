package yehor.epam.services;

import yehor.epam.entities.User;
import yehor.epam.exceptions.AuthException;
import yehor.epam.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface UserService {
    void authenticateUser(String login, String password) throws ServiceException, AuthException;

    User getUserByLogin(String login) throws ServiceException;

    List<String> getUserValidErrorList(Map<String, String> userParamMap);

    String validateUserEmail(String email);

    User getById(int id) throws ServiceException;

    int getMaxId() throws ServiceException;

    boolean save(User user) throws ServiceException;
}
