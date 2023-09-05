package interfaces;

import exception.UserException;
import models.User;

public interface UserInterface {

    User get(User user) throws UserException;
    User Add(User user) throws UserException;
    User Update(User user) throws UserException;
    User Delete(User user) throws UserException;
    User Login(User user) throws UserException;
    User Logout(User user) throws UserException;
}
