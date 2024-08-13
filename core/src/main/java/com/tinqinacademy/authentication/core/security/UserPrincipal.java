package com.tinqinacademy.authentication.core.security;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrincipal {
    private String userId;
    private String role;
}
