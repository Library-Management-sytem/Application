package implementation;

import database.MySQL;
import java.sql.Connection;

public class ClientImplementation {
    private final Connection con = MySQL.provideConnection();

    public ClientImplementation() {
    }

//    public Boolean add(String name, String email) throws SQLException {
//        PreparedStatement stmt = con.prepareStatement("INSERT INTO client (Name, Email) VALUES (?, ?)");
//        stmt.setString(1, name);
//        stmt.setString(2, email);
//        return stmt.executeUpdate() > 0;
//    }
//
//    public static Integer getClientByEmail(String email) throws SQLException {
//        Connection con = MySQL.provideConnection();
//        PreparedStatement stmt = con.prepareStatement("SELECT Id FROM client WHERE Email = ?");
//        stmt.setString(1, email);
//        ResultSet result = stmt.executeQuery();
//        if (result.next())
//            return result.getInt("Id");
//        return null;
//    }
}
