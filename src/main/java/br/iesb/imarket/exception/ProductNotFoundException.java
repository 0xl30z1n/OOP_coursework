package br.iesb.imarket.exception;

public class ProductNotFoundException extends Throwable{
    public ProductNotFoundException(Long id) {
        super("Product not found with ID " + id);
    }
    public ProductNotFoundException(String str){
        super(str);
    }
}