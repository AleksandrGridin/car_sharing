package carsharing.menu;

import carsharing.Console;
import carsharing.dao.DataBaseUtil;
import carsharing.items.Car;
import carsharing.items.Company;

import java.util.List;
import java.util.Optional;

public class MenuCompany {

    private DataBaseUtil dataBaseUtil;

    public MenuCompany(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }

    public String manageMenuItems() {
        Console.write("1. Car list");
        Console.write("2. Create a car");
        Console.write("0. Back");

        String chooseItem = Console.read();
        Console.write("");
        return chooseItem;
    }


    public void listOfCars(int id) {
        List<Car> cars = dataBaseUtil.listOfCars(id);
        if (cars.isEmpty()) {
            Console.write("The car list is empty!");
        } else {
            Console.write("Car list:");
            cars.forEach(System.out::println);
            Console.write("");
        }
    }

    public void createCar(int companyID) {
        Console.write("Enter the car name:");
        String nameOfCar = Console.read();
        dataBaseUtil.addCar(nameOfCar, companyID);
        Console.write("The car was added!");
    }
}
