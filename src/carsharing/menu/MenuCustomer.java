package carsharing.menu;

import carsharing.Console;
import carsharing.dao.DataBaseUtil;
import carsharing.items.Car;
import carsharing.items.Company;
import carsharing.items.Customer;

import java.util.Optional;

public class MenuCustomer {

    private DataBaseUtil dataBaseUtil;

    public MenuCustomer(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }

    public String manageMenuItems() {
        Console.write("");
        Console.write("1. Rent a car");
        Console.write("2. Return a rented car");
        Console.write("3. My rented car");
        Console.write("0. Back");

        String chooseItem = Console.read();
        Console.write("");
        return chooseItem;
    }

    public void rentedCar(Customer customer) {
        if (customer.getCarID() == 0) {
            Console.write("You didn't rent a car!");
        } else {
            Car car = dataBaseUtil.getCar(customer.getCarID());
            Company company = dataBaseUtil.getCompany(car.getCompanyID());
            Console.write("Your rented car:");
            Console.write(car.getName());
            Console.write("Company:");
            Console.write(company.getName());
            Console.write("");
        }
    }

    public void returnCar(Customer customer) {
        if (customer.getCarID() == 0) {
            Console.write("You didn't rent a car!\n");
        } else {
            dataBaseUtil.updateCustomer(customer.getID(), 0);
            customer.setCarID(0);
            Console.write("You've returned a rented car!\n");
        }
    }
    public void rentACar(Customer customer) {

        if(customer.getCarID() != 0) {
            Console.write("You've already rented a car!");
        } else {
            Optional<Company> company = chooseCompany();
            if (company.isEmpty()) {
                return;
            }

            Optional<Car> car = chooseCar(company.get().getID());
            if (car.isPresent()) {
                customer.setCarID(car.get().getID());
                Console.write("You rented '" + car.get().getName() + "'");
                dataBaseUtil.updateCustomer(customer.getID(), car.get().getID());
            } else {
                return;
            }
        }
    }

    private Optional<Company> chooseCompany() {

        Console.write("Choose a company:");
        dataBaseUtil
                .listOfCompanies()
                .forEach(System.out::println);
        Console.write("0. Back");
        String chooseCompany = Console.read();
        Console.write("");
        if (chooseCompany.equals("0")) {
            return Optional.empty();
        }
        return Optional.of(dataBaseUtil.getCompany(Integer.parseInt(chooseCompany)));
    }

    private Optional<Car> chooseCar(int companyID) {

        Console.write("Choose a car:");
        dataBaseUtil.listOfCars(companyID)
                .forEach(System.out::println);
        Console.write("0. Back");
        String chooseCar = Console.read();
        if (chooseCar.equals("0")) {
            return Optional.empty();
        }
        return Optional.of(dataBaseUtil.getCar(Integer.parseInt(chooseCar)));
    }
}
