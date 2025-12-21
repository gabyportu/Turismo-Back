package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Integer> {
    boolean existsByUsuario_IdUsuario(Integer idUsuario);
    Optional<Representante> findByUsuario_IdUsuario(Integer idUsuario);
    Optional<Representante> findByEmpresa_IdEmpresa(Integer idEmpresa);


}
