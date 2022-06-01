package yehor.epam.entities;

/**
 * Class of Session's Seat
 */
public class Seat extends BaseEntity {
    /**
     * Number of row
     */
    private int rowNumber;
    /**
     * Number of place
     */
    private int placeNumber;

    public Seat() {
    }

    public Seat(int id, int rowNumber, int placeNumber) {
        super(id);
        this.rowNumber = rowNumber;
        this.placeNumber = placeNumber;
    }

    public Seat(int rowNumber, int placeNumber) {
        this.rowNumber = rowNumber;
        this.placeNumber = placeNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }


    @Override
    public String toString() {
        return "Seat{" +
                "rowNumber=" + rowNumber +
                ", placeNumber=" + placeNumber +
                '}';
    }
}
