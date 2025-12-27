package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.JwtBl;
import com.example.ProyectoFinal.Bl.OfertaBl;
import com.example.ProyectoFinal.Dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/oferta")
@CrossOrigin(origins = "*")
public class OfertaApi {
    @Autowired
    private OfertaBl ofertaBl;
    @Autowired
    private JwtBl jwtBl;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @PostMapping(value = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<OfertaDto> crearOferta(
            @RequestPart("data") String dataJson,
            @RequestPart(value = "multimedia", required = false) List<MultipartFile> multimedia,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ) throws Exception {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(null);
            }

            String token = authHeader.substring(7).trim();

            jwtBl.validateTokenAndRoles(token, "ROLE_EMPRESA");
            CrearOfertaRequestDto req = objectMapper.readValue(dataJson, CrearOfertaRequestDto.class);

            OfertaDto res = ofertaBl.crearOferta(
                    req.getOferta(),
                    req.getDestinos(),
                    req.getActividades(),
                    multimedia
            );
            return ResponseEntity.ok(res);
        }catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }


    @PutMapping(value = "/editar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> editarOferta(
            @RequestPart("data") String dataJson,
            @RequestPart(value = "multimedia", required = false) List<MultipartFile> multimedia,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("TOKEN_NO_ENVIADO");
            }

            String token = authHeader.substring(7).trim();
            jwtBl.validateTokenAndRoles(token, "ROLE_EMPRESA");

            EditarOfertaRequestDto req = objectMapper.readValue(dataJson, EditarOfertaRequestDto.class);

            OfertaDto res = ofertaBl.editarOferta(
                    req.getOferta(),
                    req.getDestinos(),
                    req.getActividades(),
                    multimedia
            );

            return ResponseEntity.ok(res);

        } catch (org.springframework.security.access.AccessDeniedException e) {
            return ResponseEntity.status(403).body("ROL_NO_AUTORIZADO");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR_INTERNO: " + e.getMessage());
        }
    }


    @GetMapping("/detalle/{idOferta}")
    public ResponseEntity<OfertaDetalleDto> detalle(@PathVariable Integer idOferta, @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(null);
            }
            String token = authHeader.substring(7).trim();

            jwtBl.validateTokenAndRoles(token, "ROLE_EMPRESA", "ROLE_TURISTA", "ROLE_ADMIN");
            return ResponseEntity.ok(ofertaBl.obtenerDetalleOferta(idOferta));
        }catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }
    @GetMapping("/aprobadas")
    public ResponseEntity<List<OfertaRankingDto>> listarOfertasAprobadas() {
        return ResponseEntity.ok(
                ofertaBl.listarOfertasAprobadas()
        );
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<OfertaDto>> listarPendientes(
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(null);
            }
            String token = authHeader.substring(7).trim();

            jwtBl.validateTokenAndRoles(token, "ROLE_ADMIN");
            return ResponseEntity.ok(ofertaBl.listarOfertasPendientes());
        }catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }
    @GetMapping("/mejor-puntuadas")
    public ResponseEntity<List<OfertaRankingDto>> listarMejorPuntuadas() {
        return ResponseEntity.ok(
                ofertaBl.listarOfertasAprobadasMejorPuntuadas()
        );
    }

    @GetMapping("/empresa/listado/{idEmpresa}")
    public ResponseEntity<List<OfertaListadoDto>> listadoPorEmpresa(
            @PathVariable Integer idEmpresa,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ){
        try{
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(null);
            }
            String token = authHeader.substring(7).trim();

            jwtBl.validateTokenAndRoles(token, "ROLE_ADMIN", "ROLE_TURISTA", "ROLE_EMPRESA");

            return ResponseEntity.ok(ofertaBl.listarOfertasEmpresaAprobadas(idEmpresa));
        }catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }
    @GetMapping("/empresa/listado-apro-pend/{idEmpresa}")
    public ResponseEntity<List<OfertaListadoDto>> listadoPorEmpresaPendApro(
            @PathVariable Integer idEmpresa,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ){
        try{
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(null);
            }
            String token = authHeader.substring(7).trim();

            jwtBl.validateTokenAndRoles(token, "ROLE_EMPRESA");

            return ResponseEntity.ok(ofertaBl.listarOfertasEmpresaConImagen(idEmpresa));
        }catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }
}
