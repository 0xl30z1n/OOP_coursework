package br.iesb.imarket.exception;

public class UserUnauthorizedException extends Throwable{
    public UserUnauthorizedException(String str){
        super(str);
    }
}
