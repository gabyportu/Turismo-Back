package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.ResenaBl;
import com.example.ProyectoFinal.Dto.CrearResenaRequestDto;
import com.example.ProyectoFinal.Dto.ResenaDto;
import com.example.ProyectoFinal.Entity.Resena;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resenas")
@CrossOrigin(origins = "*")
public class ResenaApi {

    @Autowired
    private ResenaBl resenaBl;

    @PostMapping("/crear")
    public ResponseEntity<?>crearResena(@RequestBody CrearResenaRequestDto crearResenaRequestDto, HttpServletRequest httpServletRequest) {
        Integer idTurista = (Integer) httpServletRequest.getAttribute("idTurista");
        String role = (String) httpServletRequest.getAttribute("role");

        if(idTurista == null || !"ROLE_TURISTA".equals(role)) {
            return ResponseEntity.status(403)
                    .body(Map.of("error", "Solo turistas pueden crear reseñas"));
        }
        try{
            Resena resena = resenaBl.crearResena(crearResenaRequestDto, idTurista);
            return ResponseEntity.ok(resena);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/oferta/{idOferta}")
    public ResponseEntity<List<ResenaDto>> oferta(@PathVariable Integer idOferta){
        return ResponseEntity.ok(
                resenaBl.listarResenasPorOferta(idOferta)
        );
    }
}
