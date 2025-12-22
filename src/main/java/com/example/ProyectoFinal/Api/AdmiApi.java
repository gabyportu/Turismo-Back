package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.EmpresaBl;
import com.example.ProyectoFinal.Bl.OfertaBl;
import com.example.ProyectoFinal.Bl.PalabraProhibidaBl;
import com.example.ProyectoFinal.Bl.UsuarioBl;
import com.example.ProyectoFinal.Dto.UsuarioDto;
import com.example.ProyectoFinal.Entity.PalabraProhibida;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> aprobarEmpresa(@PathVariable Integer idEmpresa, HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        if(!"ROLE_ADMIN".equals(role)) {
            return ResponseEntity.status(403).body(Map.of("error", "Acceso denegado. Solo administradores"));
        }
        try{
            empresaBl.aprobarEmpresa(idEmpresa);
            return ResponseEntity.ok(Map.of("mensaje", "Empresa aprobarada"));
        }catch (RuntimeException e){
            return ResponseEntity.status(403).body(Map.of("error", e.getMessage()));
        }
    }
    @PutMapping("empresa/rechazar/{idEmpresa}")
    public ResponseEntity<?> rechazarEmpresa(
            @PathVariable Integer idEmpresa,
            HttpServletRequest request
    ) {
        String role = (String) request.getAttribute("role");

        if (!"ROLE_ADMIN".equals(role)) {
            return ResponseEntity.status(403).body(Map.of("error", "Acceso denegado. Solo administradores."));
        }

        try {
            empresaBl.rechazarEmpresa(idEmpresa);
            return ResponseEntity.ok(Map.of("mensaje", "Empresa rechazada correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/oferta/aprobar/{idOferta}")
    public ResponseEntity<?> aprobarOferta(
            @PathVariable Integer idOferta,
            HttpServletRequest request
    ) {
        String role = (String) request.getAttribute("role");

        if (!"ROLE_ADMIN".equals(role)) {
            return ResponseEntity.status(403)
                    .body(Map.of("error", "Acceso denegado. Solo administradores."));
        }

        try {
            ofertaBl.aprobarOferta(idOferta);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Oferta aprobada correctamente",
                    "idOferta", idOferta
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    @PutMapping("/oferta/rechazar/{idOferta}")
    public ResponseEntity<?> rechazarOferta(
            @PathVariable Integer idOferta,
            HttpServletRequest request
    ) {
        String role = (String) request.getAttribute("role");

        if (!"ROLE_ADMIN".equals(role)) {
            return ResponseEntity.status(403)
                    .body(Map.of("error", "Acceso denegado. Solo administradores."));
        }

        try {
            ofertaBl.rechazarOferta(idOferta);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Oferta rechazada correctamente",
                    "idOferta", idOferta
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/crear/palabra")
    public ResponseEntity<?> crearPalabra(@RequestBody Map<String, String> body) {
        palabraProhibidaBl.agregarPalabra(body.get("palabra"));
        return ResponseEntity.ok(Map.of("message", "Palabra agregada"));
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(palabraProhibidaBl.listar());
    }
}