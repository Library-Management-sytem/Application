package implementation;

import database.MySQL;
import interfaces.BookInterface;
import main.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookImplementation implements BookInterface {

    private final Connection con = MySQL.provideConnection();
//    Book instance = Book.getInstance();

    /**
     * @param book
     * @return
     * @throws SQLException
     */
    @Override
    public Book get(Book book) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM book WHERE ISBN = ?");
        stmt.setInt(1, book.getISBN());
        stmt.executeQuery();
        return book;
    }

    /**
     * @param book
     * @return
     * @throws SQLException
     */
    @Override
    public Book Add(Book book) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO book (ISBN, Name, Author, Year) VALUES (?, ?, ?, ?)");
        stmt.setInt(1, book.getISBN());
        stmt.setString(2, book.getName());
        stmt.setString(3, book.getAuthor());
        stmt.setInt(4, book.getYear());
        if (stmt.executeUpdate() > 0) return book;
        return null;
    }

    /**
     * @param book
     * @return
     * @throws SQLException
     */
    @Override
    public Book Update(Book book) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE book SET ISBN = ?, Name = ?, Author = ?, Year= ? WHERE ISBN = ?");
        stmt.setInt(1, book.getISBN());
        stmt.setString(2, book.getName());
        stmt.setString(3, book.getAuthor());
        stmt.setInt(4, book.getYear());
        stmt.setInt(5, book.getISBN());
        if (stmt.executeUpdate() > 0) return book;
        return null;
    }

    /**
     * @param book
     * @return
     * @throws SQLException
     */
    @Override
    public Book Delete(Book book) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM book WHERE ISBN = ?");
        stmt.setInt(1, book.getISBN());
        if (stmt.executeUpdate() > 0) return book;
        return null;
    }

}
