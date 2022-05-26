package yehor.epam.entities;

public class Seat extends BaseEntity {
    private int rowNumber;
    private int placeNumber;

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
