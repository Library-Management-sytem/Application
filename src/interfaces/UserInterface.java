package interfaces;

import exception.UserException;
import models.User;

import java.util.List;

public interface UserInterface {

    Boolean get(User user) throws UserException;
    List<User> get() throws UserException;
    User Add(User user) throws UserException;
    User Update(User user) throws UserException;
    User Delete(User user) throws UserException;
    User Login(User user) throws UserException;
    User Logout(User user) throws UserException;
}
