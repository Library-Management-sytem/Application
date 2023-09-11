package models;

import java.util.List;

public class Print {
    public static Print instance;
    private Integer Id;
    private String Status;
    private Boolean Archived;
    private List<Print> prints;
    private Book book;
    private Service service;
//    private Integer Prints_Available;
    public Print(){
        this.book = new Book();
    }
    public Print(Book book, Service service) {
        this.book = book;
        this.service = service;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public Boolean getArchived() {
        return Archived;
    }

    public void setArchived(Boolean archived) {
        this.Archived = archived;
    }
    public List<Print> getPrints(){ return this.prints;}
    public void setPrints(List<Print> prints){this.prints = prints;}
    public Book getBook(){ return book;}
    public void setBook(Book book){ this.book = book;}
    public Service getService(){ return service;}
    public void setService(Service service){ this.service = service;}

}
