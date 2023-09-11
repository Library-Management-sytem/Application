package interfaces;

import exception.ClientException;
import models.Client;

import java.util.List;

public interface ClientInterface {

    Boolean get(Client client) throws ClientException;
    List<Client> get() throws ClientException;
    Boolean Add(Client client) throws ClientException;
    Boolean Update(Client client) throws ClientException;
    Boolean Delete(Client client) throws ClientException;
}
