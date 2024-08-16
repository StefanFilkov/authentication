package com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException{

    private HttpStatus code;

    public HttpStatus getCode() {
        return code;
    }

    public BaseException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
