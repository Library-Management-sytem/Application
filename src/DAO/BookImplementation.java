package DAO;

import database.Datasource;
import database.MySQL;
import exception.BookException;
import interfaces.BookInterface;
import models.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookImplementation implements BookInterface {

    private final Connection con = MySQL.getInstance();

    @Override
    public Boolean get(Book book) throws BookException {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM book WHERE ISBN = ?");
            stmt.setInt(1, book.getISBN());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                book.setISBN(result.getInt("ISBN"));
                book.setName(result.getString("Name"));
                book.setAuthor(result.getString("Author"));
                book.setYear(result.getInt("Year"));
                return true;
            }else book.setISBN(null);
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new BookException(e.getMessage());
        }
    }
    public List<Book> search(String input){
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT b.ISBN, b.Name, COUNT(b.ISBN) as Prints_Available, b.Author, b.Year FROM print INNER JOIN  book b ON print.ISBN = b.ISBN WHERE b.Name LIKE ? OR b.Author LIKE ? GROUP BY b.ISBN");
            stmt.setString(1, "%"+input+"%");
            stmt.setString(2, "%"+input+"%");
            ResultSet resultSet = stmt.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setISBN(resultSet.getInt("ISBN"));
                book.setName(resultSet.getString("Name"));
                book.setAuthor(resultSet.getString("Author"));
                book.setYear(resultSet.getInt("Year"));
                book.setPrints_Available(resultSet.getInt("Prints_Available"));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> get() throws BookException {
        try {
            QueryRunner run = new QueryRunner(Datasource.getMySQLDataSource());
            ResultSetHandler<List<Book>> h = new BeanListHandler<>(Book.class);
            return run.query("SELECT b.ISBN, b.Name, COUNT(b.ISBN) as Prints_Available, b.Author, b.Year FROM print INNER JOIN  book b ON print.ISBN = b.ISBN WHERE Status = 'Available' GROUP BY b.ISBN", h);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new BookException(e.getMessage());
        }
    }

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
            System.out.println(e.getMessage());
            throw new BookException(e.getMessage());
        }
    }


    @Override
    public Boolean Update(Book book) throws BookException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE book SET ISBN = ?, Name = ?, Author = ?, Year= ? WHERE ISBN = ?");
            stmt.setInt(1, book.getISBN());
            stmt.setString(2, book.getName());
            stmt.setString(3, book.getAuthor());
            stmt.setInt(4, book.getYear());
            if (book.getTempISBN() == null) stmt.setInt(5, book.getISBN());
            else stmt.setInt(5, book.getTempISBN());
            return (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new BookException(e.getMessage());
        }
    }

    @Override
    public Boolean Delete(Book book) throws BookException {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM book WHERE ISBN = ?");
            stmt.setInt(1, book.getISBN());
            return (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new BookException(e.getMessage());
        }
    }

}
