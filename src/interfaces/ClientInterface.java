package interfaces;

import main.Client;

import java.sql.SQLException;

public interface ClientInterface {

    Client get(Client client) throws SQLException;
    Client Add(Client client) throws SQLException;
    Client Update(Client client) throws SQLException;
    Client Delete(Client client) throws SQLException;
}
