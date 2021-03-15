package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:h2:/Users/alex/IdeaProjects/jetBrains/Car Sharing/Car Sharing/task/src/carsharing/db/carsharing.mv.db";

        try {
            Class.forName ("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();

            conn.setAutoCommit(true);

            String sql =  "CREATE TABLE COMPANY (ID INT, NAME VARCHAR );";
            st.executeUpdate(sql);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }
}