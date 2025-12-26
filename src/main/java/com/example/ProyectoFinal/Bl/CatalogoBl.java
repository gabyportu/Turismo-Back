package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.CiudadRepository;
import com.example.ProyectoFinal.Dao.DestinoRepository;
import com.example.ProyectoFinal.Dao.TipoActividadRepository;
import com.example.ProyectoFinal.Dto.DestinoDto;
import com.example.ProyectoFinal.Dto.ItemDto;
import com.example.ProyectoFinal.Dto.TipoActividadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogoBl {
    @Autowired
    private DestinoRepository destinoRepository;
    @Autowired
    private TipoActividadRepository tipoActividadRepository;
    @Autowired
    private CiudadRepository ciudadRepository;

    @Transactional(readOnly = true)
    public List<ItemDto> ciudades(){
        return ciudadRepository.findAll().stream()
                .map(c -> new ItemDto(c.getIdCiudad(), c.getNombre()))
                .toList();
    }
    @Transactional(readOnly = true)
    public List<ItemDto> tiposActividades(){
        return tipoActividadRepository.findAll().stream()
                .map(d -> new ItemDto(d.getIdTipoActividad(), d.getNombre()))
                .toList();
    }
    @Transactional(readOnly = true)
    public List<ItemDto> destinos(){
        return destinoRepository.findAll().stream()
                .map(d -> new ItemDto(d.getIdDestino(), d.getNombre()))
                .toList();
    }
}
