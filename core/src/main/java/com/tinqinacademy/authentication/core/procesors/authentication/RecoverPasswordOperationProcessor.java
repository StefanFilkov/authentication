package com.tinqinacademy.authentication.core.procesors.authentication;

import com.tinqinacademy.authentication.api.errors.Errors;
import com.tinqinacademy.authentication.api.operations.recoveredpassword.RecoverPasswordInput;
import com.tinqinacademy.authentication.api.operations.recoveredpassword.RecoverPasswordOperation;
import com.tinqinacademy.authentication.api.operations.recoveredpassword.RecoverPasswordOutput;
import com.tinqinacademy.authentication.core.errorexceptionhandling.errormapper.ErrorMapper;
import com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions.InvalidLoginException;
import com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions.UserNotFoundException;
import com.tinqinacademy.authentication.core.procesors.BaseOperationProcessor;
import io.vavr.Predicates;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;

@Service
@Slf4j
public class RecoverPasswordOperationProcessor extends BaseOperationProcessor implements RecoverPasswordOperation {
    protected RecoverPasswordOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper) {
        super(conversionService, validator, errorMapper);
    }

    @Override
    public Either<Errors, RecoverPasswordOutput> process(RecoverPasswordInput input) {
        return validateInput(input).flatMap(validation -> recoverPassword(input));
    }

    private Either<Errors, ? extends RecoverPasswordOutput> recoverPassword(RecoverPasswordInput input) {
        return Try.of(()->{
                    log.info("Start Login operation with input: {}", input);

                    RecoverPasswordOutput result = RecoverPasswordOutput.builder().build();

                    log.info("End of Login operation with result: {}", result);
                    return result;
        }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(UserNotFoundException.class)), errorMapper::mapErrors),
                        Case($(Predicates.instanceOf(InvalidLoginException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)));
    }
}
