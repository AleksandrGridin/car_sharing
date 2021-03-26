package carsharing.menu;

import carsharing.Console;
import carsharing.dao.DataBaseUtil;
import carsharing.items.Company;

import java.util.List;
import java.util.Optional;

public class ManageMenu {

    private DataBaseUtil dataBaseUtil;

    public ManageMenu(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }

    public String manageMenuItems() {
        Console.write("1. Company list");
        Console.write("2. Create a company");
        Console.write("0. Back");

        String chooseItem = Console.read();
        Console.write("");
        return chooseItem;
    }

    public void createCompany() {
        Console.write("Enter the company name:");
        String nameOfCompany = Console.read();
        boolean ifExists = dataBaseUtil.listOfCompanies()
                .stream()
                .anyMatch(company -> company
                        .getName()
                        .equals(nameOfCompany));
        if (!ifExists) {
            dataBaseUtil.addCompany(nameOfCompany);
        }
        Console.write("The company was created!\n");
    }

    public Optional<Company> listCompanies() {
        List<Company> companies = dataBaseUtil.listOfCompanies();
        if (companies.isEmpty()) {
            Console.write("The company list is empty!\n");
            return Optional.empty();
        } else {
            Console.write("Choose the company:");
            companies.forEach(System.out::println);
            Console.write("0. Back");
            return getCompany();
        }

    }

    private Optional<Company> getCompany() {
        String chooseItem = Console.read();
        if (chooseItem.equals("0")) {
            return Optional.empty();
        }
        Console.write("");
        return Optional.of(dataBaseUtil.getCompany(Integer.parseInt(chooseItem)));
    }
}
