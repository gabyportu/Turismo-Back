package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.JwtBl;
import com.example.ProyectoFinal.Bl.ResenaBl;
import com.example.ProyectoFinal.Dto.CrearResenaRequestDto;
import com.example.ProyectoFinal.Dto.EditarOfertaRequestDto;
import com.example.ProyectoFinal.Dto.ResenaDto;
import com.example.ProyectoFinal.Entity.Resena;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resenas")
@CrossOrigin(origins = "*")
public class ResenaApi {

    @Autowired
    private ResenaBl resenaBl;
    @Autowired
    private JwtBl jwtBl;

    @PostMapping("/crear")
    public ResponseEntity<?> crearResena(
            @RequestBody CrearResenaRequestDto dto,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("TOKEN_NO_ENVIADO");
            }

            String token = authHeader.substring(7).trim();
            jwtBl.validateTokenAndRoles(token, "ROLE_TURISTA");

            Claims claims = jwtBl.validateTokenAndRoles(token, "ROLE_TURISTA");


            Integer idTurista = (Integer) claims.get("idTurista");
            if (idTurista == null) {
                return ResponseEntity.status(403).body("USUARIO_NO_ES_TURISTA");
            }

            return ResponseEntity.ok(
                    resenaBl.crearResena(dto, idTurista)
            );

        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR_INTERNO");
        }
    }

    @GetMapping("/oferta/{idOferta}")
    public ResponseEntity<?> listarResenasPorOferta(
            @PathVariable Integer idOferta
    ) {
        try {
            return ResponseEntity.ok(
                    resenaBl.listarPorOferta(idOferta)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR_INTERNO");
        }
    }
}
