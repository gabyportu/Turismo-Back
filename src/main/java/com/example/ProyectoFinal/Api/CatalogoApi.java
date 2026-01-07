package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.CatalogoBl;
import com.example.ProyectoFinal.Dto.CiudadDto;
import com.example.ProyectoFinal.Dto.DestinoDto;
import com.example.ProyectoFinal.Dto.TipoActividadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalogos")
@CrossOrigin("*")
public class CatalogoApi {
    @Autowired
    private CatalogoBl catalogoBl;

    @GetMapping("/ciudades")
    public ResponseEntity<?> ciudades(){
        return ResponseEntity.ok(catalogoBl.ciudades());
    }

    @GetMapping("/destinos")
    public ResponseEntity<?> destinos(){
        return ResponseEntity.ok(catalogoBl.destinos());
    }
    @GetMapping("/actividades")
    public ResponseEntity<?> actividades(){
        return ResponseEntity.ok(catalogoBl.tiposActividades());
    }
    @GetMapping("/departamentos")
    public ResponseEntity<?> departamentos(){
        return ResponseEntity.ok(catalogoBl.departamentos());
    }

    @PostMapping("/crearCiudades")
    public ResponseEntity<?> crearCiudad(
            @RequestBody CiudadDto dto) {
        return ResponseEntity.ok(catalogoBl.crearCiudad(dto));
    }
    @PostMapping("/crearDestinos")
    public ResponseEntity<?> crearDestino(
            @RequestBody DestinoDto dto){
        return ResponseEntity.ok(catalogoBl.crearDestino(dto));
    }
    @PostMapping("/crearActividad")
    public ResponseEntity<?> crearActividad(
            @RequestBody TipoActividadDto dto){
        return ResponseEntity.ok(catalogoBl.crearTipoActividad(dto));
    }
}
