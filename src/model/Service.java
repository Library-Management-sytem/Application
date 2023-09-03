package model;

import utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {
    private Connection con;
    public Service(){
        DBUtility db = new DBUtility();
        this.con = db.provideConnection();
    }
//    public Boolean returnBook(String clientName, String email, ){
//
//
//    }

//    public Boolean checkClient(String email) throws SQLException{
//        PreparedStatement stmt = con.prepareStatement("SELECT Email FROM client WHERE Email = ?");
//        stmt.setString(1, email);
//        ResultSet result = stmt.executeQuery();
//        String resutlEmail = result.getString("Email");
//        while(resutlEmail.)
//    }
    public Boolean loan(){
        return true;
    }
}
