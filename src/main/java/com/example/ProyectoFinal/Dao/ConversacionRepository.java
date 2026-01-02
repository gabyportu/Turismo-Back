package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Dto.ChatResumenDto;
import com.example.ProyectoFinal.Entity.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversacionRepository
        extends JpaRepository<Conversacion, Integer> {

    List<Conversacion> findByTurista_IdTuristaAndEmpresa_IdEmpresaAndStatusTrueOrderByFechaEnvioAsc(
            Integer idTurista, Integer idEmpresa
    );

    // Inbox de empresa (todos los mensajes de la empresa)
    List<Conversacion> findByEmpresa_IdEmpresaAndStatusTrueOrderByFechaEnvioAsc(Integer idEmpresa);

    // Inbox de turista (si quieres)
    List<Conversacion> findByTurista_IdTuristaAndStatusTrueOrderByFechaEnvioAsc(Integer idTurista);

    List<Conversacion> findByTurista_IdTuristaAndEmpresa_IdEmpresaOrderByFechaEnvioAsc(
            Integer idTurista,
            Integer idEmpresa
    );

    // ✅ Mis chats cuando soy TURISTA: me lista empresas con último mensaje y fecha
    @Query("""
        SELECT(
            t.idTurista,
            u.nombres,
            e.idEmpresa,
            e.nombre,
            c.mensaje,
            c.fechaEnvio
        )
        FROM Conversacion c
        JOIN c.turista t
        JOIN t.usuario u
        JOIN c.empresa e
        WHERE t.idTurista = :idTurista
          AND c.fechaEnvio = (
              SELECT MAX(c2.fechaEnvio)
              FROM Conversacion c2
              WHERE c2.turista.idTurista = :idTurista
                AND c2.empresa.idEmpresa = e.idEmpresa
          )
        ORDER BY c.fechaEnvio DESC
    """)
    List<Object[]> listarMisChatsTurista(@Param("idTurista") Integer idTurista);


    // ✅ Mis chats cuando soy EMPRESA: me lista turistas con último mensaje y fecha
    @Query("""
        SELECT(
            t.idTurista,
            u.nombres,
            e.idEmpresa,
            e.nombre,
            c.mensaje,
            c.fechaEnvio
        )
        FROM Conversacion c
        JOIN c.turista t
        JOIN t.usuario u
        JOIN c.empresa e
        WHERE e.idEmpresa = :idEmpresa
          AND c.fechaEnvio = (
              SELECT MAX(c2.fechaEnvio)
              FROM Conversacion c2
              WHERE c2.empresa.idEmpresa = :idEmpresa
                AND c2.turista.idTurista = t.idTurista
          )
        ORDER BY c.fechaEnvio DESC
    """)
    List<Object[]> listarMisChatsEmpresa(@Param("idEmpresa") Integer idEmpresa);
}
