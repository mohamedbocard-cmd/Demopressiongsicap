package sn.pressingsicap.Demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super (message);
    }
}