package carsharing.dao;

import carsharing.Console;
import carsharing.items.Car;
import carsharing.items.Company;

import java.util.List;

public class DataBaseUtil {

    private DataBase dataBase = new DataBase();

    public void initDB(String nameDB) {
        dataBase.initDB(nameDB);
    }

    public boolean createCompany(String name) {
        return dataBase.createCompany(name);
    }

    public Company listOfCompanies() {
        List<Company> companies = dataBase.listOfCompanies();
        if (companies.isEmpty()) {
            Console.write("The company list is empty!");
        } else {
            Console.write("Choose the company:");
            companies.forEach(System.out::println);
            Console.write("0. Back");
            return getCompany(companies);
        }
        return null;
    }

    private Company getCompany(List<Company> listOfCompanies) {
        int company = Integer.parseInt(Console.read());
        if (company == 0) {
            return null;
        }
        return listOfCompanies.get(company - 1);
    }

    public void listOfCars(int id) {
        List<Car> cars = dataBase.listOfCars(id);
        if (cars.isEmpty()) {
            Console.write("The car list is empty!");
        } else {
            Console.write("Car list:");
            cars.forEach(System.out::println);
        }
    }


    public boolean addCar(String read, int companyID) {
        return dataBase.addCar(read, companyID);
    }
}
