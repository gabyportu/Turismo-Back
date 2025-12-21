package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.TuristaRepository;
import com.example.ProyectoFinal.Dao.UsuarioRepository;
import com.example.ProyectoFinal.Dto.UsuarioDto;
import com.example.ProyectoFinal.Entity.Turista;
import com.example.ProyectoFinal.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UsuarioBl {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioDto registrarAdmin(UsuarioDto usuarioDto) {
        try{
            if(usuarioRepository.findByCorreo(usuarioDto.getCorreo()).isPresent()){
                throw new RuntimeException("El correo ya esta registrado");
            }
            Usuario usuario = new Usuario();
            usuario.setNombres(usuarioDto.getNombres());
            usuario.setApellidoPaterno(usuarioDto.getApellidoPaterno());
            usuario.setApellidoMaterno(usuarioDto.getApellidoMaterno());
            usuario.setFechaNacimiento(usuarioDto.getFechaNacimiento());
            usuario.setGenero(usuarioDto.getGenero());
            usuario.setTelefono(usuarioDto.getTelefono());
            usuario.setCorreo(usuarioDto.getCorreo());
            usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
            usuario.setStatus(true);
            usuario.setFechaRegistro(new Timestamp(System.currentTimeMillis()));

            usuario = usuarioRepository.save(usuario);

            UsuarioDto res = new UsuarioDto();
            res.setIdUsuario(usuario.getIdUsuario());
            res.setNombres(usuario.getNombres());
            res.setApellidoPaterno(usuario.getApellidoPaterno());
            res.setApellidoMaterno(usuario.getApellidoMaterno());
            res.setFechaNacimiento(usuario.getFechaNacimiento());
            res.setGenero(usuario.getGenero());
            res.setTelefono(usuario.getTelefono());
            res.setCorreo(usuario.getCorreo());
            res.setPassword(null);
            res.setStatus(usuario.getStatus());
            res.setFechaRegistro(usuario.getFechaRegistro());

            return res;

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al registrar usuario: " + e.getMessage());
        }
    }
}

