package dataBase;

import java.sql.*;
import java.util.Objects;

public class DBConnect {

    public DBConnect() {
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
            String createTable = "CREATE TABLE table_name" +
                    "(id integer not null, " +
                    "userName varchar (50)," +
                    "password varchar (50)," +
                    "primary key (id))";
            String insert = "insert into table1(userName, password) values ('dawid', 'lato2020')";
            //STEP 5: Execute a query
            stmt.executeUpdate(insert);


        } catch(Exception se){
            //Handle errors for JDBC and  Class.forName
            se.printStackTrace();
        }
        finally//finally block used to close resources
        {
            //STEP 6: Close Statement
            try {
                if(stmt!=null) {
                    stmt.close();
                }
            }
            catch(SQLException ignored){}// do nothing
            //STEP 7: Close resources
            try {
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
}
