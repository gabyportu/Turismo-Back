package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.ConversacionBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@CrossOrigin("*")
public class ConversacionApi {

    @Autowired
    private ConversacionBl conversacionBl;

    @GetMapping("/historial")
    public ResponseEntity<?> historial(
            @RequestParam Integer idTurista,
            @RequestParam Integer idEmpresa
    ) {
        return ResponseEntity.ok(
                conversacionBl.obtenerHistorial(idTurista, idEmpresa)
        );
    }
}

