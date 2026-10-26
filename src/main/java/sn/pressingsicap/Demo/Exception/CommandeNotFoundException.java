package sn.pressingsicap.Demo.Exception;

import lombok.EqualsAndHashCode;

public class CommandeNotFoundException extends RuntimeException{
    public CommandeNotFoundException(String message){
        super(message);
    }
}
