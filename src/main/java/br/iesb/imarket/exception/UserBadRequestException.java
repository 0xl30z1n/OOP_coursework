package br.iesb.imarket.exception;

public class UserBadRequestException extends Throwable{
    public UserBadRequestException(String str){
        super(str);
    }
}
