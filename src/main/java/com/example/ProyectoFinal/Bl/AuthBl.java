package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.RepresentanteRepository;
import com.example.ProyectoFinal.Dao.TuristaRepository;
import com.example.ProyectoFinal.Dao.UsuarioRepository;
import com.example.ProyectoFinal.Dto.LoginRequestDto;
import com.example.ProyectoFinal.Dto.LoginResponseDto;
import com.example.ProyectoFinal.Entity.Representante;
import com.example.ProyectoFinal.Entity.Turista;
import com.example.ProyectoFinal.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
