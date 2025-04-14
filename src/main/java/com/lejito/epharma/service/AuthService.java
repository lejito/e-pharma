package com.lejito.epharma.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class AuthService {
    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private final long EXPIRATION_TIME = 86400000; // 1 day (in ms)

    public String generateToken(String id) {
        return Jwts.builder()
                .subject(id)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}