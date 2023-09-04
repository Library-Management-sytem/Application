package main;

public class Book {
    private static Book instance;
    private Integer ISBN;
    private String Name;
    private String Author;
    private Integer Year;

    private Book(){

    }
    private Book(Integer isbn, String name, String author, Integer year) {
        this.ISBN = isbn;
        this.Name = name;
        this.Author = author;
        this.Year = year;
    }

    public static Book getInstance(Integer isbn, String name, String author, Integer year) {
        if (instance == null)
            instance = new Book(isbn, name, author, year);
        return instance;
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
