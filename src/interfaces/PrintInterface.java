package interfaces;

import main.Client;
import main.Print;

import java.sql.SQLException;

public interface PrintInterface {

    Print get(Print print) throws SQLException;
    Print Add(Print print) throws SQLException;
    Print Update(Print print) throws SQLException;
    Print Delete(Print print) throws SQLException;
}
