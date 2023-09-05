package models;

import java.util.List;

public class Book {
    private Integer ISBN;
    private String Name;
    private String Author;
    private Integer Year;
    private List<Book> books;

    public Book(){

    }
    public Book(Integer isbn, String name, String author, Integer year) {
        this.ISBN = isbn;
        this.Name = name;
        this.Author = author;
        this.Year = year;
    }

    public Integer getISBN(){
        return ISBN;
    }
    public String getName(){
        return Name;
    }
    public String getAuthor(){
        return Author;
    }
    public Integer getYear(){
        return Year;
    }

    public void setISBN(Integer isbn){
        this.ISBN = isbn;
    }
    public void setName(String name){
        this.Name = name;
    }
    public void setAuthor(String author){
        this.Author = author;
    }
    public void setYear(Integer year){
        this.Year = year;
    }


}
