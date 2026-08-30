package sn.pressingsicap.Demo.Exception;

public class CategorieNotFoundException extends RuntimeException {
    public CategorieNotFoundException(String message){
        super(message);
    }
}
