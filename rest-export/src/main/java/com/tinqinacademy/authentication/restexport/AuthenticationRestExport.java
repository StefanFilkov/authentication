    package com.tinqinacademy.authentication.restexport;

import com.tinqinacademy.authentication.api.operations.validation.ValidationInput;
import com.tinqinacademy.authentication.api.operations.validation.ValidationOutput;
import com.tinqinacademy.authentication.api.urls.URLMappings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationRestExport {

    @PostMapping(URLMappings.VALIDATE_TOKEN)
    ResponseEntity<ValidationOutput> validateToken(@RequestBody ValidationInput input);
}
