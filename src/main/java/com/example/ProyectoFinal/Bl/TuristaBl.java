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
public class TuristaBl {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TuristaRepository turistaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioDto registrarTurista(UsuarioDto usuarioDto) {
        if (usuarioRepository.findByCorreo(usuarioDto.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
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

        usuario.setFechaRegistro(Timestamp.from(Instant.now()));

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        Turista turista = new Turista();
        turista.setUsuario(usuarioGuardado);
        turista.setStatus(true);
        turistaRepository.save(turista);

        UsuarioDto res = new UsuarioDto();
        res.setIdUsuario(usuarioGuardado.getIdUsuario());
        res.setNombres(usuarioGuardado.getNombres());
        res.setApellidoPaterno(usuarioGuardado.getApellidoPaterno());
        res.setApellidoMaterno(usuarioGuardado.getApellidoMaterno());
        res.setFechaNacimiento(usuarioGuardado.getFechaNacimiento());
        res.setGenero(usuarioGuardado.getGenero());
        res.setTelefono(usuarioGuardado.getTelefono());
        res.setCorreo(usuarioGuardado.getCorreo());
        res.setStatus(true);
        res.setPassword(null);
        res.setFechaRegistro(usuarioGuardado.getFechaRegistro());

        return res;

    }

}
