package com.tinqinacademy.authentication.api.errors;


import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@SuperBuilder
public class OperationError {
    protected HttpStatus status;
    protected String message;

}
