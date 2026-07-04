package com.example.demo.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.demo.models.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.auth0.jwt.JWT.require;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return com.auth0.jwt.JWT.create()
                    .withIssuer("API")
                    .withSubject(user.getId().toString())
                    .withClaim("email", user.getEmail())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))) // Token expires in 2 hours
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generating token", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error generating token", e);
        }
    }

    public String getSubject(String tokenJWT) {
        return decodeToken(tokenJWT).getSubject();
    }

    public String getEmail(String tokenJWT) {
        return decodeToken(tokenJWT).getClaim("email").asString();
    }

    private DecodedJWT decodeToken(String tokenJWT) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return require(algorithm)
                    .withIssuer("API")
                    .build()
                    .verify(tokenJWT);
        }
        catch (Exception e) {
            throw new RuntimeException("Invalid token", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            require(algorithm)
                    .withIssuer("API")
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
