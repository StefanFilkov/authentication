package com.tinqinacademy.authentication.persistence;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum Role {
    ROLE_ADMIN, ROLE_USER, UNKNOWN;

    @JsonCreator
    public static Role getByCode(String name){
        return Arrays
                .stream(Role.values())
                .filter(role -> role.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
