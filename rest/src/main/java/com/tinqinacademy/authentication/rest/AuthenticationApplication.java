package com.tinqinacademy.authentication.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.tinqinacademy.authentication")
@EntityScan(basePackages = "com.tinqinacademy.authentication.persistence.entities")
@EnableJpaRepositories(basePackages = "com.tinqinacademy.authentication.persistence.repository")
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

}
