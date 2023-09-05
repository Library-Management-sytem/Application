package interfaces;

import exception.PrintException;
import models.Print;

public interface PrintInterface {

    Print get(Print print) throws PrintException;
    Integer getId(Print print) throws PrintException;
    Print Add(Print print) throws PrintException;
    Print Update(Print print) throws PrintException;
    Print Delete(Print print) throws PrintException;
    Boolean MakeAvailable(Print print) throws PrintException;
    Boolean Archive(Print print) throws PrintException;
}
