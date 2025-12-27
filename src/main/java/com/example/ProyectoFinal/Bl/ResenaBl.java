package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.OfertaRepository;
import com.example.ProyectoFinal.Dao.ResenaRepository;
import com.example.ProyectoFinal.Dao.TuristaRepository;
import com.example.ProyectoFinal.Dto.CrearResenaRequestDto;
import com.example.ProyectoFinal.Dto.ResenaDto;
import com.example.ProyectoFinal.Dto.ResenaViewDto;
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
    public ResenaViewDto crearResena(CrearResenaRequestDto dto, Integer idTurista){

        if (dto.getCalificacion() < 1 || dto.getCalificacion() > 5) {
            throw new RuntimeException("La calificación debe estar entre 1 y 5");
        }

        filtroGroseriasBl.validar(dto.getComentario());

        Oferta oferta = ofertaRepository.findById(dto.getIdOferta())
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        Turista turista = turistaRepository.findById(idTurista)
                .orElseThrow(() -> new RuntimeException("Turista no encontrado"));

        if (resenaRepository.existsByOferta_IdOfertaAndTurista_IdTurista(
                oferta.getIdOferta(), idTurista)) {
            throw new RuntimeException("Ya realizaste una reseña para esta oferta");
        }
        Resena r = new Resena();
        r.setOferta(oferta);
        r.setTurista(turista);
        r.setCalificacion(dto.getCalificacion());
        r.setComentario(dto.getComentario());
        r.setFecha(new java.sql.Date(System.currentTimeMillis()));
        r.setStatus(true);

        r = resenaRepository.save(r);

        // 🔁 respuesta con usuario
        var u = turista.getUsuario();

        ResenaViewDto res = new ResenaViewDto();
        res.setIdResena(r.getIdResena());
        res.setIdOferta(oferta.getIdOferta());
        res.setIdTurista(turista.getIdTurista());
        res.setCalificacion(r.getCalificacion());
        res.setComentario(r.getComentario());
        res.setFecha(r.getFecha());

        res.setIdUsuario(u.getIdUsuario());
        res.setNombreUsuario(
                (u.getNombres() + " " +
                        u.getApellidoPaterno() + " " +
                        u.getApellidoMaterno()).trim()
        );

        return res;
    }

    @Transactional(readOnly = true)
    public List<ResenaViewDto> listarPorOferta(Integer idOferta) {

        return resenaRepository
                .findByOferta_IdOfertaAndStatusTrueOrderByIdResenaDesc(idOferta)
                .stream()
                .map(r -> {
                    ResenaViewDto dto = new ResenaViewDto();
                    dto.setIdResena(r.getIdResena());
                    dto.setIdOferta(r.getOferta().getIdOferta());
                    dto.setIdTurista(r.getTurista().getIdTurista());
                    dto.setCalificacion(r.getCalificacion());
                    dto.setComentario(r.getComentario());
                    dto.setFecha(r.getFecha());

                    // ✅ sacar Usuario desde Turista -> Usuario
                    if (r.getTurista() != null && r.getTurista().getUsuario() != null) {
                        var u = r.getTurista().getUsuario();

                        dto.setIdUsuario(u.getIdUsuario());

                        String fullName =
                                safe(u.getNombres()) + " " +
                                        safe(u.getApellidoPaterno()) + " " +
                                        safe(u.getApellidoMaterno());

                        dto.setNombreUsuario(fullName.trim().replaceAll("\\s+", " "));
                    }

                    return dto;
                })
                .toList();
    }

    private String safe(String s) {
        return (s == null) ? "" : s.trim();
    }
}
