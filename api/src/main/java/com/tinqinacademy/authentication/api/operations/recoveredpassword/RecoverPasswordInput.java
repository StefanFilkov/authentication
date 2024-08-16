package com.tinqinacademy.authentication.api.operations.recoveredpassword;

import com.tinqinacademy.authentication.api.base.OperationInput;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RecoverPasswordInput implements OperationInput {
    private String email;
}
