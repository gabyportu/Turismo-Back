package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.EmpresaBl;
import com.example.ProyectoFinal.Dto.EmpresaDetalleDto;
import com.example.ProyectoFinal.Dto.EmpresaDto;
import com.example.ProyectoFinal.Dto.RegistroEmpresaRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "*")
public class EmpresaApi {

    @Autowired
    private EmpresaBl empresaBl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/registrar", consumes = "multipart/form-data")
    public ResponseEntity<EmpresaDto> registrarEmpresa(
            @RequestPart("data") String dataJson,
            @RequestPart(value = "logo", required = false) MultipartFile logo,
            @RequestPart(value = "documentos", required = false) List<MultipartFile> documentos
    ) throws Exception {

        RegistroEmpresaRequestDto req =
                objectMapper.readValue(dataJson, RegistroEmpresaRequestDto.class);

        EmpresaDto res = empresaBl.registrarEmpresa(
                req.getUsuario(),
                req.getEmpresa(),
                req.getRepresentante(),
                logo,
                documentos
        );

        return ResponseEntity.ok(res);
    }
    @GetMapping("/detalle/{idEmpresa}")
    public ResponseEntity<EmpresaDetalleDto> detalle(@PathVariable Integer idEmpresa) {
        return ResponseEntity.ok(empresaBl.obtenerDetalleEmpresa(idEmpresa));
    }
    @GetMapping("/pendientes")
    public ResponseEntity<List<EmpresaDto>> listarPendientes() {
        return ResponseEntity.ok(empresaBl.listarEmpresasPendientes());
    }

}
