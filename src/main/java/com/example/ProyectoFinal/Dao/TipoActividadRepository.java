package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.TipoActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoActividadRepository extends JpaRepository<TipoActividad, Integer> {
    List<TipoActividad> findByStatusTrueOrderByNombreAsc();
}
