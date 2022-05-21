package yehor.epam.dao;

import yehor.epam.entities.BaseEntity;

import java.util.List;

public interface DAO<T extends BaseEntity>{
    /**
     * Insert T element to the DataBase
     *
     * @param element element to insert
     * @return true - inserted successfully
     * false - did not insert
     */
    boolean insert(T element);

    /**
     * Find element by id
     *
     * @param id id of the element to find
     * @return element
     */
    T findById(int id);

    /**
     * Get all T elements from DB in List
     *
     * @return List of received elements
     */
    List<T> findAll();

    /**
     * Replace element in DB with one another
     *
     * @param element changed the DB element but with the same id
     * @return the DB element (old version)
     */
    T update(T element);

    /**
     * Delete the element in DB
     *
     * @param element element to delete
     * @return <code>true</code> if removed successfully;
     * <code>false</code> if element wasn't removed: couldn't find the element in DB or got error.
     */
    boolean delete(T element);
}
