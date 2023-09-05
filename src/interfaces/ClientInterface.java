package interfaces;

import exception.ClientException;
import models.Client;

public interface ClientInterface {

    Client get(Client client) throws ClientException;
    Boolean Add(Client client) throws ClientException;
    Boolean Update(Client client) throws ClientException;
    Boolean Delete(Client client) throws ClientException;
    Integer GetId(Client client) throws ClientException;
}
