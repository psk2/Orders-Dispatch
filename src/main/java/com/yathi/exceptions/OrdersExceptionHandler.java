package com.yathi.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
@ControllerAdvice
@RestController
public class OrdersExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status", String.valueOf(400));
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false), 500);
        return new ResponseEntity(errorDetails,responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status", String.valueOf(400));
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false),400);
        return new ResponseEntity(errorDetails,responseHeaders,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderAlreadyDispatchedException.class)
    public final ResponseEntity<ErrorDetails> handleOrderAlreadyDispatchedException(OrderAlreadyDispatchedException ex, WebRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status", String.valueOf(400));
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false),400);
        return new ResponseEntity(errorDetails,responseHeaders,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidBrickTypeException.class)
    public final ResponseEntity<ErrorDetails> handleInvalidBrickTypeException(InvalidBrickTypeException ex, WebRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status", String.valueOf(400));
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false),400);
        return new ResponseEntity(errorDetails,responseHeaders,HttpStatus.NOT_FOUND);
    }
}
