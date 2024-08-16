package com.tinqinacademy.authentication.rest.controllers;

import com.tinqinacademy.authentication.api.base.OperationOutput;
import com.tinqinacademy.authentication.api.errors.Errors;
import com.tinqinacademy.authentication.api.operations.login.LogInOutput;
import io.vavr.control.Either;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public abstract class BaseController {
    protected ResponseEntity<?> handleResult(Either<Errors, ? extends OperationOutput> result) {
        return result.isLeft()
                ? new ResponseEntity<>(result.getLeft(), result.getLeft().getStatus())
                : new ResponseEntity<>(result.get(), HttpStatus.OK);

    }

    protected ResponseEntity<?> handleLogIn(Either<Errors, LogInOutput> result) {
        if (result.isLeft()){
            return new ResponseEntity<>(result.getLeft(), result.getLeft().getStatus());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", result.get().getToken()));


        return new ResponseEntity<>(headers, HttpStatus.OK);

    }
}
