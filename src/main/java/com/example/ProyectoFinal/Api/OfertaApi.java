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

            jwtBl.validateTokenAndRoles(token, "ROL_EMPRESA");
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


    @PutMapping(value = "/editar", consumes = "multipart/form-data")
    public ResponseEntity<OfertaDto> editarOferta(
            @RequestPart("data") EditarOfertaRequestDto req,
            @RequestPart(value = "multimedia", required = false) List<MultipartFile> multimedia,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ){
        OfertaDto res = ofertaBl.editarOferta(
                req.getOferta(),
                req.getDestinos(),
                req.getActividades(),
                multimedia
        );
        return ResponseEntity.ok(res);
    }

    @GetMapping("/detalle/{idOferta}")
    public ResponseEntity<OfertaDetalleDto> detalle(@PathVariable Integer idOferta) {
        return ResponseEntity.ok(ofertaBl.obtenerDetalleOferta(idOferta));
    }
    @GetMapping("/aprobadas")
    public ResponseEntity<List<OfertaDto>> listarAprobadas() {
        return ResponseEntity.ok(ofertaBl.listarOfertasAprobadas());
    }
    @GetMapping("/pendientes")
    public ResponseEntity<List<OfertaDto>> listarPendientes() {
        return ResponseEntity.ok(ofertaBl.listarOfertasPendientes());
    }
    @GetMapping("mejor-puntuadas")
    public ResponseEntity<List<OfertaRankingDto>> mejorPuntuadas(){
        return ResponseEntity.ok(
                ofertaBl.obtenerOfertasMejorPuntuadas()
        );
    }
}
