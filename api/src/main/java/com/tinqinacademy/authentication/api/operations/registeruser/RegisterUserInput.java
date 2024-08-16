package com.tinqinacademy.authentication.api.operations.registeruser;

import com.tinqinacademy.authentication.api.base.OperationInput;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterUserInput implements OperationInput {
    private String username;
    private String password;
    private String email;
}
