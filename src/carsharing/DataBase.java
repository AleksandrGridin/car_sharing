package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private String url = "jdbc:h2:/Users/alex/IdeaProjects/jetBrains/Car Sharing/Car Sharing/task/src/carsharing/db/";

    public void initDB(String dataBase) {
        url +=dataBase;
        try (Connection conn = DriverManager.getConnection(url)){
            Class.forName ("org.h2.Driver");
            Statement st = conn.createStatement();

            conn.setAutoCommit(true);

            String sql =  "CREATE TABLE COMPANY " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT," +
                    " NAME VARCHAR UNIQUE NOT NULL);";
            st.executeUpdate(sql);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void listOfCompanies() {
        List<String> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)){
            String listOfCompanies = "SELECT * FROM COMPANY;";
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery(listOfCompanies);
            while (resultSet.next()) {
                String result = "" + resultSet.getInt(1) + ". " + resultSet.getString(2);
                list.add(result);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        if (list.isEmpty()) {
            Console.write("The company list is empty!");
        } else {
            Console.write("Company list:");
            list.forEach(System.out::println);
        }
    }


    public void createCompany() {
        Console.write("Enter the company name: ");
        String company = Console.read();
        String queryAddCompany = "INSERT INTO COMPANY (NAME) VALUES ('" + company + "');";
        try (Connection conn = DriverManager.getConnection(url)){
            Statement st = conn.createStatement();
            st.execute(queryAddCompany);
            Console.write("The company was created!");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }
}
