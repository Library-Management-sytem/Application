package DAO;

import database.MySQL;
import exception.PrintException;
import interfaces.PrintInterface;
import models.Print;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintImplementation implements PrintInterface {
    public final Connection con = MySQL.getInstance();

    public PrintImplementation() {
    }

    /**
     * @param print
     * @return
     * @throws SQLException
     */
    @Override
    public Print get(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM print");
            return (Print) stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PrintException(e.getMessage());
        }
    }

    /**
     * @param print
     * @return
     * @throws PrintException
     */
    @Override
    public Integer getId(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("");
            return 5;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PrintException(e.getMessage());
        }
    }

    /**
     * @param print
     * @return
     * @throws SQLException
     */
    @Override
    public Print Add(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO print (ISBN) VALUES (?)");
            stmt.setInt(1, print.getISBN());
            if (stmt.executeUpdate() > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        print.setId(generatedKeys.getInt("Id"));
                        return print;
                    } else {
                        throw new SQLException("Creating print failed, no ID obtained.");
                    }
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PrintException(e.getMessage());
        }
    }

    /**
     * @param print
     * @return
     * @throws SQLException
     */
    @Override
    public Print Update(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE print SET ISBN = ?, Status = ?, Archived = ? WHERE Id = ?");
            stmt.setInt(1, print.getISBN());
            stmt.setString(2, print.getStatus());
            stmt.setBoolean(3, print.getArchived());
            stmt.setInt(4, print.getId());
            if (stmt.executeUpdate() > 0) return print;
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PrintException(e.getMessage());
        }
    }

    /**
     * @param print
     * @return
     * @throws SQLException
     */
    @Override
    public Print Delete(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM print WHERE Id = ?");
            stmt.setInt(1, print.getId());
            if (stmt.executeUpdate() > 0) return print;
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PrintException(e.getMessage());
        }
    }

    /**
     * @param print
     * @return Boolean
     * @throws SQLException
     */
    @Override
    public Boolean MakeAvailable(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE print SET Status = 'Available' WHERE Id = ?");
            stmt.setInt(1, print.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PrintException(e.getMessage());
        }
    }

    /**
     * @param print
     * @return Boolean
     * @throws SQLException
     */
    @Override
    public Boolean Archive(Print print) throws PrintException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE print SET Archived = true WHERE Id = ?");
            stmt.setInt(1, print.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PrintException(e.getMessage());
        }
    }

}
