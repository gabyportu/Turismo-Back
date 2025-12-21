package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversacionRepository
        extends JpaRepository<Conversacion, Integer> {

    List<Conversacion> findByTurista_IdTuristaAndEmpresa_IdEmpresaOrderByFechaEnvioAsc(
            Integer idTurista,
            Integer idEmpresa
    );
}
