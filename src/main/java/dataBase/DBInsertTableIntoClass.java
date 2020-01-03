package dataBase;

import DBEntities.CredentialsEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBInsertTableIntoClass {
    String DB_URL = "jdbc:mysql://localhost:3306/project?useSSL=false"; //Database -> project
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String user = "root";
    String pass = "root";

    public DBInsertTableIntoClass() {
        Connection conn = null;
        Statement stmt = null;
        List<CredentialsEntity> credencials = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, user, pass);
            stmt = conn.createStatement();

            String select = "select * from credentials";
            ResultSet rs = stmt.executeQuery(select);

            while(rs.next()){
                int id  = rs.getInt("id");
                String userName = rs.getString("UserName");
                String password = rs.getString("Password");
                System.out.printf("ID:%s Username: %s Password: %s%n", id, userName, password);

                CredentialsEntity credentialsEntity = new CredentialsEntity();
                credentialsEntity.setId(rs.getInt("id"));
                credentialsEntity.setUserName(rs.getString("userName"));
                credentialsEntity.setPassword(rs.getString("password"));
                credencials.add(credentialsEntity);
            }
            stmt.close();
            conn.close();

        }catch (Exception ex){
            System.out.println("Error" + ex);

        }
        System.out.println("Goodbye!");
    }

}
