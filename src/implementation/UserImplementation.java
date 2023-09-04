package implementation;

import database.MySQL;
import interfaces.UserInterface;
import main.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserImplementation implements UserInterface {
    public StringBuilder resultString;
    private Connection con = MySQL.provideConnection();

    public UserImplementation() {
        try {
            this.resultString = new StringBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param user 
     * @return
     * @throws SQLException
     */
    @Override
    public User get(User user) throws SQLException {
        return null;
    }

    /**
     * @param user 
     * @return
     * @throws SQLException
     */
    @Override
    public User Add(User user) throws SQLException {
        return null;
    }

    /**
     * @param user 
     * @return
     * @throws SQLException
     */
    @Override
    public User Update(User user) throws SQLException {
        return null;
    }

    /**
     * @param user 
     * @return
     * @throws SQLException
     */
    @Override
    public User Delete(User user) throws SQLException {
        return null;
    }

    /**
     * @param user 
     * @return
     * @throws SQLException
     */
    @Override
    public User Login(User user) throws SQLException {
        return null;
    }

    /**
     * @param user 
     * @return
     * @throws SQLException
     */
    @Override
    public User Logout(User user) throws SQLException {
        return null;
    }

//    public Boolean login(String email, String password) throws SQLException {
//        PreparedStatement stmt = con.prepareStatement("SELECT Email, Password FROM user WHERE Email LIKE  ? AND Password LIKE ?");
//        stmt.setString(1, email);
//        stmt.setString(2, password);
//        ResultSet result = stmt.executeQuery();
//        while (result.next()) {
//            String dbemail = result.getString("Email");
//            if (dbemail != null)
//                return true;
//        }
//        return false;
//    }
//
//    public String getUsers() throws SQLException {
//        ResultSet result = con.createStatement().executeQuery("SELECT * FROM user");
//        while (result.next()) {
//            int id = result.getInt("Id");
//            String name = result.getString("Name");
//            String email = result.getString("Email");
//            this.resultString.append("ID: ").append(id).append("\tName: ").append(name).append("\tEmail: ").append(email).append("\n");
//        }
//        return resultString.toString();
//    }
}
