package com.yathi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderAlreadyDispatchedException extends RuntimeException {
    public OrderAlreadyDispatchedException(String exception) {
        super(exception);
    }
}
