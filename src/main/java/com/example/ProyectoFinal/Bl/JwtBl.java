package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtBl {
    private final JwtProperties props;

    public JwtBl(@Qualifier("jwtProperties") JwtProperties props) {
        this.props = props;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(props.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    // ✅ Token con IDs (no rompe tu login)
    public String generateToken(String email, String role, Integer idUsuario, Integer idTurista, Integer idEmpresa) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("idUsuario", idUsuario)
                .claim("idTurista", idTurista) // null si no es turista
                .claim("idEmpresa", idEmpresa) // null si no es empresa
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(props.getExpirationMinutes(), ChronoUnit.MINUTES)))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
