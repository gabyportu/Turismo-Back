package com.example.ProyectoFinal.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootConfiguration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {
    private String secret;
    private long expirationMinutes;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpirationMinutes() {
        return expirationMinutes;
    }

    public void setExpirationMinutes(long expirationMinutes) {
        this.expirationMinutes = expirationMinutes;
    }
}
