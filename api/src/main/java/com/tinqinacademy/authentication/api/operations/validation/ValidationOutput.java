package com.tinqinacademy.authentication.api.operations.validation;

import com.tinqinacademy.authentication.api.base.OperationOutput;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ValidationOutput implements OperationOutput {
    private Boolean isTokenValid;
}
