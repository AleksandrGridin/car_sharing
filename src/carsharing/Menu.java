package carsharing;

import carsharing.dao.DataBaseUtil;
import carsharing.items.Company;

public class Menu {

    DataBaseUtil dataBase = new DataBaseUtil();

    public Menu(String base) {
        dataBase.initDB(base);
    }

    public void mainMenu() {
        while (true) {

            Console.write("1. Log in as a manager");
            Console.write("0. Exit");

            String chooseItem = Console.read();
            Console.write("");
            if (chooseItem.equals("1")) {
                manegeMenu();
            } else if (chooseItem.equals("0")) {
                break;
            } else {
                Console.write("Wrong number menu\n" );
            }
        }
    }

    public void manegeMenu() {

        while (true) {

            Console.write("1. Company list");
            Console.write("2. Create a company");
            Console.write("0. Back");
            String chooseItem = Console.read();
            Console.write("");

            if (chooseItem.equals("1")) {
                Company company = dataBase.listOfCompanies();
                if (company == null) {
                    Console.write("");
                    continue;
                } else {
                    Console.write("");
                    menuCompany(company);
                }
                Console.write("");
            } else if (chooseItem.equals("2")) {
                Console.write("Enter the company name:");
                String nameOfCompany = Console.read();
                if (dataBase.createCompany(nameOfCompany)) {
                    Console.write("The company was created!");
                }
                Console.write("");
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
            Console.write("1. Car list");
            Console.write("2. Create a car");
            Console.write("0. Back");

            String choice = Console.read();
            Console.write("");

            if (choice.equals("1")) {
                dataBase.listOfCars(company.getID());
            } else if (choice.equals("2")) {
                Console.write("Enter the car name:");
                if (dataBase.addCar(Console.read(), company.getID())) {
                    Console.write("The car was added!");
                }
            } else if (choice.equals("0")) {
                break;
            }
            Console.write("");
        }
    }

}
