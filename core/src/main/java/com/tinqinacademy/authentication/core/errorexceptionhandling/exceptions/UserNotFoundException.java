package com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException{
    public UserNotFoundException(String name) {
        super(String.format("User with name or id: %s was not found", name), HttpStatus.NOT_FOUND);
    }
}
