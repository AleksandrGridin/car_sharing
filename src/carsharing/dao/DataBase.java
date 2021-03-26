package carsharing.dao;

import carsharing.items.Car;
import carsharing.items.Company;
import carsharing.items.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private String url = "jdbc:h2:/Users/alex/IdeaProjects/jetBrains/Car Sharing/Car Sharing/task/src/carsharing/db/";

    private final String dropIfExist = "DROP TABLE IF EXISTS COMPANY;";

    private final String listOfCompanies = "SELECT * FROM COMPANY;";

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

    private final String createTableCustomer = "CREATE TABLE CUSTOMER\n" +
            "(\n" +
            "    ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "    NAME VARCHAR UNIQUE NOT NULL,\n" +
            "    RENTED_CAR_ID INT DEFAULT NULL,\n" +
            "    FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID)\n" +
            ");\n";

    public void initDB(String dataBase) {
        url +=dataBase;
        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()){
            Class.forName ("org.h2.Driver");

            conn.setAutoCommit(true);

            //st.executeUpdate(dropIfExist);
            st.executeUpdate(createTableOfCompanies);
            st.executeUpdate(createTableOfCars);
            st.executeUpdate(createTableCustomer);

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
                Company company =  new Company(resultSet.getInt(1),resultSet.getString(2));
                companies.add(company);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return companies;
    }


    public void createCompany(String name) {
        String queryAddCompany = "INSERT INTO COMPANY (NAME) VALUES ('" + name + "');";

        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()){

            st.executeUpdate(queryAddCompany);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
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

//    public List<Car> listOfCars() {
//
//        List<Car> cars = new ArrayList<>();
//        try (Connection conn = DriverManager.getConnection(url);
//             Statement st = conn.createStatement()){
//
//            ResultSet resultSet = st.executeQuery("SELECT * FROM CAR");
//            while (resultSet.next()) {
//                Car car =  new Car(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
//                cars.add(car);
//            }
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//        return cars;
//    }

    public void addCar(String name, int companyID) {
        String queryAddCompany = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES ( '" + name + "', "+ companyID + " );";

        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()){

            st.executeUpdate(queryAddCompany);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public boolean createCustomer(String nameOfCustomer) {

        String queryCreateOfCustomer = "INSERT INTO CUSTOMER (NAME) VALUES ('" + nameOfCustomer + "');";

        try (Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement()){

            st.executeUpdate(queryCreateOfCustomer);
            return true;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public List<Customer> listOfCustomers() {
        List<Customer> customers = new ArrayList<>();
        String querySelectCustomers = "SELECT ROW_NUMBER() over (ORDER BY ID), ID, NAME, RENTED_CAR_ID FROM CUSTOMER";

        try (Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement()){

            ResultSet resultSet = st.executeQuery(querySelectCustomers);

            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3));
                customer.setCarID(resultSet.getInt(4));
                customers.add(customer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }


    public Car getCar(int carID) {
        Car car;

        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()){

            String getCarQuery = "SELECT * FROM CAR WHERE ID =" + carID + ";";
            ResultSet resultSet = st.executeQuery(getCarQuery);

            if (resultSet.next()) {
                car = new Car(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                return car;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void updateCustomer(int customerID, int carID) {
        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()) {

            String updateCustomerQuery = "UPDATE CUSTOMER " +
                    "SET RENTED_CAR_ID = "+ carID +" " +
                    "WHERE ID = "+ customerID +";";

            if (carID == 0) {
                updateCustomerQuery = "UPDATE CUSTOMER " +
                        "SET RENTED_CAR_ID = "+ null +" " +
                        "WHERE ID = "+ customerID +";";
            }
            st.executeUpdate(updateCustomerQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
