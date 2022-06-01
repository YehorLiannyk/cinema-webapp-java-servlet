package yehor.epam.entities;

/**
 * Film's genre
 */
public class Genre extends BaseEntity {
    /**
     * Genre's name
     */
    private String name;

    public Genre(int id, String name) {
        super(id);
        this.name = name;
    }

    public Genre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
