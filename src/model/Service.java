package model;

import utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Service {
    private final Connection con;
    public Service() {
        DBUtility db = new DBUtility();
        this.con = db.provideConnection();
    }
    public Boolean returnBook(String clientName, String email) {
        return true;

    }
    // This method searches for the user by email and returns its ID if it exists, returns null id not
    public Integer checkClient(String email) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT Id, Email FROM client WHERE Email = ?");
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                String resultEmail = result.getString("Email");
                if (Objects.equals(resultEmail, email))
                    return result.getInt("Id");
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean loan() {
        return true;
    }
}
