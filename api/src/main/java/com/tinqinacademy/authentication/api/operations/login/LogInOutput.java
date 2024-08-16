package com.tinqinacademy.authentication.api.operations.login;

import com.tinqinacademy.authentication.api.base.OperationOutput;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogInOutput implements OperationOutput {
    private String token;
}
