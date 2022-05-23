package yehor.epam.dao;

import yehor.epam.dao.exception.AuthException;
import yehor.epam.entities.User;

public interface UserDAO extends DAO<User> {
    User getUser(String login, String password) throws AuthException;

}
