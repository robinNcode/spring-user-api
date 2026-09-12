package com.example.user_api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Handles JWT token creation and validation.
 */
@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long expiration;

    /**
     * Creates the JWT service using application configuration.
     *
     * @param secret JWT signing secret
     * @param expiration token expiration in milliseconds
     */
    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );

        this.expiration = expiration;
    }

    /**
     * Generates a JWT token for a user.
     *
     * @param email authenticated user's email
     * @return signed JWT
     */
    public String generateToken(String email) {

        Date now = new Date();

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiration))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Extracts the subject from a JWT.
     *
     * @param token JWT token
     * @return token subject
     */
    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     * Validates a JWT against the expected username.
     *
     * @param token JWT token
     * @param username expected username
     * @return true when token is valid
     */
    public boolean isTokenValid(String token, String username) {

        String extractedUsername = extractUsername(token);

        return extractedUsername.equals(username)
                && !isExpired(token);
    }

    /**
     * Checks whether the JWT has expired.
     *
     * @param token JWT token
     * @return true when expired
     */
    private boolean isExpired(String token) {

        Date expirationDate = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();

        return expirationDate.before(new Date());
    }
}