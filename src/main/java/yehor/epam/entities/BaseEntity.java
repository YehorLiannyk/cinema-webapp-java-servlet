package yehor.epam.entities;

/**
 * Abstract base class of all Database Entity
 */
public abstract class BaseEntity {
    private int id;

    /**
     * BaseEntity constructor
     *
     * @param id Entity id
     */
    protected BaseEntity(int id) {
        this.id = id;
    }

    protected BaseEntity() {
    }

    public int getId() {
        return id;
    }

    /**
     * Set id for Entity
     *
     * @param id new id for Entity
     */
    public void setId(int id) {
        this.id = id;
    }
}
