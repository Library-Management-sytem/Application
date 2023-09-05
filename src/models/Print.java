package models;

import java.util.List;

public class Print {
    public static Print instance;
    private Integer Id;
    private Integer ISBN;
    private String Status;
    private Boolean Archived;
    private List<Print> prints;

    public Print(Integer ISBN) {
        this.ISBN = ISBN;
        this.Status = "Available";
        this.Archived = false;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
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
}
