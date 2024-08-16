package com.tinqinacademy.authentication.api.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Service
public class Errors implements ErrorsWrapper {

    @Builder.Default
    private List<OperationError> errors = new ArrayList<>();

    private HttpStatus status;

    @Override
    public void addError(OperationError error) {
        errors.add(error);
    }

    @Override
    public List<OperationError> getErrors() {
        return errors;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }


}
