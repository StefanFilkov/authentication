package com.tinqinacademy.authentication.rest.controllers;


import com.tinqinacademy.authentication.api.errors.Errors;
import com.tinqinacademy.authentication.api.operations.login.LogInInput;
import com.tinqinacademy.authentication.api.operations.login.LogInOperation;
import com.tinqinacademy.authentication.api.operations.login.LogInOutput;
import com.tinqinacademy.authentication.api.operations.registeruser.RegisterUserInput;
import com.tinqinacademy.authentication.api.operations.registeruser.RegisterUserOperation;

import com.tinqinacademy.authentication.api.operations.validation.ValidationInput;
import com.tinqinacademy.authentication.api.urls.URLMappings;
import com.tinqinacademy.authentication.core.procesors.authentication.ValidateTokenOperationProcessor;
import io.vavr.control.Either;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController extends BaseController {
    private final RegisterUserOperation registerUserOperation;
    private final LogInOperation logInOperation;
    private final ValidateTokenOperationProcessor validateTokenOperationProcessor;


    public AuthenticationController(RegisterUserOperation registerUserOperation, LogInOperation logInOperation, ValidateTokenOperationProcessor validateTokenOperationProcessor) {
        this.registerUserOperation = registerUserOperation;
        this.logInOperation = logInOperation;
        this.validateTokenOperationProcessor = validateTokenOperationProcessor;
    }

    @PostMapping(URLMappings.VALIDATE_TOKEN)
    public ResponseEntity<?> validateToken(@RequestBody ValidationInput input) {
        return handleResult(validateTokenOperationProcessor.process(input));
    }

    @PostMapping(URLMappings.REGISTER)
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserInput input, HttpServletResponse response) {

        return handleResult(registerUserOperation.process(input));
    }

    @PostMapping(URLMappings.LOGIN)
        public ResponseEntity<?> login(@RequestBody LogInInput input, HttpServletResponse response) {
        Either<Errors, LogInOutput> result = logInOperation.process(input);
        if (result.isRight()){
            response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + result.get().getToken());
        }
        return handleResult(result);
    }


}
