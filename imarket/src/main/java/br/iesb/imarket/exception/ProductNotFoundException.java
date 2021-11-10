package br.iesb.imarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProductNotFoundException extends Throwable{
    public ProductNotFoundException(Long id) {
        super("Product not found with ID " + id);
    }
    public ProductNotFoundException(String str){
        super("Product not found with "+ str);
    }
}