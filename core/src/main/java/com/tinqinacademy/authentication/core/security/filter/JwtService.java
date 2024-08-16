package com.tinqinacademy.authentication.core.security.filter;

import com.tinqinacademy.authentication.persistence.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    @Value("${JWT_EXPIRATION_TIME}")
    private Long JWT_EXPIRATION_TIME;

    public String generateToken(User user) {
        Map<String,String> claims = Map.of(
                "userId", user.getId().toString(),
                "username", user.getName(),
                "role", user.getRole().toString()
        );

        return generateToken(claims);
    }

    public String generateToken(Map<String, String > claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(JWT_EXPIRATION_TIME).toInstant(ZoneOffset.UTC)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
//TODO for bff
//    public String extractUserId(String token) {
//        Claims claims = extractAllClaims(token);
//
//        return (String) claims.get("id");
//    }

//    private Claims extractAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    //TOKEN VALIDITY
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }

//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }

    //TODO add to bff
//    public boolean isTokenValid(String token, User user) {
//        final String userId = extractUserId(token);
//        return (userId.equals(String.valueOf(user.getId())) && !isTokenExpired(token));
//    }
}
