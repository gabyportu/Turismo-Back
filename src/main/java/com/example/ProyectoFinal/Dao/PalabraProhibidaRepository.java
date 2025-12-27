package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.PalabraProhibida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PalabraProhibidaRepository extends JpaRepository<PalabraProhibida, Integer> {
    List<PalabraProhibida> findByStatusTrue();
    boolean existsByPalabraIgnoreCase(String palabra);
}
