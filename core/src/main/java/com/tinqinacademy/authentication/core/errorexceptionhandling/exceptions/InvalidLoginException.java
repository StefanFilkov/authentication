package com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidLoginException extends BaseException {
    public InvalidLoginException() {
        super("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }
}
