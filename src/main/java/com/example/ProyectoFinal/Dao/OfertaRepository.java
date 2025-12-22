package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Dto.OfertaRankingDto;
import com.example.ProyectoFinal.Entity.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer>{
    List<Oferta> findByEstadoAndStatus(String estado, Boolean status);

    @Query("""
    SELECT new com.example.ProyectoFinal.Dto.OfertaRankingDto(
        o.idOferta,
        o.titulo,
        o.precio,
        AVG(r.calificacion),
        COUNT(r.idResena)
    )
    FROM Oferta o
    JOIN Resena r ON r.oferta.idOferta = o.idOferta
    WHERE o.estado = 'APROBADO'
      AND o.status = true
      AND r.status = true
    GROUP BY o.idOferta, o.titulo, o.precio
    ORDER BY AVG(r.calificacion) DESC
""")
    List<OfertaRankingDto> listarOfertasMejorPuntuadas();

}
