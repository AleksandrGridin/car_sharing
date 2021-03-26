package carsharing.menu;

import carsharing.Console;
import carsharing.dao.DataBaseUtil;
import carsharing.items.Company;
import carsharing.items.Customer;

import java.util.Optional;

public class Menu {

    private DataBaseUtil dataBase = new DataBaseUtil();
    private MainMenu mainMenu = new MainMenu(dataBase);
    private ManageMenu manageMenu = new ManageMenu(dataBase);
    private MenuCompany menuCompany = new MenuCompany(dataBase);
    private MenuCustomer menuCustomer = new MenuCustomer(dataBase);

    public Menu(String base) {
        dataBase.initDB(base);
    }

    public void mainMenu() {
        while (true) {

            String chooseItem = mainMenu.mainMenuItems();

            if (chooseItem.equals("1")) {
                manageMenu();
            } else if (chooseItem.equals("2")) {
                Optional<Customer> optional = mainMenu.listCustomers();
                if (optional.isPresent()) {
                    Customer customer = optional.get();
                    menuCustomer(customer);
                }
            } else if (chooseItem.equals("3")) {
                mainMenu.createCustomer();
            } else if (chooseItem.equals("0")) {
                break;
            } else {
                Console.write("Wrong number menu\n" );
            }
        }
    }

    public void manageMenu() {

        while (true) {

            String chooseItem = manageMenu.manageMenuItems();

            if (chooseItem.equals("1")) {
                Optional<Company> optional = manageMenu.listCompanies();
                if (optional.isPresent()) {
                    Company company = optional.get();
                    menuCompany(company);
                }
                Console.write("");
            } else if (chooseItem.equals("2")) {
                manageMenu.createCompany();
            } else if (chooseItem.equals("0")) {
                break;
            } else {
                Console.write("Wrong number menu\n");
            }
        }
    }

    private void menuCompany(Company company) {
        Console.write("'" + company.getName() + "' company");

        while (true) {

            String chooseItem = menuCompany.manageMenuItems();

            if (chooseItem.equals("1")) {
                menuCompany.listOfCars(company.getID());
            } else if (chooseItem.equals("2")) {
                menuCompany.createCar(company.getID());
            } else if (chooseItem.equals("0")) {
                break;
            }
            Console.write("");
        }
    }

    private void menuCustomer(Customer customer) {

        while (true) {

            String chooseItem = menuCustomer.manageMenuItems();

            if (chooseItem.equals("1")) {
                menuCustomer.rentACar(customer);
            } else if (chooseItem.equals("2")) {
                menuCustomer.returnCar(customer);
            } else if (chooseItem.equals("3")) {
                menuCustomer.rentedCar(customer);
            } else if (chooseItem.equals("0")) {
                break;
            }
        }
    }



}
