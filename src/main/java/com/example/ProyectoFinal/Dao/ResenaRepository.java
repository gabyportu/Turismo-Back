package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.Resena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResenaRepository extends JpaRepository<Resena, Integer> {

    List<Resena> findByOferta_IdOfertaAndStatusTrueOrderByIdResenaDesc(Integer idOferta);

    boolean existsByOferta_IdOfertaAndTurista_IdTurista(Integer idOferta, Integer idTurista);

    Optional<Resena>findByIdResenaAndTurista_IdTurista(Integer idResena, Integer idTurista);

}