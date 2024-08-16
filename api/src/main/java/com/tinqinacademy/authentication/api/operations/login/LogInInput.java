package com.tinqinacademy.authentication.api.operations.login;

import com.tinqinacademy.authentication.api.base.OperationInput;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogInInput implements OperationInput {
    private String username;
    private String password;
}
