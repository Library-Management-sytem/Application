package interfaces;

import exception.BookException;
import models.Book;

import java.util.List;

public interface BookInterface {

    Boolean get(Book book) throws BookException;
    List<Book> get() throws BookException;
    Boolean Add(Book book) throws BookException;
    Boolean Update(Book book) throws BookException;
    Boolean Delete(Book book) throws BookException;
}
