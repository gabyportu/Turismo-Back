package com.example.ProyectoFinal.config;

import com.example.ProyectoFinal.Bl.JwtBl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtBl jwtBl;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            Claims claims = jwtBl.parseClaims(token);

            String email = claims.getSubject();
            String role = (String) claims.get("role");

            Integer idUsuario = toInt(claims.get("idUsuario"));
            Integer idTurista = toInt(claims.get("idTurista"));
            Integer idEmpresa = toInt(claims.get("idEmpresa"));

            // ✅ request attributes para controllers
            request.setAttribute("role", role);
            request.setAttribute("idUsuario", idUsuario);
            request.setAttribute("idTurista", idTurista);
            request.setAttribute("idEmpresa", idEmpresa);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            role != null ? List.of(new SimpleGrantedAuthority(role)) : List.of()
                    );

            // ✅ details (fallback)
            auth.setDetails(Map.of(
                    "role", role,
                    "idUsuario", idUsuario,
                    "idTurista", idTurista,
                    "idEmpresa", idEmpresa
            ));

            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private Integer toInt(Object value) {
        if (value == null) return null;
        if (value instanceof Integer i) return i;
        if (value instanceof Long l) return l.intValue();
        if (value instanceof Number n) return n.intValue();
        return null;
    }
}
