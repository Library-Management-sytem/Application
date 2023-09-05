package interfaces;

import exception.BookException;
import models.Book;

public interface BookInterface {

    Book get(Book book) throws BookException;
    Boolean Add(Book book) throws BookException;
    Boolean Update(Book book) throws BookException;
    Boolean Delete(Book book) throws BookException;
}
