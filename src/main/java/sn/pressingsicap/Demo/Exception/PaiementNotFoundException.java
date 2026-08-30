package sn.pressingsicap.Demo.Exception;

import sn.pressingsicap.Demo.Service.PaiementService;

public class PaiementNotFoundException extends RuntimeException{
    public PaiementNotFoundException(String message){
        super(message);
    }
}
