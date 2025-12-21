package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.ConversacionRepository;
import com.example.ProyectoFinal.Dao.EmpresaRepository;
import com.example.ProyectoFinal.Dao.TuristaRepository;
import com.example.ProyectoFinal.Dto.ChatMessageDto;
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

    @Transactional
    public Conversacion guardarMensaje(ChatMessageDto dto) {

        Turista turista = turistaRepository.findById(dto.getIdTurista())
                .orElseThrow(() -> new RuntimeException("Turista no encontrado"));

        Empresa empresa = empresaRepository.findById(dto.getIdEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        Conversacion c = new Conversacion();
        c.setTurista(turista);
        c.setEmpresa(empresa);
        c.setMensaje(dto.getMensaje());
        c.setFechaEnvio(new Timestamp(System.currentTimeMillis()));
        c.setStatus(true);

        return conversacionRepository.save(c);
    }

    @Transactional(readOnly = true)
    public List<Conversacion> obtenerHistorial(Integer idTurista, Integer idEmpresa) {
        return conversacionRepository
                .findByTurista_IdTuristaAndEmpresa_IdEmpresaOrderByFechaEnvioAsc(
                        idTurista, idEmpresa
                );
    }
}

