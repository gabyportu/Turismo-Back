package com.example.ProyectoFinal.Dao;

import com.example.ProyectoFinal.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.correo =?1 AND u.password =?2")
    Optional<Usuario> findUsuarioByCorreoAndPassword(String correo, String password);

    @Query("SELECT u FROM Usuario u WHERE u.correo = ?1")
    Optional<Usuario> findUsuarioByCorreo(String correo);

    Optional<Usuario> findByCorreo(String correo);


}
