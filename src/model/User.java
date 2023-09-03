package model;

import utility.DBUtility;

import java.sql.*;

public class User {
    private Connection con;
    public StringBuilder resultString;

    public User() {
        try {
        utility.DBUtility db = new DBUtility();
        this.con = db.provideConnection();
        this.resultString = new StringBuilder();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Boolean login(String email, String password) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT Email, Password FROM user WHERE Email LIKE  ? AND Password LIKE ?");
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet result = stmt.executeQuery();
        return result.getString("Email").isBlank();
    }

    public String getUsers() throws SQLException {
        ResultSet result = con.createStatement().executeQuery("SELECT * FROM user");
        while (result.next()) {
            int id = result.getInt("Id");
            String name = result.getString("Name");
            String email = result.getString("Email");
            this.resultString.append("ID: ").append(id).append("\tName: ").append(name).append("\tEmail: ").append(email).append("\n");
        }
        return resultString.toString();
    }
}
