package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer>{
    List<Oferta> findByEstadoAndStatus(String estado, Boolean status);
}
