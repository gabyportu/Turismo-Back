package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.TipoActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoActividadRepository extends JpaRepository<TipoActividad, Integer> {
}
