package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.DocumentoEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoEmpresaRepository extends JpaRepository<DocumentoEmpresa, Integer>{
    List<DocumentoEmpresa> findByEmpresa_IdEmpresa(Integer idEmpresa);
}
