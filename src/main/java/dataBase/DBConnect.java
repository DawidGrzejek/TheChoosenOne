package dataBase;

import DBEntities.CredentialsEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnect {
    // JDBC driver name and database URL
    private static String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/project?useSSL=false"; //Database -> project
    private static String DEFAULT_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    //  Database credentials
    private static String DEFAULT_user = "root";
    private static String DEFAULT_pass = "root";

    private static Connection conn = null;
    private static Statement stmt = null;


    public DBConnect(typeOfQuery typeOfQuery, String query) {
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
            if(typeOfQuery == DBConnect.typeOfQuery.EXECUTE)
            stmt.executeQuery(query);
            else if(typeOfQuery == DBConnect.typeOfQuery.UPDATE)
                stmt.executeUpdate(query);
            else{
                System.out.print("Unknown query type");
            }
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
            //String select = "select * from table1";


            stmt.close();
            conn.close();
        }catch (Exception ex){
            System.out.println("Error" + ex);

        }
        System.out.println("Goodbye!");
    }


    //Is it most efficient and elegant way? That's the question.
    public static List<CredentialsEntity> list(String query){
        List<CredentialsEntity> credencials = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(DEFAULT_DB_URL, DEFAULT_user, DEFAULT_pass);
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
        ){
            while (resultSet.next()){
                CredentialsEntity credentialsEntity = new CredentialsEntity();
                credentialsEntity.setId(resultSet.getInt("id"));
                credentialsEntity.setUserName(resultSet.getString("userName"));
                credentialsEntity.setPassword(resultSet.getString("password"));
                credencials.add(credentialsEntity);
            }
        }catch (Exception ex){
            System.out.print("Error" + ex);
        }
        return credencials;
    }

    public enum typeOfQuery{
        UPDATE,
        EXECUTE
    }

}
