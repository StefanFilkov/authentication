package com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameTakenException extends BaseException{
    public UsernameTakenException(String username) {
        super(String.format("Username: %s taken", username), HttpStatus.CONFLICT);
    }
}
