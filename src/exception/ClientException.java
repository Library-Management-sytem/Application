package exception;

public class ClientException extends Exception {
    public ClientException(){

    }
    public ClientException(String message){
        super(message);
    }
}
