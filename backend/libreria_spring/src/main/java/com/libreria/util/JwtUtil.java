package com.libreria.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    //should be put in file txt and ignored
    public static final String SECRET = "ea9d4f1c8e9a6b3c7d2f4e7a3c9e1c3d2f8b2e1c2c6f4d3a9c7e4d9a6c3f8e";
    // Generate a JWT token for the user with the specified email
    public String generateTokem(String email){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    // Create a JWT token with the specified claims and user's email
    private String createToken(Map<String, Object> claims, String email){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    // Get the signing key using the secret key
    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
