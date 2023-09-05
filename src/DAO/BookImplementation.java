package DAO;

import database.MySQL;
import exception.BookException;
import interfaces.BookInterface;
import models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookImplementation implements BookInterface {

    private final Connection con = MySQL.getInstance();

    /**
     * @param book
     * @return
     * @throws SQLException
     */
    @Override
    public Book get(Book book) throws BookException {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM book WHERE ISBN = ?");
            stmt.setInt(1, book.getISBN());
            stmt.executeQuery();
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BookException(e.getMessage());
        }
    }

    /**
     * @param book
     * @return
     */
    @Override
    public Boolean Add(Book book) throws BookException {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO book (ISBN, Name, Author, Year) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, book.getISBN());
            stmt.setString(2, book.getName());
            stmt.setString(3, book.getAuthor());
            stmt.setInt(4, book.getYear());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BookException(e.getMessage());
        }
    }

    /**
     * @param book
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean Update(Book book) throws BookException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE book SET ISBN = ?, Name = ?, Author = ?, Year= ? WHERE ISBN = ?");
            stmt.setInt(1, book.getISBN());
            stmt.setString(2, book.getName());
            stmt.setString(3, book.getAuthor());
            stmt.setInt(4, book.getYear());
            stmt.setInt(5, book.getISBN());
            return  (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BookException(e.getMessage());
        }
    }

    /**
     * @param book
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean Delete(Book book) throws BookException {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM book WHERE ISBN = ?");
            stmt.setInt(1, book.getISBN());
            return  (stmt.executeUpdate() > 0) ;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BookException(e.getMessage());
        }
    }

}
