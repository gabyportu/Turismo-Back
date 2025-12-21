package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.OfertaActividad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfertaActividadRepository extends JpaRepository<OfertaActividad, Integer> {
    void deleteByOferta_IdOferta(Integer idOferta);
    List<OfertaActividad> findByOferta_IdOferta(Integer idOferta);

}
