package yehor.epam.entities;

/**
 * Abstract base class of DB Entity
 */
public abstract class BaseEntity {
    private final int id;

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
