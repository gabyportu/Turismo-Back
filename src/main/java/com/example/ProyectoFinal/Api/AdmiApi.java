package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.EmpresaBl;
import com.example.ProyectoFinal.Bl.OfertaBl;
import com.example.ProyectoFinal.Bl.UsuarioBl;
import com.example.ProyectoFinal.Dto.UsuarioDto;
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
    public ResponseEntity<?> aprobarEmpresa(@PathVariable Integer idEmpresa) {
        empresaBl.aprobarEmpresa(idEmpresa);
        return ResponseEntity.ok().body("Empresa aprobada");
    }

    @PutMapping("empresa/rechazar/{idEmpresa}")
    public ResponseEntity<?> rechazarEmpresa(@PathVariable Integer idEmpresa) {
        empresaBl.rechazarEmpresa(idEmpresa);
        return ResponseEntity.ok().body("Empresa rechazada");
    }

    @PutMapping("oferta/aprobar/{idOferta}")
    public ResponseEntity<?> aprobarOferta(@PathVariable Integer idOferta) {
        ofertaBl.aprobarOferta(idOferta);
        return ResponseEntity.ok(Map.of(
                "message", "Oferta aprobada",
                "idOferta", idOferta,
                "estado", "APROBADO"
        ));
    }
    @PutMapping("oferta/rechazar/{idOferta}")
    public ResponseEntity<?> rechazarOferta (@PathVariable Integer idOferta) {
        ofertaBl.rechazarOferta(idOferta);
        return ResponseEntity.ok(Map.of(
                "message", "Oferta rechazada",
                "idOferta", idOferta,
                "estado", "RECHAZADO"
        ));
    }
}