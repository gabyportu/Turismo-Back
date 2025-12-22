package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.PalabraProhibidaRepository;
import com.example.ProyectoFinal.Entity.PalabraProhibida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiltroGroseriasBl {
    @Autowired
    private PalabraProhibidaRepository palabraProhibidaRepository;

    public boolean contienePalabraProhibida(String palabra) {
        if(palabra == null || palabra.isBlank()) return false;

        String comentario = palabra.toLowerCase();

        List<PalabraProhibida> palabras = palabraProhibidaRepository.findByStatusTrue();
        for(PalabraProhibida p : palabras) {
            if(comentario.contains(p.getPalabra().toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public void validarComentario(String comentario) {
        if (comentario == null || comentario.isBlank()) {
            throw new RuntimeException("El comentario no puede estar vacío");
        }

        String texto = comentario.toLowerCase();

        boolean contiene = palabraProhibidaRepository.findByStatusTrue()
                .stream()
                .anyMatch(p -> texto.contains(p.getPalabra().toLowerCase()));

        if (contiene) {
            throw new RuntimeException("El comentario contiene palabras no permitidas");
        }
    }
}
