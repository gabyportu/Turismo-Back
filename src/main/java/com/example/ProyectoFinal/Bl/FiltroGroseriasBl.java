package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.PalabraProhibidaRepository;
import com.example.ProyectoFinal.Entity.PalabraProhibida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FiltroGroseriasBl {
    @Autowired
    private PalabraProhibidaRepository palabraProhibidaRepository;

    private static final Set<String> PALABRAS_PROHIBIDAS = Set.of(
            "mierda", "puta", "carajo", "imbecil", "idiota"
    );

    public void validar(String texto) {
        if (texto == null) return;

        String lower = texto.toLowerCase();

        for (String palabra : PALABRAS_PROHIBIDAS) {
            if (lower.contains(palabra)) {
                throw new RuntimeException("El comentario contiene lenguaje inapropiado");
            }
        }
    }
}
