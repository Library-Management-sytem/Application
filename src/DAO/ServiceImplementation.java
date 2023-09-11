package DAO;

import database.Datasource;
import database.MySQL;
import exception.ServiceException;
import interfaces.ServiceInterface;
import models.Service;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServiceImplementation implements ServiceInterface {
    private final Connection con = MySQL.getInstance();

    public ServiceImplementation() {
    }

    @Override
    public Boolean get(Service service) throws ServiceException {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM service WHERE PrintId = ? AND returned = false LIMIT 1");
            stmt.setInt(1, service.getPrint().getId());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                service.setBorrowDate(result.getString("BorrowDate"));
                service.setBorrowDate(result.getString("ReturnDate"));
                service.getClient().setId(result.getInt("ClientId"));
                service.getPrint().setId(result.getInt("PrintId"));
                service.setReturned(result.getBoolean("Returned"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Service> get() throws ServiceException {
        try {
            QueryRunner run = new QueryRunner(Datasource.getMySQLDataSource());
            ResultSetHandler<List<Service>> h = new BeanListHandler<>(Service.class);
            return run.query("SELECT * FROM service where returned = false", h);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
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
            PreparedStatement stmt = con.prepareStatement("");
            return service;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }


    @Override
    public Service Delete(Service service) throws ServiceException {
        try {
            PreparedStatement stmt = con.prepareStatement("");
            return service;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    public Boolean returnPrint(Service service) throws ServiceException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE service SET returned = true WHERE PrintId = ?");
            stmt.setInt(1, service.getPrint().getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException s) {
            System.out.println(s.getMessage());
            throw new ServiceException(s.getMessage());
        }
    }

    public Boolean loan(Service service) {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO service (BorrowDate, ReturnDate, ClientId, PrintId) VALUES (?,?,?,?)");
            stmt.setString(1, service.getBorrowDate());
            stmt.setString(2, service.getReturnDate());
            stmt.setInt(3, service.getClient().getId());
            stmt.setInt(4, service.getPrint().getId());
            if (stmt.executeUpdate() > 0)
                return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
