package model;

import utility.DBUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Client {
    private Connection con;
    public Client(){
        DBUtility db = new DBUtility();
        this.stmt = db.provideConnection();
    }

    public Boolean add(String name, String email) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO client (Name, Email) VALUES (?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, email);
        return stmt.execute();
    }
}
