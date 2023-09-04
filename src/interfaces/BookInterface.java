package interfaces;

import implementation.BookImplementation;
import main.Book;

import java.sql.SQLException;

public interface BookInterface {

    Book get(Book book) throws SQLException;
    Book Add(Book book) throws SQLException;
    Book Update(Book book) throws SQLException;
    Book Delete(Book book) throws SQLException;
}
