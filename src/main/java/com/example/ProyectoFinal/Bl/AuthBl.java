package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.RepresentanteRepository;
import com.example.ProyectoFinal.Dao.TuristaRepository;
import com.example.ProyectoFinal.Dao.UsuarioRepository;
import com.example.ProyectoFinal.Dto.LoginRequestDto;
import com.example.ProyectoFinal.Dto.LoginResponseDto;
import com.example.ProyectoFinal.Entity.Representante;
import com.example.ProyectoFinal.Entity.Turista;
import com.example.ProyectoFinal.Entity.Usuario;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthBl {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TuristaRepository turistaRepository;

    @Autowired
    private RepresentanteRepository representanteRepository;

    @Autowired
    private JwtBl jwtBl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSenderBl emailSenderBl;

    private final String FRONT_RESET_URL = "http://localhost:4200/reset-password";

    public LoginResponseDto login(LoginRequestDto req) {

        Usuario u = usuarioRepository.findByCorreo(req.getCorreo())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (Boolean.FALSE.equals(u.getStatus())) {
            throw new RuntimeException("Usuario inactivo");
        }

        if (!passwordEncoder.matches(req.getPassword(), u.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // ✅ Rol
        String role;
        boolean esTurista = turistaRepository.existsByUsuario_IdUsuario(u.getIdUsuario());
        boolean esEmpresa = representanteRepository.existsByUsuario_IdUsuario(u.getIdUsuario());

        if (esTurista) role = "ROLE_TURISTA";
        else if (esEmpresa) role = "ROLE_EMPRESA";
        else role = "ROLE_ADMIN";

        // ✅ IDs para claims + response
        Integer idTurista = null;
        Integer idEmpresa = null;
        Integer idRepresentante = null;

        if ("ROLE_TURISTA".equals(role)) {
            Turista t = turistaRepository.findByUsuario_IdUsuario(u.getIdUsuario()).orElse(null);
            if (t != null) idTurista = t.getIdTurista();
        }

        if ("ROLE_EMPRESA".equals(role)) {
            Representante r = representanteRepository.findByUsuario_IdUsuario(u.getIdUsuario()).orElse(null);
            if (r != null) {
                idRepresentante = r.getIdRepresentante();
                if (r.getEmpresa() != null) idEmpresa = r.getEmpresa().getIdEmpresa();
            }
        }

        // ✅ Token con IDs
        String token = jwtBl.generateToken(u.getCorreo(), role, u.getIdUsuario(), idTurista, idEmpresa);

        LoginResponseDto res = new LoginResponseDto();
        res.setToken(token);
        res.setRole(role);
        res.setIdUsuario(u.getIdUsuario());
        res.setIdTurista(idTurista);
        res.setIdRepresentante(idRepresentante);
        res.setIdEmpresa(idEmpresa);

        return res;
    }

    @Transactional
    public void solicitarRecuperacion(String correo) {

        if (correo == null || correo.isBlank()) {
            throw new RuntimeException("CORREO_OBLIGATORIO");
        }

        // Importante: por seguridad, NO digas si existe o no.
        usuarioRepository.findByCorreo(correo.trim())
                .ifPresent(usuario -> {
                    String token = jwtBl.generatePasswordResetToken(usuario.getCorreo());

                    String link = FRONT_RESET_URL + "?token=" + token;

                    String body =
                            "Recuperación de contraseña\n\n" +
                                    "Haz clic en el siguiente enlace para cambiar tu contraseña (válido 15 minutos):\n" +
                                    link + "\n\n" +
                                    "Si tú no solicitaste esto, ignora este correo.";

                    emailSenderBl.sendEmail("proyectoturismoportugal@gmail.com", "Recuperación de contraseña", body);
                });

        // Respuesta neutra (aunque no exista)
    }

    @Transactional
    public void confirmarRecuperacion(String token, String nuevaPassword) {

        if (token == null || token.isBlank()) {
            throw new RuntimeException("TOKEN_OBLIGATORIO");
        }
        if (nuevaPassword == null || nuevaPassword.isBlank()) {
            throw new RuntimeException("PASSWORD_OBLIGATORIA");
        }
        if (nuevaPassword.length() < 6) {
            throw new RuntimeException("PASSWORD_MUY_CORTA");
        }

        Claims claims = jwtBl.parseResetClaims(token);
        String correo = claims.getSubject();

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("USUARIO_NO_ENCONTRADO"));

        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);
    }
}
