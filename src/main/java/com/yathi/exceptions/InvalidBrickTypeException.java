package com.yathi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidBrickTypeException extends RuntimeException {
    public InvalidBrickTypeException(String exception) {
        super(exception);
    }
}
