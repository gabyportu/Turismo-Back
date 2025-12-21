package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.OfertaDestino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaDestinoRepository extends JpaRepository<OfertaDestino, Integer> {
    void deleteByOferta_IdOferta(Integer idOferta);
    List<OfertaDestino> findByOferta_IdOferta(Integer idOferta);
}
