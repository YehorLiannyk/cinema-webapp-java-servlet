package yehor.epam.dao;

import yehor.epam.entities.BaseEntity;
import yehor.epam.exceptions.DaoException;

import java.util.List;

public interface DAO<T extends BaseEntity> {
    /**
     * Insert T element to the DataBase
     *
     * @param element element to insert
     * @return true - inserted successfully
     * false - did not insert
     */
    boolean insert(T element) throws DaoException;

    /**
     * Find element by id
     *
     * @param id id of the element to find
     * @return element
     */
    T findById(int id) throws DaoException;

    /**
     * Get all T elements from Database in List
     *
     * @return List of received elements
     */
    List<T> findAll() throws DaoException;

    /**
     * Replace element in Database with one another
     *
     * @param element changed the Database element but with the same id
     * @return the Database element (old version)
     */
    T update(T element) throws DaoException;

    /**
     * Delete the element in Database
     *
     * @param element element to delete
     * @return <code>true</code> if removed successfully;
     * <code>false</code> if element wasn't removed: couldn't find the element in Database or got error.
     */
    boolean delete(T element) throws DaoException;
}
