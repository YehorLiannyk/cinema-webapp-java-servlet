package yehor.epam.dao;

import yehor.epam.dao.exception.AuthException;
import yehor.epam.entities.User;

import java.util.Map;

public interface UserDAO extends DAO<User> {
    User getUser(String login) throws AuthException;

    int getMaxId();

    Map<String, String> getSaltAndPassByLogin(String login) throws AuthException;
}
