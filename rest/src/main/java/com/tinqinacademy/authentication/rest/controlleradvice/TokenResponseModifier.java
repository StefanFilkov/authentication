package com.tinqinacademy.authentication.rest.controlleradvice;

import com.tinqinacademy.authentication.api.operations.login.LogInOutput;
import com.tinqinacademy.authentication.api.operations.registeruser.RegisterUserOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Component
@RestControllerAdvice
@Slf4j
public class TokenResponseModifier implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        if ("registerUser".equals(returnType.getMethod().getName()) ||
                "login".equals(returnType.getMethod().getName())) {

            log.info(String.format("RestController advice for %s", returnType.getDeclaringClass().getName()));
        }
        return "registerUser".equals(returnType.getMethod().getName()) ||
                "login".equals(returnType.getMethod().getName());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof RegisterUserOutput output) {
            body = registerUserTokenInsertion(output, response);
        }

        if (body instanceof LogInOutput output) {
            body = logInUserTokenInsertion(output, response);

        }

        return body;
    }

    private RegisterUserOutput logInUserTokenInsertion(LogInOutput output, ServerHttpResponse response) {

        String token = output.getToken();

        if (token != null) {
            response.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }

        return RegisterUserOutput.builder().build();
    }

    private LogInOutput registerUserTokenInsertion(RegisterUserOutput output, ServerHttpResponse response) {

        String token = output.getToken();

        if (token != null) {
            response.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }

        return LogInOutput.builder().build();
    }


}