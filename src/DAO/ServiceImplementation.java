package DAO;

import database.Datasource;
import database.MySQL;
import exception.ServiceException;
import interfaces.ServiceInterface;
import models.Book;
import models.Client;
import models.Service;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceImplementation implements ServiceInterface {
    private final Connection con = MySQL.getInstance();

    public ServiceImplementation() {
    }

    @Override
    public Boolean get(Service service) throws ServiceException {
        return false;
    }
    @Override
    public List<Service> get() throws ServiceException {
        try {
            QueryRunner run = new QueryRunner(Datasource.getMySQLDataSource());
            ResultSetHandler<List<Service>> h = new BeanListHandler<>(Service.class);
            return run.query("SELECT * FROM service", h);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    Service GetOne(Book book, Client client) throws ServiceException {
//        try {
//            PreparedStatement stmt = con.prepareStatement("SELECT * FROM service WHERE PrintId = ? AND ClientId = ? AND returned = false LIMIT 1");
//            stmt.setInt(1, book.getISBN());
//            stmt.setInt(2, client.getId());
//            ResultSet result = stmt.executeQuery();
//            Service service = new Service();
//            if (result.next()){
//                service.setBorrowDate(result.getString("BorrowDate"));
//                service.setBorrowDate(result.getString("ReturnDate"));
//                service.setClient(client);
//                service.setBoo(book);
//
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            throw new ServiceException(e.getMessage());
//        }
        return new Service();
    }

    @Override
    public Service Add(Service service) throws ServiceException {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO service (BorrowDate, ReturnDate, ClientId, returned, PrintId) VALUES (?,?,?,?,?)");
            stmt.setString(1, service.getBorrowDate());
            stmt.setString(1, service.getReturnDate());
            stmt.setInt(1, service.getClient().getId());
            stmt.setBoolean(1, service.getReturned());
            stmt.setInt(1, service.getPrint().getId());
            if (stmt.executeUpdate() > 0)
                return service;
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public Service Update(Service service) throws ServiceException {
        try {
            PreparedStatement stmt =  con.prepareStatement("");
            return service;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }


    @Override
    public Service Delete(Service service) throws ServiceException {
        try {
            PreparedStatement stmt =  con.prepareStatement("");
            return service;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
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
    public Boolean loan(Service service) {
        try {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO service (BorrowDate, ReturnDate, ClientId, PrintId) VALUES (?,?,?,?)");
        stmt.setString(1, service.getBorrowDate());
        stmt.setString(2, service.getReturnDate());
        stmt.setInt(3, service.getClient().getId());
        stmt.setInt(4, service.getPrint().getId());
        if (stmt.executeUpdate() > 0)
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
