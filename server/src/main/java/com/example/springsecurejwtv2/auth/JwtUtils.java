package com.example.springsecurejwtv2.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final SecretKey SECRET_KEY;

    @Value("${jwt.expiration.seconds}")
    private int expirationTimeInSeconds;

    public boolean validateToken(String token, UserDetails user) {
        String userName = user.getUsername();
        Claims claims = extractClaims(token);
        return userName.equals(claims.getSubject()) && !isTokenExpired(claims);
    }

    public String createToken(UserDetails user) {
        return Jwts.builder()
                .issuer("kelvinqiu.tech")
                .subject(user.getUsername())
                .expiration(new Date(new Date().getTime() + TimeUnit.SECONDS.toMillis(expirationTimeInSeconds)))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}
