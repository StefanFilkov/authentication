package com.tinqinacademy.authentication.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "black-listed-tokens")
public class BlackListedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String token;
}
