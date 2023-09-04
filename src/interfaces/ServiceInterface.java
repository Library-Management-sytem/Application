package interfaces;

import main.Print;
import main.Service;

import java.sql.SQLException;

public interface ServiceInterface {

    Service get(Service service) throws SQLException;
    Service Add(Service service) throws SQLException;
    Service Update(Service service) throws SQLException;
    Service Delete(Service service) throws SQLException;
}
