package yehor.epam.dao;

import yehor.epam.entities.Session;

import java.util.List;
import java.util.Map;

public interface SessionDAO extends DAO<Session> {
    List<Session> getFilteredAndSortedSessionList(Map<String, String> map);
}
