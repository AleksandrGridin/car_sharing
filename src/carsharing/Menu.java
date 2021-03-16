package carsharing;

public class Menu {

    private DataBase dataBase = new DataBase();

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
                dataBase.listOfCompanies();
                Console.write("");
            } else if (chooseItem.equals("2")) {
                dataBase.createCompany();
                Console.write("");
            } else if (chooseItem.equals("0")) {
                break;
            } else {
                Console.write("Wrong number menu\n");
            }
        }
    }

}
