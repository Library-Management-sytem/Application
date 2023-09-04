package interfaces;

import main.User;

import java.sql.SQLException;

public interface UserInterface {

    User get(User user) throws SQLException;
    User Add(User user) throws SQLException;
    User Update(User user) throws SQLException;
    User Delete(User user) throws SQLException;
    User Login(User user) throws SQLException;
    User Logout(User user) throws SQLException;
}
