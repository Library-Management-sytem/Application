package model;

import utility.DBUtility;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

import static utility.DBUtility.resultSetToTableModel;

public class User {
    private Statement stmt;

    public User() throws SQLException {
        try {
        utility.DBUtility db = new DBUtility();
        Connection con = db.provideConnection();
        this.stmt = con.createStatement();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean login(String email, String password) throws SQLException {
        stmt.executeQuery("SELECT Email, Password FROM user WHERE Email = ? AND Password = ?");
        return true;
    }

    public DefaultTableModel getUsers() throws SQLException {
        ResultSet result = stmt.executeQuery("SELECT * FROM user");
        System.out.println(result);
        return resultSetToTableModel(null, result);
    }
}
