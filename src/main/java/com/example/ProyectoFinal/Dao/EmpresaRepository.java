package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    boolean existsByNit(String nit);
    Optional<Empresa> findByNit(String nit);
    List<Empresa> findByEstadoAndStatus(String estado, Boolean status);
}
