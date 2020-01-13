package com.blockchain.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.persistence.NoResultException;


@ControllerAdvice
public class ExceptionHandlerHelper extends ResponseEntityExceptionHandler {
    @ExceptionHandler({NoResultException.class,UserNotFoundException.class})
    protected ResponseEntity noResultFoundException(Exception ex,WebRequest request){
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ExceptionHandler(TransactionSystemException.class)
    protected ResponseEntity validationFailedException(Exception ex,WebRequest request) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }
}
