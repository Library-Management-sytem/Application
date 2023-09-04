package model;

import utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {
    private final Connection con;
    public Book(){
        DBUtility db = new DBUtility();
        this.con = db.provideConnection();
    }
    public ResultSet get(Integer isbn) throws SQLException{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM book WHERE ISBN = ?");
            stmt.setInt(1, isbn);
            return stmt.executeQuery();
    }
    public Boolean add(Integer isbn, String name, String author, Integer year) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO book (ISBN, Name, Author, Year) VALUES (?, ?, ?, ?)");
        stmt.setInt(1, isbn);
        stmt.setString(2, name);
        stmt.setString(3, author);
        stmt.setInt(4, year);
        return stmt.executeUpdate() > 0;
    }

    public Boolean update(Integer ISBN, Integer isbn, String name, String author, Integer year) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE book SET ISBN = ?, Name = ?, Author = ?, Year= ? WHERE ISBN = ?");
        stmt.setInt(1, isbn);
        stmt.setString(2, name);
        stmt.setString(3, author);
        stmt.setInt(4, year);
        stmt.setInt(5, ISBN);
        return stmt.executeUpdate() > 0;
    }

    public Boolean delete(Integer isbn) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM book WHERE ISBN = ?");
        stmt.setInt(1, isbn);
        return stmt.executeUpdate() > 0;
    }

    public Boolean archive(Integer isbn) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("UPDATE book SET Archived = true WHERE ISBN = ?");
        stmt.setInt(1, isbn);
        return stmt.executeUpdate() > 0;
    }

    public static boolean makeAvailable(Integer id) throws SQLException {
        DBUtility db = new DBUtility();
        Connection con = db.provideConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE book SET Status = 'Available' WHERE ISBN = " + id);
        return stmt.executeUpdate() > 0;
    }
}
