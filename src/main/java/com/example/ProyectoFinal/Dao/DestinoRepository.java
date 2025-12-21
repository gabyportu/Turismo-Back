package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Integer> {

}
