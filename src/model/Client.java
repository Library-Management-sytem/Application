package model;

import utility.DBUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {
    private final Connection con;

    public Client() {
        DBUtility db = new DBUtility();
        this.con = db.provideConnection();
    }

    public Boolean add(String name, String email) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO client (Name, Email) VALUES (?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, email);
        return stmt.executeUpdate() > 0;
    }

    public static Integer getClientByEmail(String email) throws SQLException {
        DBUtility db = new DBUtility();
        Connection con = db.provideConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT Id FROM client WHERE Email = ?");
        stmt.setString(1, email);
        ResultSet result = stmt.executeQuery();
        if (result.next())
            return result.getInt("Id");
        return null;
    }
}
