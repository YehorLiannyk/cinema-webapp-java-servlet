package yehor.epam.services;

import yehor.epam.entities.Session;
import yehor.epam.exceptions.ServiceException;

import java.util.List;

public interface SessionService {
    Session getById(int id) throws ServiceException;

    void deleteSession(int id) throws ServiceException;

    void addSession(Session session) throws ServiceException;

    List<Session> getAll() throws ServiceException;
}
