package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.CatalogoBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
