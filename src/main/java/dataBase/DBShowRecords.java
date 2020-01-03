package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBShowRecords {

    public DBShowRecords() {
        // JDBC driver name and database URL
        String DB_URL = "jdbc:mysql://localhost:3306/project?useSSL=false"; //Database -> project
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

        //  Database credentials
        String user = "root";
        String pass = "root";

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to a `project` database...");
            conn = DriverManager.getConnection(DB_URL, user, pass);
            System.out.println("Connected database successfully...");

            //STEP 3: Create Statement
            stmt = conn.createStatement();
            //STEP 4: Create a query

            //STEP 5: Execute a query
            String select = "select * from credentials";
            ResultSet rs = stmt.executeQuery(select);
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

