package br.iesb.imarket.controller;

import br.iesb.imarket.exception.ProductBadRequestException;
import br.iesb.imarket.exception.ProductNotFoundException;
import br.iesb.imarket.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandlerController {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StandardError> productNotFound(ProductNotFoundException e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(ProductBadRequestException.class)
    public ResponseEntity<StandardError> productBadRequest(ProductBadRequestException e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Resource bad request");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
