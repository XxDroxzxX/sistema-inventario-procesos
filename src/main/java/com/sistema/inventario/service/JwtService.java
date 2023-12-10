package com.sistema.inventario.service;

import com.sistema.inventario.exceptions.AuthenticationFailedException;
import com.sistema.inventario.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service

public class JwtService {
    private static final String SECRET_KEY="eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJlbGl1IiwiVXNlcm5hbWUiOiJlbGl1IiwiZXhwIjoxNzAyNzU4Nzk1LCJpYXQiOjE3MDAxNjY3OTV9.7_xe0cw6bq-IWoYrfEtlZEarxGtWU1uSOatKm0asJho";
    private static final long accessTokenValidity = 60*60*1000;
    public String getToken(UserDetails user){
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user){
        return Jwts.builder().
                setClaims(extraClaims).
                setSubject(user.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity)).
                signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).
                compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            throw new AuthenticationFailedException(e.getMessage());
        }
    }
}