package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.OfertaRepository;
import com.example.ProyectoFinal.Dao.ResenaRepository;
import com.example.ProyectoFinal.Dao.TuristaRepository;
import com.example.ProyectoFinal.Dto.CrearResenaRequestDto;
import com.example.ProyectoFinal.Dto.ResenaDto;
import com.example.ProyectoFinal.Entity.Oferta;
import com.example.ProyectoFinal.Entity.Resena;
import com.example.ProyectoFinal.Entity.Turista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ResenaBl {

    @Autowired
    private ResenaRepository resenaRepository;
    @Autowired
    private OfertaRepository ofertaRepository;
    @Autowired
    private TuristaRepository turistaRepository;
    @Autowired
    private FiltroGroseriasBl filtroGroseriasBl;

    @Transactional
    public Resena crearResena(CrearResenaRequestDto dto, Integer idTurista){

        Turista turista = turistaRepository.findById(idTurista)
                .orElseThrow(() -> new RuntimeException("El usuario no es turista"));

        Oferta oferta = ofertaRepository.findById(dto.getIdOferta())
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        if (resenaRepository.existsByOferta_IdOfertaAndTurista_IdTurista(
                oferta.getIdOferta(), idTurista)) {
            throw new RuntimeException("Ya registraste una reseña para esta oferta");
        }
        if (dto.getCalificacion() < 1 || dto.getCalificacion() > 5) {
            throw new RuntimeException("La calificación debe estar entre 1 y 5");
        }
        filtroGroseriasBl.validarComentario(dto.getComentario());

        Resena resena = new Resena();
        resena.setOferta(oferta);
        resena.setTurista(turista);
        resena.setCalificacion(dto.getCalificacion());
        resena.setComentario(dto.getComentario());
        resena.setFecha(new Date(System.currentTimeMillis()));
        resena.setStatus(true);

        return resenaRepository.save(resena);

    }


    @Transactional(readOnly = true)
    public List<ResenaDto> listarResenasPorOferta(Integer idOferta) {
        List<Resena> resenas = resenaRepository.findByOferta_IdOfertaAndStatusTrueOrderByIdResenaDesc(idOferta);

        return resenas.stream().map(r -> {
            ResenaDto res = new ResenaDto();
            res.setIdResena(r.getIdResena());
            res.setIdTurista(r.getTurista().getIdTurista());
            res.setIdOferta(r.getOferta().getIdOferta());
            res.setCalificacion(r.getCalificacion());
            res.setComentario(r.getComentario());
            res.setFecha(r.getFecha());
            res.setStatus(r.getStatus());
            return res;
        }).toList();
    }

}
