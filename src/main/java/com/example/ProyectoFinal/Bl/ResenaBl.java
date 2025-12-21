package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.OfertaRepository;
import com.example.ProyectoFinal.Dao.ResenaRepository;
import com.example.ProyectoFinal.Dao.TuristaRepository;
import com.example.ProyectoFinal.Dto.ResenaDto;
import com.example.ProyectoFinal.Entity.Oferta;
import com.example.ProyectoFinal.Entity.Resena;
import com.example.ProyectoFinal.Entity.Turista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class ResenaBl {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private TuristaRepository turistaRepository;

    @Transactional
    public ResenaDto crearResena(Integer idTuristaFromToken,
                                 Integer idOferta,
                                 Integer calificacion,
                                 String comentario) {

        if (idOferta == null) {
            throw new RuntimeException("El idOferta es obligatorio");
        }

        if (calificacion == null || calificacion < 1 || calificacion > 5) {
            throw new RuntimeException("La calificación debe estar entre 1 y 5");
        }

        if (comentario == null || comentario.trim().isEmpty()) {
            throw new RuntimeException("El comentario es obligatorio");
        }

        Oferta oferta = ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        Turista turista = turistaRepository.findById(idTuristaFromToken)
                .orElseThrow(() -> new RuntimeException("Turista no encontrado"));

        // Evitar duplicados
        if (resenaRepository.existsByOferta_IdOfertaAndTurista_IdTurista(idOferta, idTuristaFromToken)) {
            throw new RuntimeException("Ya realizaste una reseña para esta oferta");
        }

        Resena resena = new Resena();
        resena.setOferta(oferta);
        resena.setTurista(turista);
        resena.setCalificacion(calificacion);
        resena.setComentario(comentario.trim());
        resena.setFecha(new Date(System.currentTimeMillis()));
        resena.setStatus(true);

        resena = resenaRepository.save(resena);

        ResenaDto dto = new ResenaDto();
        dto.setIdResena(resena.getIdResena());
        dto.setIdOferta(oferta.getIdOferta());
        dto.setIdTurista(turista.getIdTurista());
        dto.setCalificacion(resena.getCalificacion());
        dto.setComentario(resena.getComentario());
        dto.setFecha(resena.getFecha());
        dto.setStatus(resena.getStatus());
        return dto;
    }
}
