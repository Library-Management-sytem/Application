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
    public Print Add(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO print (ISBN) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, print.getBook().getISBN());
            if (stmt.executeUpdate() > 0) {
                try {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        print.setId(generatedKeys.getInt(1));
                        return print;
                    } else {
                        System.out.println("Creating print failed, no ID obtained.");
                        throw new SQLException("Creating print failed, no ID obtained.");
                    }
                } catch (NullPointerException n) {
                    n.fillInStackTrace();
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

    @Override
    public Print Update(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE print SET ISBN = ?, Status = ?, Archived = ? WHERE Id = ?");
            stmt.setInt(1, print.getBook().getISBN());
            stmt.setString(2, print.getStatus());
            stmt.setBoolean(3, print.getArchived());
            stmt.setInt(4, print.getId());
            if (stmt.executeUpdate() > 0) return print;
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PrintException(e.getMessage());
        }
    }

    @Override
    public Print Delete(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM print WHERE Id = ?");
            stmt.setInt(1, print.getId());
            if (stmt.executeUpdate() > 0) return print;
            return null;
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

}
