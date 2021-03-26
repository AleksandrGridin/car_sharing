package carsharing.items;

public class Company {

    private int currentRowNumber;
    private int ID;
    private String name;

    public Company(int currentRowNumber, int ID, String name) {
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

    @Override
    public String toString() {
        return currentRowNumber + ". " + name;
    }
}
