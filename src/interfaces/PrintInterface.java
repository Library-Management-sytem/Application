package interfaces;

import exception.PrintException;
import models.Print;

import java.util.List;

public interface PrintInterface {

    Boolean get(Print print) throws PrintException;
    List<Print> get() throws PrintException;
    Boolean Add(Print print) throws PrintException;
    Boolean Update(Print print) throws PrintException;
    Boolean Delete(Print print) throws PrintException;
    Boolean MakeAvailable(Print print) throws PrintException;
    Boolean Archive(Print print) throws PrintException;
    Boolean MakeLoaned(Print print) throws PrintException;
}
