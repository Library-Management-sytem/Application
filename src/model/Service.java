package model;

import utility.DBUtility;

import java.sql.Connection;

public class Service {

    private Connection stmt;
    public Service(){
        DBUtility db = new DBUtility();
        this.stmt = db.provideConnection();
    }

    public Boolean returnBook(){
        return true;
    }

    public Boolean loan(){
        return true;
    }
}
