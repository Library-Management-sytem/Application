package implementation;

import database.MySQL;

import java.sql.Connection;

public class ServiceImplementation {
    private final Connection con = MySQL.provideConnection();

    public ServiceImplementation() {
    }

//    public Integer getServiceId(Integer isbn, String email) throws SQLException {
//        Integer clientId = Client.getClientByEmail(email);
//        if (clientId != null) {
//            PreparedStatement stmt = con.prepareStatement("SELECT Id FROM service WHERE BookId = ? AND ClientId = ? AND returned = false LIMIT 1");
//            stmt.setInt(1, isbn);
//            stmt.setInt(2, clientId);
//            ResultSet result = stmt.executeQuery();
//            if (result.next())
//                return result.getInt("Id");
//        }
//        System.out.println("Service not found, please enter a valid ISBN or email");
//        return null;
//    }
//
//    public Boolean returnBook(Integer isbn, String email) throws SQLException {
//        Integer borrowId = this.getServiceId(isbn, email);
//        if (borrowId != null) {
//            PreparedStatement stmt = con.prepareStatement("UPDATE service SET returned = true WHERE Id = " + borrowId);
//            if (Print.makeAvailable(isbn))
//                return stmt.executeUpdate() > 0;
//        }
//        return false;
//    }
//
//    // This method searches for the user by email and returns its ID if it exists, returns null id not
//    public Integer checkClient(String email) throws SQLException {
//        PreparedStatement stmt = con.prepareStatement("SELECT Id, Email FROM client WHERE Email = ? LIMIT 1");
//        stmt.setString(1, email);
//        ResultSet result = stmt.executeQuery();
//        while (result.next()) {
//            String resultEmail = result.getString("Email");
//            if (resultEmail.equals(email))
//                return result.getInt("Id");
//        }
//        return null;
//    }
//
//    public Boolean loan(String clientEmail, String clientName, Integer bookId, String returnDate) {
//        return true;
//    }
}
