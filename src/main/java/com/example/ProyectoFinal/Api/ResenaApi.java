package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.JwtBl;
import com.example.ProyectoFinal.Bl.ResenaBl;
import com.example.ProyectoFinal.Dto.CrearResenaRequestDto;
import com.example.ProyectoFinal.Dto.ResenaDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/resenas")
@CrossOrigin(origins = "*")
public class ResenaApi {

    @Autowired
    private ResenaBl resenaBl;

    @Autowired
    private JwtBl jwtBl;

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResenaDto> crearResena(
            HttpServletRequest request,
            @RequestBody CrearResenaRequestDto req
    ) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(UNAUTHORIZED, "Falta token Bearer");
        }

        String token = authHeader.substring(7);

        Claims claims;
        try {
            claims = jwtBl.parseClaims(token);
        } catch (Exception e) {
            throw new ResponseStatusException(UNAUTHORIZED, "Token inválido o expirado");
        }

        String role = claims.get("role") != null ? String.valueOf(claims.get("role")).trim() : null;
        Integer idTurista = toIntFlexible(claims.get("idTurista"));

        if (!"ROLE_TURISTA".equals(role)) {
            throw new ResponseStatusException(FORBIDDEN, "No autorizado: solo TURISTA.");
        }
        if (idTurista == null) {
            throw new ResponseStatusException(FORBIDDEN, "Token sin idTurista.");
        }

        ResenaDto res = resenaBl.crearResena(
                idTurista,
                req.getIdOferta(),
                req.getCalificacion(),
                req.getComentario()
        );

        return ResponseEntity.ok(res);
    }

    private Integer toIntFlexible(Object value) {
        if (value == null) return null;
        if (value instanceof Number n) return n.intValue();
        if (value instanceof String s) {
            try { return Integer.parseInt(s.trim()); } catch (Exception ignored) {}
        }
        return null;
    }
}
