package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.EmpresaBl;
import com.example.ProyectoFinal.Bl.JwtBl;
import com.example.ProyectoFinal.Dto.EmpresaDetalleDto;
import com.example.ProyectoFinal.Dto.EmpresaDto;
import com.example.ProyectoFinal.Dto.RegistroEmpresaRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "*")
public class EmpresaApi {

    @Autowired
    private EmpresaBl empresaBl;
    @Autowired
    private JwtBl jwtBl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/registrar", consumes = "multipart/form-data")
    public ResponseEntity<EmpresaDto> registrarEmpresa(
            @RequestPart("data") String dataJson,
            @RequestPart(value = "logo", required = false) MultipartFile logo,
            @RequestPart(value = "documentos", required = false) List<MultipartFile> documentos,
            @RequestHeader(value = "Authorization", required = false) String authHeader
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
    public ResponseEntity<EmpresaDetalleDto> detalle(@PathVariable Integer idEmpresa,  @RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(null);
            }

            String token = authHeader.substring(7).trim();

            jwtBl.validateTokenAndRoles(token, "ROLE_TURISTA", "ROL_EMPRESA");

            return ResponseEntity.ok(empresaBl.obtenerDetalleEmpresa(idEmpresa));
        }catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }
    @GetMapping("/pendientes")
    public ResponseEntity<List<EmpresaDto>> listarPendientes(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(null);
            }

            String token = authHeader.substring(7).trim();

            jwtBl.validateTokenAndRoles(token, "ROLE_ADMIN");

            return ResponseEntity.ok(empresaBl.listarEmpresasPendientes());
        }catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }
}
