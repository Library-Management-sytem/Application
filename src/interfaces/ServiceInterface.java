package interfaces;

import exception.ServiceException;
import models.Service;

public interface ServiceInterface {

    Service get(Service service) throws ServiceException;
    Service Add(Service service) throws ServiceException;
    Service Update(Service service) throws ServiceException;
    Service Delete(Service service) throws ServiceException;
}
