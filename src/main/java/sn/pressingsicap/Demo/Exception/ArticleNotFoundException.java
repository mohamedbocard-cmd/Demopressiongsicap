package sn.pressingsicap.Demo.Exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException(String message){
       super (message);
    }
}
