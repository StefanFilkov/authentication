package com.tinqinacademy.authentication.core.converters;

import com.tinqinacademy.authentication.api.operations.registeruser.RegisterUserInput;
import com.tinqinacademy.authentication.persistence.Role;
import com.tinqinacademy.authentication.persistence.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserInputToUserBuilder implements Converter<RegisterUserInput, User.UserBuilder> {
    @Override
    public User.UserBuilder convert(RegisterUserInput source) {
        return User.builder()
                .email(source.getEmail())
                .password(source.getPassword())
                .name(source.getUsername())
                .role(Role.ROLE_USER);

    }
}
