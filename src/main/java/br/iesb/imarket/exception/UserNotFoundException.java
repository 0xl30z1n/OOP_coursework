package br.iesb.imarket.exception;

public class UserNotFoundException extends Throwable{
    public UserNotFoundException(String str){
        super(str);
    }
}
