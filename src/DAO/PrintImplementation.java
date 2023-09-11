package DAO;

import database.Datasource;
import database.MySQL;
import exception.PrintException;
import interfaces.PrintInterface;
import models.Print;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.List;

public class PrintImplementation implements PrintInterface {
    public final Connection con = MySQL.getInstance();

    public PrintImplementation() {
    }

    @Override
    public List<Print> get() throws PrintException {
        try {
            QueryRunner run = new QueryRunner(Datasource.getMySQLDataSource());
            ResultSetHandler<List<Print>> h = new BeanListHandler<>(Print.class);
            return run.query("SELECT * FROM print", h);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

    @Override
    public Boolean get(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM print WHERE Id = ?");
            stmt.setInt(1, print.getId());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                print.getBook().setISBN(result.getInt("ISBN"));
                print.setStatus(result.getString("Status"));
                print.setArchived(result.getBoolean("Archived"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

    @Override
    public Boolean Add(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO print (Id, ISBN) VALUES (?, ?)");
            stmt.setInt(1, print.getId());
            stmt.setInt(2, print.getBook().getISBN());
            return (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

    @Override
    public Boolean Update(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE print SET ISBN = ?, Status = ?, Archived = ? WHERE Id = ?");
            stmt.setInt(1, print.getBook().getISBN());
            stmt.setString(2, print.getStatus());
            stmt.setBoolean(3, print.getArchived());
            stmt.setInt(4, print.getId());
            return  (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

    @Override
    public Boolean Delete(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM print WHERE Id = ?");
            stmt.setInt(1, print.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

    @Override
    public Boolean MakeAvailable(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE print SET Status = 'Available' WHERE Id = ?");
            stmt.setInt(1, print.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

    @Override
    public Boolean Archive(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE print SET Archived = true WHERE Id = ?");
            stmt.setInt(1, print.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }
    @Override
    public Boolean MakeLoaned(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE print SET Status = 'Loaned' WHERE Id = ?");
            stmt.setInt(1, print.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

    public Integer AvailableStats() throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(Id) as available FROM print WHERE Status = 'Available' and Archived = false");
            ResultSet result  = stmt.executeQuery();
            if (result.next())
                return result.getInt("available");
            return null;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

    public Integer LoanedStats() throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(Id) as loaned FROM print WHERE Status = 'Loaned' and Archived = false");
            ResultSet result  = stmt.executeQuery();
            if (result.next())
                return result.getInt("loaned");
            return null;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

    public Integer LostStats() throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(Id) as lost FROM print WHERE Archived = true");
            ResultSet result  = stmt.executeQuery();
            if (result.next())
                return result.getInt("lost");
            return null;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }
    public Integer Total() throws PrintException{
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(Id) as total FROM print WHERE Archived = false");
            ResultSet result  = stmt.executeQuery();
            if (result.next())
                return result.getInt("total");
            return null;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

}
