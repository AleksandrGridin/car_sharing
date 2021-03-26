package carsharing.items;

public class Car {

    private int ID;
    private String name;
    private int companyID;

    public Car(int ID, String name, int companyID) {
        this.ID = ID;
        this.name = name;
        this.companyID = companyID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getCompanyID() {
        return companyID;
    }

    @Override
    public String toString() {
        return ID + ". " + name;
    }
}
