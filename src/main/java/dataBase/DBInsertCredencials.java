package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBInsertCredencials {

    public DBInsertCredencials(String un, String ps) {
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
            /*String insert = "insert into `credencials` (userName, password)" +
                    "values ('"+un+"', '"+ps+"')";*/
            String insert = "insert into credentials (userName, password) " +
                            "values ('"+un+"', '"+ps+"')";
                    //STEP 5: Execute a query
            stmt.executeUpdate(insert);


            stmt.close();
            conn.close();
        }catch (Exception ex){
            System.out.println("Error" + ex);

        }
        System.out.println("Goodbye!");
    }
}

