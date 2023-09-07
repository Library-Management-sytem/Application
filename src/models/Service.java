package models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Service {
    public static Service instance;
    private String BorrowDate;
    private String ReturnDate;
    private Boolean Returned;
    private Client client;
    private Print print;
    private List<Service> services;

    public Service(String borrowDate, Client client, Print print) {
        this.BorrowDate = borrowDate;
        this.client = client;
        this.print = print;

    }

    public String getBorrowDate() {return BorrowDate;}

    public void setBorrowDate(String borrowDate) {this.BorrowDate = borrowDate;}

    public String getReturnDate() {return ReturnDate;}

    public void setReturnDate(String returnDate) {this.ReturnDate = returnDate;}

    public Boolean getReturned() {return Returned;}

    public void setReturned(Boolean returned) {this.Returned = returned;}

    public List<Service> getServices() {return services;}

    public void setServices(List<Service> services) {this.services = services;}

    public Client getClient() {return client;}

    public void setClient(Client client) {this.client = client;}

    public Print getPrint() {return print;}

    public void setPrint(Print print) {this.print = print;}
}
