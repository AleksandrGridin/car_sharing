package carsharing.items;

public class Customer {

    private int currentRowNumber;
    private int ID;
    private String name;
    private int carID;

    public Customer(int currentRowNumber, int ID, String name) {
        this.currentRowNumber = currentRowNumber;
        this.ID = ID;
        this.name = name;
    }

    public int getCurrentRowNumber() {
        return currentRowNumber;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    @Override
    public String toString() {
        return currentRowNumber + ". " +name;
    }
}
