package com.example.ProyectoFinal.Api;


import com.example.ProyectoFinal.Bl.TuristaBl;
import com.example.ProyectoFinal.Dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turista")
@CrossOrigin(origins = "*")
public class TuristaApi {
    @Autowired
    private TuristaBl turistaBl;

    @PostMapping("/registrar")
    public UsuarioDto registrarTurista(@RequestBody UsuarioDto usuarioDto) {
        return turistaBl.registrarTurista(usuarioDto);
    }
}
