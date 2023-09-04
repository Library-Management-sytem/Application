package database;

import interfaces.DBUtility;

import java.sql.*;
import java.util.ResourceBundle;

public class MySQL implements DBUtility {
    public static String driver;
    public static String url;
    public static String username;
    public static String password;



    static {
        ResourceBundle rd =ResourceBundle.getBundle("db");
        driver=rd.getString("db.driver");
        url=rd.getString("db.url");
        username=rd.getString("db.username");
        password=rd.getString("db.password");
    }
    public static Connection provideConnection(){
        Connection connection = null;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.getException().getMessage();
        }
//        String url="jdbc:mysql://localhost:3306/library";

        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }

}