package main;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Service {
    public static Service instance;
    private Integer Id;
    private Date BorrowDate;
    private String  ReturnDate;
    private Integer UserId;
    private Integer ClientId;
    private Boolean Returned;
    private Integer PrintId;

    public Service(Date borrowDate, Integer userId, Integer clientId, Integer printId) {
        this.BorrowDate = borrowDate;
        // Add 15 days to the borrowing date to get return date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Using the current date
        c.add(Calendar.DATE, 15); // Adding 5 days
        this.ReturnDate = sdf.format(c.getTime());
        this.UserId = userId;
        this.ClientId = clientId;
        this.Returned = false;
        this.PrintId = printId;
    }

    public static Service getInstance(Date borrowDate, Integer userId, Integer clientId, Integer printId) {
        if (instance == null)
            instance = new Service(borrowDate, userId, clientId, printId);
        return instance;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public Date getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.BorrowDate = borrowDate;
    }


    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String  returnDate) {
        this.ReturnDate = returnDate;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        this.UserId = userId;
    }

    public Integer getClientId() {
        return ClientId;
    }

    public void setClientId(Integer clientId) {
        this.ClientId = clientId;
    }

    public Boolean getReturned() {
        return Returned;
    }

    public void setReturned(Boolean returned) {
        this.Returned = returned;
    }

    public Integer getPrintId() {
        return PrintId;
    }

    public void setPrintId(Integer printId) {
        this.PrintId = printId;
    }
}
