package model;

import utility.DBUtility;

import java.sql.Connection;
public class Book {
    private Connection stmt;
    public Book(){
        DBUtility db = new DBUtility();
        this.stmt = db.provideConnection();
    }
    public Boolean add(String name, String author, Integer year){
        return true;
    }

    public Boolean update(String name, String author, Integer year){
        return true;
    }

    public Boolean delete(String isbn){
        return true;
    }

    public Boolean archive(){
        return true;
    }
}
