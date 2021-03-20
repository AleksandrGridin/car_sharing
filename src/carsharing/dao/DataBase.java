package carsharing.dao;

import carsharing.Console;
import carsharing.items.Car;
import carsharing.items.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private String url = "jdbc:h2:/Users/alex/IdeaProjects/jetBrains/Car Sharing/Car Sharing/task/src/carsharing/db/";
    private final String listOfCompanies = "SELECT * FROM COMPANY;";

    private final String dropIfExist = "DROP TABLE IF EXISTS CAR ;\n" +
            "DROP TABLE IF EXISTS COMPANY;";

    private final String createTableOfCompanies =  "CREATE TABLE COMPANY\n" +
            "(\n" +
            "    ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "    NAME VARCHAR UNIQUE NOT NULL\n" +
            ");";

    private final String createTableOfCars = "CREATE TABLE CAR\n" +
            "(\n" +
            "    ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "    NAME VARCHAR UNIQUE NOT NULL,\n" +
            "    COMPANY_ID INT NOT NULL,\n" +
            "    FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)\n" +
            ");";

    public void initDB(String dataBase) {
        url +=dataBase;
        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()){
            Class.forName ("org.h2.Driver");

            conn.setAutoCommit(true);

            st.executeUpdate(dropIfExist);
            st.executeUpdate(createTableOfCompanies);
            st.executeUpdate(createTableOfCars);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Company> listOfCompanies() {
        List<Company> companies = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()){

            ResultSet resultSet = st.executeQuery(listOfCompanies);
            while (resultSet.next()) {
                Company company =  new Company(resultSet.getInt(1), resultSet.getString(2));
                companies.add(company);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return companies;
    }


    public boolean createCompany(String name) {
        String queryAddCompany = "INSERT INTO COMPANY (NAME) VALUES ('" + name + "');";

        boolean exec = false;

        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()){

            if (st.executeUpdate(queryAddCompany) == 1) {
                exec = true;
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return exec;
    }

    public List<Car> listOfCars(int id) {

        List<Car> cars = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()){

            ResultSet resultSet = st.executeQuery("SELECT ROW_NUMBER() over (ORDER BY ID)" +
                    " row_numb, NAME, COMPANY_ID  " +
                    "FROM CAR WHERE CAR.COMPANY_ID = " + id + ";");
            while (resultSet.next()) {
                Car car =  new Car(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                cars.add(car);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return cars;
    }

    public boolean addCar(String name, int companyID) {
        String queryAddCompany = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES ( '" + name + "', "+ companyID + " );";

        boolean exec = false;

        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()){

            st.executeUpdate(queryAddCompany);
            exec = true;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return exec;
    }
}
