package com.tinqinacademy.authentication.core.errorexceptionhandling.errormapper;


import com.tinqinacademy.authentication.api.errors.Errors;

public interface ErrorMapper {
     Errors mapErrors(Throwable throwable);
}
