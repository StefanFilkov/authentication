package com.tinqinacademy.authentication.api.operations.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.authentication.api.base.OperationOutput;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogInOutput implements OperationOutput {
    @Hidden
    @JsonIgnore
    private String token;
}
