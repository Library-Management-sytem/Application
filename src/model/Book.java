package model;

import utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Book {
    private final Connection con;
    public Book(){
        DBUtility db = new DBUtility();
        this.con = db.provideConnection();
    }
    public Boolean add(Integer isbn, String name, String author, Integer year) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO book (ISBN, Name, Author, Year) VALUES (?, ?, ?, ?)");
        stmt.setInt(1, isbn);
        stmt.setString(2, name);
        stmt.setString(3, author);
        stmt.setInt(4, year);
        return stmt.execute();
    }

    public Boolean update(Integer ISBN, Integer isbn, String name, String author, Integer year) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE book SET ISBN = ?, Name = ?, Author = ?, Year= ? WHERE ISBN = ?");
        stmt.setInt(1, isbn);
        stmt.setString(2, name);
        stmt.setString(3, author);
        stmt.setInt(4, year);
        stmt.setInt(5, ISBN);
        return stmt.execute();
    }

    public Boolean delete(Integer isbn) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM book WHERE ISBN = ?");
        stmt.setInt(1, isbn);
        return stmt.execute();
    }

    public Boolean archive(Integer isbn) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("UPDATE book SET Archived = true WHERE ISBN = ?");
        stmt.setInt(1, isbn);
        return stmt.execute();
    }
}
