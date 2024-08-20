package com.tinqinacademy.authentication.persistence.repository;

import com.tinqinacademy.authentication.persistence.entities.BlackListedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlackListedTokenRepository extends JpaRepository<BlackListedToken, UUID> {
    Optional<BlackListedToken> findByToken(String token);
}
