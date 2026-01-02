package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.ConversacionRepository;
import com.example.ProyectoFinal.Dao.EmpresaRepository;
import com.example.ProyectoFinal.Dao.TuristaRepository;
import com.example.ProyectoFinal.Dto.ChatMessageDto;
import com.example.ProyectoFinal.Dto.ChatResumenDto;
import com.example.ProyectoFinal.Dto.ConversacionDto;
import com.example.ProyectoFinal.Entity.Conversacion;
import com.example.ProyectoFinal.Entity.Empresa;
import com.example.ProyectoFinal.Entity.Turista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ConversacionBl {

    @Autowired
    private ConversacionRepository conversacionRepository;
    @Autowired
    private TuristaRepository turistaRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private JwtBl jwtBl;

    @Transactional
    public ConversacionDto guardarMensajeWs(ChatMessageDto dto) {

        Turista turista = turistaRepository.findById(dto.getIdTurista())
                .orElseThrow(() -> new RuntimeException("Turista no encontrado"));

        Empresa empresa = empresaRepository.findById(dto.getIdEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        if (dto.getRolEmisor() == null || dto.getIdEmisor() == null) {
            throw new RuntimeException("EMISOR_NO_DEFINIDO");
        }

        Conversacion c = new Conversacion();
        c.setTurista(turista);
        c.setEmpresa(empresa);
        c.setMensaje(dto.getMensaje());
        c.setFechaEnvio(new java.sql.Timestamp(System.currentTimeMillis()));
        c.setStatus(true);

        // ✅ obligatorios (tu DB los pide NOT NULL)
        c.setRolEmisor(dto.getRolEmisor());
        c.setIdEmisor(dto.getIdEmisor());

        Conversacion saved = conversacionRepository.save(c);

        // ✅ construir DTO plano (SIN mandar entidades)
        ConversacionDto res = new ConversacionDto();
        res.setIdConversacion(saved.getIdConversacion());
        res.setIdTurista(dto.getIdTurista());
        res.setIdEmpresa(dto.getIdEmpresa());
        res.setMensaje(saved.getMensaje());
        res.setFechaEnvio(saved.getFechaEnvio());
        res.setRolEmisor(saved.getRolEmisor());
        res.setIdEmisor(saved.getIdEmisor());

        // opcional: nombre del emisor (sin tocar lazy usuario)
        if ("TURISTA".equalsIgnoreCase(saved.getRolEmisor())) {
            res.setEmisorNombre("Turista"); // o consulta nombre con query si quieres
        } else {
            res.setEmisorNombre("Empresa");
        }

        // esMio NO lo calcules aquí si no tienes “quién soy” (cliente)
        // eso lo puedes calcular en front usando rolEmisor/idEmisor vs miId

        return res;
    }


    @Transactional(readOnly = true)
    public List<ConversacionDto> obtenerHistorialDto(Integer idTurista, Integer idEmpresa, String token) {

        // ✅ Validar token solo para permitir roles
        io.jsonwebtoken.Claims claims = jwtBl.validateTokenAndRoles(token, "ROLE_TURISTA", "ROLE_EMPRESA");
        String role = (String) claims.get("role");

        Integer idTuristaToken = toInt(claims.get("idTurista"));
        Integer idEmpresaToken = toInt(claims.get("idEmpresa"));

        // ✅ seguridad: turista solo ve lo suyo, empresa solo ve lo suyo
        if ("ROLE_TURISTA".equals(role) && (idTuristaToken == null || !idTuristaToken.equals(idTurista))) {
            throw new RuntimeException("NO_AUTORIZADO_TURISTA");
        }
        if ("ROLE_EMPRESA".equals(role) && (idEmpresaToken == null || !idEmpresaToken.equals(idEmpresa))) {
            throw new RuntimeException("NO_AUTORIZADO_EMPRESA");
        }

        List<Conversacion> lista = conversacionRepository
                .findByTurista_IdTuristaAndEmpresa_IdEmpresaOrderByFechaEnvioAsc(idTurista, idEmpresa);

        return lista.stream().map(c -> {
            ConversacionDto out = new ConversacionDto();
            out.setIdConversacion(c.getIdConversacion());
            out.setIdTurista(c.getTurista().getIdTurista());
            out.setIdEmpresa(c.getEmpresa().getIdEmpresa());
            out.setMensaje(c.getMensaje());
            out.setFechaEnvio(c.getFechaEnvio());
            out.setRolEmisor(c.getRolEmisor());
            out.setIdEmisor(c.getIdEmisor());
            out.setNombreEmpresa(c.getEmpresa().getNombre());
            if (c.getTurista() != null && c.getTurista().getUsuario() != null) {
                out.setNombreTurista(
                        c.getTurista().getUsuario().getNombres()
                );
            }

            if (c.getTurista() != null && c.getTurista().getUsuario() != null) {
                out.setApellidosTurista(c.getTurista().getUsuario().getApellidoPaterno());
            }


            // ✅ esMio “simple”: si soy turista, mis mensajes son los que envié como turista
            // Como tu tabla aún no guarda emisor, esto es aproximado:
            out.setEsMio(true);

            return out;
        }).toList();
    }

    private Integer toInt(Object value) {
        if (value == null) return null;
        if (value instanceof Integer i) return i;
        if (value instanceof Long l) return l.intValue();
        if (value instanceof Number n) return n.intValue();
        return null;
    }

    @Transactional(readOnly = true)
    public List<ChatResumenDto> listarMisChatsTurista(Integer idTurista){
        return conversacionRepository.listarMisChatsTurista(idTurista)
                .stream()
                .map(row -> new ChatResumenDto(
                        (Integer) row[0],
                        (String) row[1],
                        (Integer) row[2],
                        (String) row[3],
                        (String) row[4],
                        (Timestamp) row[5]
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ChatResumenDto> listarMisChatsEmpresa(Integer idEmpresa) {
        return conversacionRepository.listarMisChatsEmpresa(idEmpresa)
                .stream()
                .map(row -> new ChatResumenDto(
                        (Integer) row[0],
                        (String) row[1],
                        (Integer) row[2],
                        (String) row[3],
                        (String) row[4],
                        (Timestamp) row[5]
                ))
                .toList();
    }
}

