package yehor.epam.entities;

/**
 * Abstract base class of all DB Entity
 */
public abstract class BaseEntity {
    private int id;

    /**
     * BaseEntity constructor
     * @param id id of Entity
     */
    protected BaseEntity(int id) {
        this.id = id;
    }

    /**
     * Not recommended to use this constructor as might be forgotten to set the id for Entity
     */
    protected BaseEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
