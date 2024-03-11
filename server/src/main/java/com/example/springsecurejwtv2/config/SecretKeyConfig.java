package com.example.springsecurejwtv2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class SecretKeyConfig {

    @Value("${jwt.secret}")
    private String secretString;

    @Bean
    public SecretKey secretKey() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(secretString.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(hash, "HmacSHA256");
    }

}
