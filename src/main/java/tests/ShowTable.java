package tests;

import DBEntities.CredentialsEntity;
import dataBase.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowTable {
    // JDBC driver name and database URL
    private static String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/project?useSSL=false"; //Database -> project
    private static String DEFAULT_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    //  Database credentials
    private static String DEFAULT_user = "root";
    private static String DEFAULT_pass = "root";

    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] Args){
        try {
            //STEP 1: Register JDBC driver
            Class.forName(DEFAULT_JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to a `project` database...");
            conn = DriverManager.getConnection(DEFAULT_DB_URL, DEFAULT_user, DEFAULT_pass);
            System.out.println("Connected database successfully...");

            //STEP 3: Create Statement
            stmt = conn.createStatement();
            //STEP 4: Create a query
            //Get query from argument
            //STEP 5: Execute a query
            //language=sql
            String query = "SELECT * from credentials";
            ResultSet rs = stmt.executeQuery(query);

            //Show records
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String userName = rs.getString("UserName");
                String password = rs.getString("Password");
                //Display values
                System.out.printf("ID:%s Username: %s Password: %s%n", id, userName, password);
            }

            stmt.close();
            conn.close();
        }catch (Exception ex){
            System.out.println("Error" + ex);

        }
        System.out.println("Goodbye!");
    }
}
