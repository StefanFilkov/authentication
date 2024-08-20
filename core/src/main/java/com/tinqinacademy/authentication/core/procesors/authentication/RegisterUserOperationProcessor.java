package com.tinqinacademy.authentication.core.procesors.authentication;

import com.tinqinacademy.authentication.api.errors.Errors;
import com.tinqinacademy.authentication.api.operations.registeruser.RegisterUserInput;
import com.tinqinacademy.authentication.api.operations.registeruser.RegisterUserOperation;
import com.tinqinacademy.authentication.api.operations.registeruser.RegisterUserOutput;
import com.tinqinacademy.authentication.core.errorexceptionhandling.errormapper.ErrorMapper;
import com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions.UserNotFoundException;
import com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions.UsernameTakenException;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;

@Slf4j
@Service
public class RegisterUserOperationProcessor extends BaseOperationProcessor implements RegisterUserOperation  {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender getJavaMailSender;


    protected RegisterUserOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, JavaMailSender getJavaMailSender) {
        super(conversionService, validator, errorMapper);
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.getJavaMailSender = getJavaMailSender;
    }

    @Override
    public Either<Errors, RegisterUserOutput> process(RegisterUserInput input) {
        return validateInput(input).flatMap(validated -> registerUser(input));
    }

    private Either<Errors, RegisterUserOutput> registerUser(RegisterUserInput input) {
            log.info("Start of Register with username and password: {}", input);

            return Try.of(() -> {
                        isUsernameTaken(input.getUsername());
                        User newUser = conversionService.convert(input, User.UserBuilder.class)
                                .password(passwordEncoder.encode(input.getPassword()))
                                .build();

                        userRepository.save(newUser);
                        String token = jwtService.generateToken(newUser);

                        RegisterUserOutput result = RegisterUserOutput.builder()
                                .build();

                        SimpleMailMessage simpleMessage = new SimpleMailMessage();
                        simpleMessage.setText("Verify your email: ");
                        simpleMessage.setTo(input.getEmail());
                        getJavaMailSender.send(simpleMessage);

                        log.info("End of Register with result: {}", result);
                        return result;

                    })
                    .toEither()
                    .mapLeft(throwable -> Match(throwable).of(
                            Case($(Predicates.instanceOf(UserNotFoundException.class)), errorMapper::mapErrors),
                            Case($(Predicates.instanceOf(UsernameTakenException.class)), errorMapper::mapErrors),
                            Case($(), errorMapper::mapErrors)
                    ));
        }

        private void isUsernameTaken(String username) {
            if(userRepository.findByName(username).isPresent()) {
                throw new UsernameTakenException(username);
            }
        }


}
