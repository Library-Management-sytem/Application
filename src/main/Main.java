package main;

import model.User;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        // Press Ctrl+. with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        model.User user = new model.User();
        System.out.println(user + "Sidati");
//        DefaultTableModel users = user.getUsers();
//        for (ResultSetMetaData users: user)
        System.out.println(user.getUsers());
    }
}