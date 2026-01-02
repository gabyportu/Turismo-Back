package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.ConversacionBl;
import com.example.ProyectoFinal.Bl.JwtBl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@CrossOrigin("*")
public class ConversacionApi {

    @Autowired private ConversacionBl conversacionBl;
    @Autowired private JwtBl jwtBl;

    @GetMapping("/historial")
    public ResponseEntity<?> historial(
            @RequestParam Integer idTurista,
            @RequestParam Integer idEmpresa,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("TOKEN_NO_ENVIADO");
            }

            String token = authHeader.substring(7).trim();
            jwtBl.validateTokenAndRoles(token, "ROLE_TURISTA", "ROLE_EMPRESA");

            return ResponseEntity.ok(conversacionBl.obtenerHistorialDto(idTurista, idEmpresa, token));

        } catch (org.springframework.security.access.AccessDeniedException e) {
            return ResponseEntity.status(403).body("ROL_NO_AUTORIZADO");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR_INTERNO: " + e.getMessage());
        }
    }

    @GetMapping("/mis-chats")
    public ResponseEntity<?> misChats(
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("TOKEN_NO_ENVIADO");
            }

            String token = authHeader.substring(7).trim();

            // ✅ puede entrar turista o empresa
            var claims = jwtBl.validateTokenAndRoles(token, "ROLE_TURISTA", "ROLE_EMPRESA");
            String role = (String) claims.get("role");

            Integer idTurista = toInt(claims.get("idTurista"));
            Integer idEmpresa = toInt(claims.get("idEmpresa"));

            if ("ROLE_TURISTA".equals(role)) {
                if (idTurista == null) return ResponseEntity.status(401).body("TOKEN_SIN_ID_TURISTA");
                return ResponseEntity.ok(conversacionBl.listarMisChatsTurista(idTurista));
            }

            if ("ROLE_EMPRESA".equals(role)) {
                if (idEmpresa == null) return ResponseEntity.status(401).body("TOKEN_SIN_ID_EMPRESA");
                return ResponseEntity.ok(conversacionBl.listarMisChatsEmpresa(idEmpresa));
            }

            return ResponseEntity.status(403).body("ROL_NO_AUTORIZADO");

        } catch (org.springframework.security.access.AccessDeniedException e) {
            return ResponseEntity.status(403).body("ROL_NO_AUTORIZADO");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR_INTERNO: " + e.getMessage());
        }
    }

    private Integer toInt(Object value) {
        if (value == null) return null;
        if (value instanceof Integer i) return i;
        if (value instanceof Long l) return l.intValue();
        if (value instanceof Number n) return n.intValue();
        return null;
    }
}


