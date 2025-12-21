package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.*;
import com.example.ProyectoFinal.Dto.MultimediaItemDto;
import com.example.ProyectoFinal.Dto.OfertaDetalleDto;
import com.example.ProyectoFinal.Dto.OfertaDto;
import com.example.ProyectoFinal.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OfertaBl {

    @Autowired
    private OfertaRepository ofertaRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private DestinoRepository destinoRepository;
    @Autowired
    private TipoActividadRepository tipoActividadRepository;
    @Autowired
    private OfertaDestinoRepository ofertaDestinoRepository;
    @Autowired
    private OfertaActividadRepository ofertaActividadRepository;
    @Autowired
    private MultimediaOfertaRepository multimediaOfertaRepository;
    @Autowired
    private MinioBl minioBl;

    @Transactional
    public OfertaDto crearOferta(OfertaDto ofertaDto,
                                 List<Integer> destinos,
                                 List<Integer> actividades,
                                 List<MultipartFile> multimedia) {
        try{
            Empresa empresa = empresaRepository.findById(ofertaDto.getIdEmpresa())
                    .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + ofertaDto.getIdEmpresa()));

            Oferta oferta = new Oferta();
            oferta.setEmpresa(empresa);
            oferta.setTitulo(ofertaDto.getTitulo());
            oferta.setDescripcion(ofertaDto.getDescripcion());
            oferta.setPrecio(ofertaDto.getPrecio());

            if(ofertaDto.getFechaInicio() != null){
                oferta.setFechaInicio(new java.sql.Date(ofertaDto.getFechaInicio().getTime()));
            }
            if(ofertaDto.getFechaFin() != null){
                oferta.setFechaFin(new java.sql.Date(ofertaDto.getFechaFin().getTime()));
            }
            oferta.setDetalles(ofertaDto.getDetalles());
            oferta.setEstado("PENDIENTE");
            oferta.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            oferta.setStatus(true);
            oferta = ofertaRepository.save(oferta);

            if(destinos != null){
                for(Integer idDestino: destinos){
                    Destino destino = destinoRepository.findById(idDestino)
                            .orElseThrow(() -> new RuntimeException("Destino no encontrada con ID: " + idDestino));
                    OfertaDestino od = new OfertaDestino();
                    od.setOferta(oferta);
                    od.setDestino(destino);
                    ofertaDestinoRepository.save(od);
                }
            }
            if(actividades != null){
                for(Integer idActividad: actividades){
                    TipoActividad ta = tipoActividadRepository.findById(idActividad)
                            .orElseThrow(() -> new RuntimeException("Tipo de Actividad no encontrado con ID: " + idActividad));
                    OfertaActividad oa = new OfertaActividad();
                    oa.setOferta(oferta);
                    oa.setTipoActividad(ta);
                    ofertaActividadRepository.save(oa);
                }
            }
            if (multimedia != null && !multimedia.isEmpty()) {
                String prefix = "ofertas/" + oferta.getIdOferta() + "/multimedia";

                for (MultipartFile file : multimedia) {
                    if (file == null || file.isEmpty()) continue;

                    String objectName = minioBl.upload(file, prefix);

                    MultimediaOferta mo = new MultimediaOferta();
                    mo.setOferta(oferta);
                    mo.setMultimedia(objectName);
                    mo.setStatus(true);
                    multimediaOfertaRepository.save(mo);
                }
            }
            OfertaDto res = new OfertaDto();
            res.setIdOferta(oferta.getIdOferta());
            res.setIdEmpresa(empresa.getIdEmpresa());
            res.setTitulo(oferta.getTitulo());
            res.setDescripcion(oferta.getDescripcion());
            res.setPrecio(oferta.getPrecio());
            res.setFechaInicio(oferta.getFechaInicio());
            res.setFechaFin(oferta.getFechaFin());
            res.setDetalles(oferta.getDetalles());
            res.setEstado(oferta.getEstado());
            res.setFechaCreacion(oferta.getFechaCreacion());
            res.setStatus(oferta.getStatus());

            return res;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al crear oferta: " + e.getMessage());
        }
    }

    @Transactional
    public OfertaDto editarOferta(OfertaDto ofertaDto,
                                  List<Integer> destinos,
                                  List<Integer> actividades,
                                  List<MultipartFile> nuevaMultimedia) {
        try {
            if (ofertaDto.getIdOferta() == null) {
                throw new RuntimeException("idOferta es requerido para editar");
            }

            Oferta oferta = ofertaRepository.findById(ofertaDto.getIdOferta())
                    .orElseThrow(() -> new RuntimeException("Oferta no encontrada con ID: " + ofertaDto.getIdOferta()));

            if (Boolean.FALSE.equals(oferta.getStatus())) {
                throw new RuntimeException("La oferta está inactiva/eliminada");
            }

            if (ofertaDto.getTitulo() != null) oferta.setTitulo(ofertaDto.getTitulo());
            if (ofertaDto.getDescripcion() != null) oferta.setDescripcion(ofertaDto.getDescripcion());
            if (ofertaDto.getPrecio() != null) oferta.setPrecio(ofertaDto.getPrecio());
            if (ofertaDto.getDetalles() != null) oferta.setDetalles(ofertaDto.getDetalles());

            if (ofertaDto.getFechaInicio() != null) {
                oferta.setFechaInicio(new java.sql.Date(ofertaDto.getFechaInicio().getTime()));
            }
            if (ofertaDto.getFechaFin() != null) {
                oferta.setFechaFin(new java.sql.Date(ofertaDto.getFechaFin().getTime()));
            }

            oferta = ofertaRepository.save(oferta);

            if (destinos != null) {
                ofertaDestinoRepository.deleteByOferta_IdOferta(oferta.getIdOferta());
                for (Integer idDestino : destinos) {
                    Destino destino = destinoRepository.findById(idDestino)
                            .orElseThrow(() -> new RuntimeException("Destino no encontrado con ID: " + idDestino));

                    OfertaDestino od = new OfertaDestino();
                    od.setOferta(oferta);
                    od.setDestino(destino);
                    ofertaDestinoRepository.save(od);
                }
            }

            if (actividades != null) {
                ofertaActividadRepository.deleteByOferta_IdOferta(oferta.getIdOferta());
                for (Integer idActividad : actividades) {
                    TipoActividad ta = tipoActividadRepository.findById(idActividad)
                            .orElseThrow(() -> new RuntimeException("Tipo de Actividad no encontrado con ID: " + idActividad));

                    OfertaActividad oa = new OfertaActividad();
                    oa.setOferta(oferta);
                    oa.setTipoActividad(ta);
                    ofertaActividadRepository.save(oa);
                }
            }

            if (nuevaMultimedia != null && !nuevaMultimedia.isEmpty()) {
                String prefix = "ofertas/" + oferta.getIdOferta() + "/multimedia";

                for (MultipartFile file : nuevaMultimedia) {
                    if (file == null || file.isEmpty()) continue;

                    String objectName = minioBl.upload(file, prefix);

                    MultimediaOferta mo = new MultimediaOferta();
                    mo.setOferta(oferta);
                    mo.setMultimedia(objectName);
                    mo.setStatus(true);
                    multimediaOfertaRepository.save(mo);
                }
            }

            OfertaDto res = new OfertaDto();
            res.setIdOferta(oferta.getIdOferta());
            res.setIdEmpresa(oferta.getEmpresa().getIdEmpresa());
            res.setTitulo(oferta.getTitulo());
            res.setDescripcion(oferta.getDescripcion());
            res.setPrecio(oferta.getPrecio());
            res.setFechaInicio(oferta.getFechaInicio());
            res.setFechaFin(oferta.getFechaFin());
            res.setDetalles(oferta.getDetalles());
            res.setEstado(oferta.getEstado());
            res.setFechaCreacion(oferta.getFechaCreacion());
            res.setStatus(oferta.getStatus());
            return res;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al editar oferta: " + e.getMessage());
        }
    }


    @Transactional
    public void aprobarOferta(Integer idOferta){
        Oferta oferta = ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrado con ID: " + idOferta));
        oferta.setEstado("APROBADO");
        oferta.setStatus(true);
        ofertaRepository.save(oferta);
    }

    @Transactional
    public void rechazarOferta(Integer idOferta){
        Oferta oferta = ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrado con ID: " + idOferta));
        oferta.setEstado("RECHAZADO");
        oferta.setStatus(false);
        ofertaRepository.save(oferta);
    }

    @Transactional(readOnly = true)
    public OfertaDetalleDto obtenerDetalleOferta(Integer idOferta) {

        Oferta oferta = ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada con ID: " + idOferta));

        /*if (Boolean.FALSE.equals(oferta.getStatus())) {
            throw new RuntimeException("La oferta está eliminada/inactiva");
        }*/

        // Oferta -> OfertaDto
        OfertaDto ofertaDto = new OfertaDto();
        ofertaDto.setIdOferta(oferta.getIdOferta());
        ofertaDto.setIdEmpresa(oferta.getEmpresa().getIdEmpresa());
        ofertaDto.setTitulo(oferta.getTitulo());
        ofertaDto.setDescripcion(oferta.getDescripcion());
        ofertaDto.setPrecio(oferta.getPrecio());
        ofertaDto.setFechaInicio(oferta.getFechaInicio());
        ofertaDto.setFechaFin(oferta.getFechaFin());
        ofertaDto.setDetalles(oferta.getDetalles());
        ofertaDto.setEstado(oferta.getEstado());
        ofertaDto.setFechaCreacion(oferta.getFechaCreacion());
        ofertaDto.setStatus(oferta.getStatus());

        // Destinos (ids)
        List<Integer> destinos = ofertaDestinoRepository.findByOferta_IdOferta(idOferta)
                .stream()
                .map(od -> od.getDestino().getIdDestino())
                .toList();

        // Actividades (ids)
        List<Integer> actividades = ofertaActividadRepository.findByOferta_IdOferta(idOferta)
                .stream()
                .map(oa -> oa.getTipoActividad().getIdTipoActividad())
                .toList();

        // Multimedia -> generar URL firmada
        int minutes = 60*24; // ejemplo: 30 min
        List<MultimediaItemDto> multimedia = multimediaOfertaRepository
                .findByOferta_IdOfertaAndStatusTrue(idOferta)
                .stream()
                .map(mo -> {
                    MultimediaItemDto item = new MultimediaItemDto();
                    item.setIdMultimediaOferta(mo.getIdMultimediaOferta());
                    item.setObjectName(mo.getMultimedia());
                    item.setStatus(mo.getStatus());

                    // URL firmada de MinIO (si falla conexión, revisa minio.url)
                    String url = minioBl.presignedGetUrl(mo.getMultimedia(), minutes);
                    item.setUrl(url);

                    return item;
                })
                .toList();

        OfertaDetalleDto res = new OfertaDetalleDto();
        res.setOferta(ofertaDto);
        res.setDestinos(destinos);
        res.setActividades(actividades);
        res.setMultimedia(multimedia);

        return res;
    }
    @Transactional(readOnly = true)
    public List<OfertaDto> listarOfertasAprobadas() {

        return ofertaRepository
                .findByEstadoAndStatus("APROBADO", true)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<OfertaDto> listarOfertasPendientes() {

        return ofertaRepository
                .findByEstadoAndStatus("PENDIENTE", true)
                .stream()
                .map(this::mapToDto)
                .toList();
    }
    private OfertaDto mapToDto(Oferta oferta) {
        OfertaDto dto = new OfertaDto();
        dto.setIdOferta(oferta.getIdOferta());
        dto.setIdEmpresa(oferta.getEmpresa().getIdEmpresa());
        dto.setTitulo(oferta.getTitulo());
        dto.setDescripcion(oferta.getDescripcion());
        dto.setPrecio(oferta.getPrecio());
        dto.setFechaInicio(oferta.getFechaInicio());
        dto.setFechaFin(oferta.getFechaFin());
        dto.setDetalles(oferta.getDetalles());
        dto.setEstado(oferta.getEstado());
        dto.setFechaCreacion(oferta.getFechaCreacion());
        dto.setStatus(oferta.getStatus());
        return dto;
    }
}
