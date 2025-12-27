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
        SELECT o.idOferta,
               o.titulo,
               o.precio,
               AVG(r.calificacion),
               COUNT(r.idResena)
        FROM Oferta o
        JOIN Resena r ON r.oferta.idOferta = o.idOferta
        WHERE o.estado = 'APROBADO'
          AND o.status = true
          AND r.status = true
        GROUP BY o.idOferta, o.titulo, o.precio
        ORDER BY AVG(r.calificacion) DESC, COUNT(r.idResena) DESC
    """)
    List<Object[]> listarOfertasMejorPuntuadas();

    @Query("""
        SELECT o.idOferta,
               o.titulo,
               o.precio,
               COALESCE(AVG(r.calificacion), 0),
               COUNT(r.idResena)
        FROM Oferta o
        LEFT JOIN Resena r 
            ON r.oferta.idOferta = o.idOferta
            AND r.status = true
        WHERE o.estado = 'APROBADO'
          AND o.status = true
        GROUP BY o.idOferta, o.titulo, o.precio
        ORDER BY o.fechaCreacion DESC
    """)
    List<Object[]> listarOfertasAprobadas();

    // 🔹 Caso 1: Pendiente + Aprobado
    List<Oferta> findByEmpresa_IdEmpresaAndStatusTrueAndEstadoIn(
            Integer idEmpresa,
            List<String> estados
    );

    // 🔹 Caso 2: Solo aprobado
    List<Oferta> findByEmpresa_IdEmpresaAndStatusTrueAndEstado(
            Integer idEmpresa,
            String estado
    );


}
