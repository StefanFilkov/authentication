package com.tinqinacademy.authentication.core.procesors.authentication;

import com.tinqinacademy.authentication.api.errors.Errors;
import com.tinqinacademy.authentication.api.operations.login.LogInInput;
import com.tinqinacademy.authentication.api.operations.login.LogInOperation;
import com.tinqinacademy.authentication.api.operations.login.LogInOutput;
import com.tinqinacademy.authentication.core.errorexceptionhandling.errormapper.ErrorMapper;
import com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions.InvalidLoginException;
import com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions.UserNotFoundException;
import com.tinqinacademy.authentication.core.procesors.BaseOperationProcessor;
import com.tinqinacademy.authentication.core.security.filter.JwtService;
import com.tinqinacademy.authentication.persistence.entities.User;
import com.tinqinacademy.authentication.persistence.repository.UserRepository;
import io.vavr.Predicates;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;


@Service
@Slf4j
public class LogInOperationProcessor extends BaseOperationProcessor implements LogInOperation {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    protected LogInOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        super(conversionService, validator, errorMapper);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public Either<Errors, LogInOutput> process(LogInInput input) {
        return validateInput(input).flatMap(validated -> logIn(input));
    }

    public Either<Errors, LogInOutput> logIn(LogInInput input) {
        return Try.of(() -> {
                    log.info("Start Login operation with input: {}", input);

                    User user = userRepository.findByName(input.getUsername())
                            .orElseThrow(() -> new UserNotFoundException(input.getUsername()));

                    if(!passwordEncoder.matches(input.getPassword(), user.getPassword())){
                        throw new InvalidLoginException();
                    }

                    String userToken = jwtService.generateToken(user);

                    LogInOutput result = LogInOutput.builder()
                            .token(userToken)
                            .build();

                    log.info("End of Login operation with result: {}", result);
                    return result;
                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(UserNotFoundException.class)), errorMapper::mapErrors),
                        Case($(Predicates.instanceOf(InvalidLoginException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
