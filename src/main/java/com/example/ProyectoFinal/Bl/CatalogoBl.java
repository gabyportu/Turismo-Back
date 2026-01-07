package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.CiudadRepository;
import com.example.ProyectoFinal.Dao.DepartamentoRepository;
import com.example.ProyectoFinal.Dao.DestinoRepository;
import com.example.ProyectoFinal.Dao.TipoActividadRepository;
import com.example.ProyectoFinal.Dto.CiudadDto;
import com.example.ProyectoFinal.Dto.DestinoDto;
import com.example.ProyectoFinal.Dto.ItemDto;
import com.example.ProyectoFinal.Dto.TipoActividadDto;
import com.example.ProyectoFinal.Entity.Ciudad;
import com.example.ProyectoFinal.Entity.Departamento;
import com.example.ProyectoFinal.Entity.Destino;
import com.example.ProyectoFinal.Entity.TipoActividad;
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
    @Autowired
    private DepartamentoRepository departamentoRepository;

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

    @Transactional(readOnly = true)
    public List<ItemDto> departamentos(){
        List<Departamento> dep = departamentoRepository.findAll();
        return dep.stream()
                .map(de -> new ItemDto(de.getIdDepartamento(), de.getNombre())).toList();
    }

    @Transactional
    public CiudadDto crearCiudad(CiudadDto dto) {

        Departamento departamento = departamentoRepository.findById(dto.getIdDepartamento())
                .orElseThrow(() -> new RuntimeException(
                        "Departamento no encontrado con ID: " + dto.getIdDepartamento()
                ));

        // Validación básica
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new RuntimeException("El nombre de la ciudad es obligatorio");
        }

        Ciudad ciudad = new Ciudad();
        ciudad.setDepartamento(departamento);
        ciudad.setNombre(dto.getNombre().trim());
        ciudad.setStatus(true);

        ciudad = ciudadRepository.save(ciudad);

        return new CiudadDto(
                ciudad.getIdCiudad(),
                departamento.getIdDepartamento(),
                ciudad.getNombre(),
                ciudad.getStatus()
        );
    }

    @Transactional
    public DestinoDto crearDestino(DestinoDto dto) {

        Ciudad ciudad = ciudadRepository.findById(dto.getIdCiudad())
                .orElseThrow(() -> new RuntimeException(
                        "Ciudad no encontrada con ID: " + dto.getIdCiudad()
                ));

        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new RuntimeException("El nombre del destino es obligatorio");
        }

        Destino destino = new Destino();
        destino.setCiudad(ciudad);
        destino.setNombre(dto.getNombre().trim());
        destino.setDescripcion(dto.getDescripcion());
        destino.setStatus(true);

        destino = destinoRepository.save(destino);

        return new DestinoDto(
                destino.getIdDestino(),
                ciudad.getIdCiudad(),
                destino.getNombre(),
                destino.getDescripcion(),
                destino.getStatus()
        );
    }

    @Transactional
    public TipoActividadDto crearTipoActividad(TipoActividadDto dto) {

        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new RuntimeException("El nombre de la actividad es obligatorio");
        }

        TipoActividad actividad = new TipoActividad();
        actividad.setNombre(dto.getNombre().trim());
        actividad.setStatus(true);

        actividad = tipoActividadRepository.save(actividad);

        return new TipoActividadDto(
                actividad.getIdTipoActividad(),
                actividad.getNombre(),
                actividad.getStatus()
        );
    }
}
