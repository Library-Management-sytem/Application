package DAO;

import database.MySQL;
import exception.ServiceException;
import interfaces.ServiceInterface;
import models.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServiceImplementation implements ServiceInterface {
    private final Connection con = MySQL.getInstance();

    public ServiceImplementation() {
    }

    /**
     * @param service
     * @return
     * @throws SQLException
     */
    @Override
    public Service get(Service service) throws ServiceException {
        try {
            PreparedStatement stmt =  con.prepareStatement("");
            return service;

        }catch(SQLException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    Integer GetId(Service service) throws ServiceException {
        try {
            PreparedStatement stmt =  con.prepareStatement("");
            return 5;
//            PreparedStatement stmt = con.prepareStatement("SELECT Id FROM service WHERE BookId = ? AND ClientId = ? AND returned = false LIMIT 1");
//            stmt.setInt(1, service.getB);
//            stmt.setInt(2, clientId);
//            ResultSet result = stmt.executeQuery();
//            if (result.next())
//                return result.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param service
     * @return
     * @throws SQLException
     */
    @Override
    public Service Add(Service service) throws ServiceException {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO service (BorrowDate, ReturnDate, UsersId, ClientId, returned, PrintId) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, service.getBorrowDate());
            stmt.setString(1, service.getReturnDate());
            stmt.setInt(1, service.getUserId());
            stmt.setInt(1, service.getClientId());
            stmt.setBoolean(1, service.getReturned());
            stmt.setInt(1, service.getPrintId());
            if (stmt.executeUpdate() > 0)
                return service;
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    /**
     * @param service
     * @return
     * @throws SQLException
     */
    @Override
    public Service Update(Service service) throws ServiceException {
        try {
            PreparedStatement stmt =  con.prepareStatement("");
            return service;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param service
     * @return
     * @throws SQLException
     */
    @Override
    public Service Delete(Service service) throws ServiceException {
        try {
            PreparedStatement stmt =  con.prepareStatement("");
            return service;

        }catch(SQLException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
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
