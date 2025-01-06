package org.example.dma6m6beadando.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;

@Service
public class JwtService {

    private final String secret = "myasdtestasdjajasdkjhfasdlfj213126kljadfash24234afhksdfkshfjsdha4232adfhskahdasdatrtrerher1235gef234f2f2ed2fihi";

    private final long EXP_TIME = 60000;

    public String getuserName(String token){
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parser().verifyWith(getSecretKey())
                .build().parseSignedClaims(token).getPayload();
    }



    public String generateToken(String username) {

        var currentDate = new Date();

        Map<String, Objects> claims = new HashMap<>();

        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()*60*60*30))
                .and()
                .signWith(getSecretKey())
                .compact();

    }

    private SecretKey getSecretKey() {
        byte[] encodedKey = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(encodedKey);
    }


    public boolean ValidateToken(String token, UserDetails userDetails) {

        String username = getuserName(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {

        return getClaims(token).getExpiration();
    }
}
