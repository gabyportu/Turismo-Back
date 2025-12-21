package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.MultimediaOferta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultimediaOfertaRepository extends JpaRepository<MultimediaOferta, Integer> {
    List<MultimediaOferta> findByOferta_IdOferta(Integer idOferta);
    void deleteByOferta_IdOferta(Integer idOferta);
    List<MultimediaOferta> findByOferta_IdOfertaAndStatusTrue(Integer idOferta);
}
