package implementation;

import database.MySQL;

import java.sql.Connection;

public class PrintImplementation {
    public final Connection con = MySQL.provideConnection();
    public PrintImplementation(){
    }

//    public Boolean add(Integer ISBN, Integer Number) throws SQLException {
//        PreparedStatement stmt = con.prepareStatement("INSERT INTO print (ISBN) VALUES (?)");
//        stmt.setInt(1, ISBN);
//        for (int i = 0; i < Number; i++){
//            if (stmt.executeUpdate() == 0)
//                return false;
//        }
//        return true;
//    }
//
//    public static boolean makeAvailable(Integer id) throws SQLException {
//        Connection con = MySQL.provideConnection();
//        PreparedStatement stmt = con.prepareStatement("UPDATE book SET Status = 'Available' WHERE ISBN = " + id);
//        return stmt.executeUpdate() > 0;
//    }
//
//    public Boolean archive(Integer isbn) throws SQLException{
//        System.out.println(con);
//        PreparedStatement stmt = con.prepareStatement("UPDATE book SET Archived = true WHERE ISBN = ?");
//        stmt.setInt(1, isbn);
//        return stmt.executeUpdate() > 0;
//    }
//
//    public Boolean delete(Integer id) throws SQLException {
//        PreparedStatement stmt = con.prepareStatement("DELETE FROM print WHERE Id = ?");
//        stmt.setInt(1, id);
//        return stmt.executeUpdate() > 0;
//    }
}
