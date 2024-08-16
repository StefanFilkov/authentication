package com.tinqinacademy.authentication.rest.controllers;


import com.tinqinacademy.authentication.api.operations.login.LogInInput;
import com.tinqinacademy.authentication.api.operations.login.LogInOperation;
import com.tinqinacademy.authentication.api.operations.registeruser.RegisterUserInput;
import com.tinqinacademy.authentication.api.operations.registeruser.RegisterUserOperation;

import com.tinqinacademy.authentication.api.urls.URLMappings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController extends BaseController {
    private final RegisterUserOperation registerUserOperation;
    private final LogInOperation logInOperation;


    public AuthenticationController(RegisterUserOperation registerUserOperation, LogInOperation logInOperation) {
        this.registerUserOperation = registerUserOperation;
        this.logInOperation = logInOperation;

    }


    @PostMapping(URLMappings.REGISTER)
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserInput input) {
        return handleResult(registerUserOperation.process(input));
    }

    @PostMapping(URLMappings.LOGIN)
        public ResponseEntity<?> login(@RequestBody LogInInput input) {
        return handleResult(logInOperation.process(input));
    }


}
