package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.CiudadBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ciudades")
@CrossOrigin("*")
public class CiudadApi {

    @Autowired
    private CiudadBl ciudadBl;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(ciudadBl.listarCiudades());
    }
}
