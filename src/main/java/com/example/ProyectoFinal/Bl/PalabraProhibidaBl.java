package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.PalabraProhibidaRepository;
import com.example.ProyectoFinal.Entity.PalabraProhibida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PalabraProhibidaBl {

    @Autowired
    private PalabraProhibidaRepository palabraProhibidaRepository;

    @Transactional
    public void agregarPalabra(String palabra) {
        PalabraProhibida p = new PalabraProhibida();
        p.setPalabra(palabra);
        p.setStatus(true);
        p.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
        palabraProhibidaRepository.save(p);
    }

    @Transactional
    public void desactivar(Integer idPalabra) {
        PalabraProhibida p = palabraProhibidaRepository.findById(idPalabra)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada"));
        p.setStatus(false);
        palabraProhibidaRepository.save(p);
    }
    public List<PalabraProhibida> listar(){
        return palabraProhibidaRepository.findAll();
    }
}
