package com.sistema.inventario.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY = "7f08153513d489a979265575aa0dfd5bed7bd9588981bb60f4a5e19d0261447b";

    public String getToken(UserDetails user){
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user){
        return Jwts.builder().
                setClaims(extraClaims).
                setSubject(user.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())). //Fecha de creacion
                setExpiration(new Date(System.currentTimeMillis() + 1000 *60*24)).
                signWith(getKey(), SignatureAlgorithm.HS256).
                compact();
    }
    private Key getKey(){ 
        byte [] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

}
