package carsharing;

import carsharing.menu.Menu;

public class Main {
    public static void main(String[] args) {

        String dataBase = "carsharing";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-databaseFileName")) {
                dataBase = args[i + 1];
            }
        }

        Menu menu = new Menu(dataBase);
        menu.mainMenu();
        Console.close();
    }
}