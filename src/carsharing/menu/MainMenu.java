package carsharing.menu;

import carsharing.Console;
import carsharing.dao.DataBaseUtil;
import carsharing.items.Customer;

import java.util.List;
import java.util.Optional;

public class MainMenu {

    private DataBaseUtil dataBaseUtil;

    public MainMenu(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }

    public String mainMenuItems() {
        Console.write("1. Log in as a manager");
        Console.write("2. Log in as a customer");
        Console.write("3. Create a customer");
        Console.write("0. Exit");

        String chooseItem = Console.read();
        Console.write("");
        return chooseItem;
    }

    public Optional<Customer> listCustomers() {
        List<Customer> customers = dataBaseUtil.listOfCustomers();
        if (customers.isEmpty()) {
            Console.write("The customer list is empty!\n");
            return Optional.empty();
        } else {
            Console.write("Customer list:");
            customers.forEach(System.out::println);
            Console.write("0. Back");
            return getCustomer();
        }
    }

    private Optional<Customer> getCustomer() {
        String chooseItem = Console.read();
        if (chooseItem.equals("0")) {
            return Optional.empty();
        }
        Console.write("");
        return Optional.of(dataBaseUtil.getCustomer(Integer.parseInt(chooseItem)));
    }

    public void createCustomer() {
        Console.write("Enter the customer name:");
        if (dataBaseUtil.addCustomer(Console.read())) {
            Console.write("The customer was added!");
            Console.write("");
        }
    }
}
