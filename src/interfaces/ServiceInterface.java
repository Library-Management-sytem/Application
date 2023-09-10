package interfaces;

import exception.ServiceException;
import models.Service;

import java.util.List;

public interface ServiceInterface {

    Boolean get(Service service) throws ServiceException;
    List<Service> get() throws ServiceException;
    Service Add(Service service) throws ServiceException;
    Service Update(Service service) throws ServiceException;
    Service Delete(Service service) throws ServiceException;
}
