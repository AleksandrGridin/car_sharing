package carsharing.dao;

import carsharing.Console;
import carsharing.items.Car;
import carsharing.items.Company;
import carsharing.items.Customer;

import java.util.List;

public class DataBaseUtil {

    private DataBase dataBase = new DataBase();

    public void initDB(String nameDB) {
        dataBase.initDB(nameDB);
    }

    public void addCompany(String name) {
        dataBase.createCompany(name);
    }

    public List<Company> listOfCompanies() {
        return dataBase.listOfCompanies();
    }

    public Company getCompany(int currentRowNumber) {
        return dataBase.listOfCompanies().get(currentRowNumber - 1);
    }

    public List<Car> listOfCars(int company_id) {
        return dataBase.listOfCars(company_id);
    }

    public void addCar(String read, int companyID) {
        dataBase.addCar(read, companyID);
    }

    public Car getCar(int carID) {
        return dataBase.getCar(carID);
    }

    public boolean addCustomer(String nameOfCustomer) {
        return dataBase.createCustomer(nameOfCustomer);
    }

    public List<Customer> listOfCustomers() {
        return dataBase.listOfCustomers();
    }

    public Customer getCustomer(int currentRowNumber) {
        return dataBase.listOfCustomers().get(currentRowNumber - 1);
    }

    public void updateCustomer(int customerID, int carID) {
        dataBase.updateCustomer(customerID, carID);
    }
}
