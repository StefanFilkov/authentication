package com.tinqinacademy.authentication.core.procesors.authentication;

import com.tinqinacademy.authentication.api.errors.Errors;
import com.tinqinacademy.authentication.api.operations.validation.ValidationInput;
import com.tinqinacademy.authentication.api.operations.validation.ValidationOperation;
import com.tinqinacademy.authentication.api.operations.validation.ValidationOutput;
import com.tinqinacademy.authentication.core.errorexceptionhandling.errormapper.ErrorMapper;
import com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions.UserNotFoundException;
import com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions.UsernameTakenException;
import com.tinqinacademy.authentication.core.procesors.BaseOperationProcessor;
import com.tinqinacademy.authentication.core.security.filter.JwtService;
import io.vavr.Predicates;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;

@Service
@Slf4j
public class ValidateTokenOperationProcessor extends BaseOperationProcessor implements ValidationOperation {
    private final JwtService jwtService;

    protected ValidateTokenOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, JwtService jwtService) {
        super(conversionService, validator, errorMapper);
        this.jwtService = jwtService;
    }

    @Override
    public Either<Errors, ValidationOutput> process(ValidationInput input) {
        return validateInput(input).flatMap(valid -> tokenValidation(input));
    }

    private Either<Errors, ValidationOutput> tokenValidation(ValidationInput input) {
        return Try.of(() -> {
                    log.info("Start of tokenValidation with input: {}", input);

                    ValidationOutput result = ValidationOutput
                            .builder()
                            .isTokenValid(jwtService.isTokenValid(input.getToken()))
                            .build();


                    log.info("End of tokenValidation with result: {}", result);
                    return result;
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));

    }
}
