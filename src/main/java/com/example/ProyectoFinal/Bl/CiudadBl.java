package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.CiudadRepository;
import com.example.ProyectoFinal.Dto.CiudadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CiudadBl {
    @Autowired
    private CiudadRepository ciudadRepository;

    @Transactional(readOnly = true)
    public List<CiudadDto> listarCiudades(){
        return ciudadRepository.findByStatusTrueOrderByNombreAsc().stream()
                .map(c -> {
                    CiudadDto dto = new CiudadDto();
                    dto.setIdCiudad(c.getIdCiudad());
                    dto.setNombre(c.getNombre());
                    return dto;
                })
                .toList();
    }

}
