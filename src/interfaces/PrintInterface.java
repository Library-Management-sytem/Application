package interfaces;

import exception.PrintException;
import models.Print;

import java.util.List;

public interface PrintInterface {

    Boolean get(Print print) throws PrintException;
    List<Print> get() throws PrintException;
    Print Add(Print print) throws PrintException;
    Print Update(Print print) throws PrintException;
    Print Delete(Print print) throws PrintException;
    Boolean MakeAvailable(Print print) throws PrintException;
    Boolean Archive(Print print) throws PrintException;
}
