package com.tinqinacademy.authentication.api.operations.validation;

import com.tinqinacademy.authentication.api.base.OperationInput;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ValidationInput implements OperationInput {
    private String token;
}
