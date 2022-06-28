package yehor.epam.dao;

import yehor.epam.entities.BaseEntity;
import yehor.epam.exceptions.DAOException;

import java.util.List;

public interface PaginatableDao<T extends BaseEntity> {
    /**
     * Get T elements from Database in paginated List
     *
     * @return List of received elements
     */
    List<T> findAll(int start, int size) throws DAOException;

    /**
     * Get total amount of rows
     * @return total amount of rows
     */
    int countTotalRow() throws DAOException;
}
