package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
    List<Ciudad> findByStatusTrueOrderByNombreAsc();
}
