package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.*;
import com.example.ProyectoFinal.Dto.UsuarioDto;
import com.example.ProyectoFinal.Entity.PalabraProhibida;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdmiApi {
    @Autowired
    private EmpresaBl empresaBl;
    @Autowired
    private UsuarioBl usuarioBl;
    @Autowired
    private OfertaBl ofertaBl;
    @Autowired
    private PalabraProhibidaBl palabraProhibidaBl;
    @Autowired
    private JwtBl jwtBl;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarAdmin(@RequestBody UsuarioDto usuarioDto) {
        try{
            UsuarioDto res = usuarioBl.registrarAdmin(usuarioDto);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    Map.of("message", "No se pudo registrar el usuario", "detail", e.getMessage())
            );
        }
    }

    @PutMapping("empresa/aprobar/{idEmpresa}")
    public ResponseEntity<?> aprobarEmpresa(@PathVariable Integer idEmpresa, @RequestHeader(value = "Authorization", required = false) String authHeader) {
        try{
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Token no enviado");
            }

            String token = authHeader.substring(7).trim();

            jwtBl.validateTokenAndRoles(token, "ROLE_ADMIN");

            empresaBl.aprobarEmpresa(idEmpresa);
            return ResponseEntity.ok("EMPRESA_APROBADA");
        }catch (AccessDeniedException e){
            return ResponseEntity.status(403).body(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR_INTERNO: " + e.getMessage());
        }
    }
    @PutMapping("empresa/rechazar/{idEmpresa}")
    public ResponseEntity<?> rechazarEmpresa(
            @PathVariable Integer idEmpresa,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Token no enviado");
            }

            String token = authHeader.substring(7).trim();

            jwtBl.validateTokenAndRoles(token, "ROLE_ADMIN");

            empresaBl.rechazarEmpresa(idEmpresa);
            return ResponseEntity.ok("EMPRESA_RECHAZADA");
        }catch (AccessDeniedException e){
            return ResponseEntity.status(403).body(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR_INTERNO: " + e.getMessage());
        }
    }

    @PutMapping("/oferta/aprobar/{idOferta}")
    public ResponseEntity<?> aprobarOferta(
            @PathVariable Integer idOferta,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Token no enviado");
            }

            String token = authHeader.substring(7).trim();

            jwtBl.validateTokenAndRoles(token, "ROLE_ADMIN");

            ofertaBl.aprobarOferta(idOferta);
            return ResponseEntity.ok("OFERTA APROBADA");
        } catch (AccessDeniedException e){
            return ResponseEntity.status(403).body(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR_INTERNO: " + e.getMessage());
        }
    }
    @PutMapping("/oferta/rechazar/{idOferta}")
    public ResponseEntity<?> rechazarOferta(
            @PathVariable Integer idOferta,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Token no enviado");
            }

            String token = authHeader.substring(7).trim();

            jwtBl.validateTokenAndRoles(token, "ROLE_ADMIN");

            ofertaBl.rechazarOferta(idOferta);
            return ResponseEntity.ok("OFERTA RECHAZADA");

        }catch (AccessDeniedException e){
            return ResponseEntity.status(403).body(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR_INTERNO: " + e.getMessage());
        }
    }

    @PostMapping("/crear/palabra")
    public ResponseEntity<?> crearPalabra(@RequestBody Map<String, String> body) {
        palabraProhibidaBl.agregarPalabra(body.get("palabra"));
        return ResponseEntity.ok("PALABRA AGREGADA");
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(palabraProhibidaBl.listar());
    }
}