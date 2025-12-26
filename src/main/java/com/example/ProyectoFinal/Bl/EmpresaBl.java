package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.*;
import com.example.ProyectoFinal.Dto.*;
import com.example.ProyectoFinal.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EmpresaBl {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private EmpresaRepository empresaRepository;
    @Autowired private RepresentanteRepository representanteRepository;
    @Autowired private CiudadRepository ciudadRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private MinioBl minioBl;
    @Autowired private DocumentoEmpresaRepository documentoEmpresaRepository;
    @Autowired private EmailSenderBl emailSenderBl;

    @Transactional
    public EmpresaDto registrarEmpresa(
            UsuarioDto usuarioDto,
            EmpresaDto empresaDto,
            RepresentanteDto representanteDto,
            MultipartFile logo,
            List<MultipartFile> documentos
    ) {
        try {
            // Validaciones
            if (empresaRepository.existsByNit(empresaDto.getNit())) {
                throw new RuntimeException("Ya existe una empresa con ese NIT");
            }
            if (usuarioRepository.findByCorreo(usuarioDto.getCorreo()).isPresent()) {
                throw new RuntimeException("Ya existe una empresa con ese correo");
            }

            // 1) Usuario
            Usuario usuario = new Usuario();
            usuario.setNombres(usuarioDto.getNombres());
            usuario.setApellidoPaterno(usuarioDto.getApellidoPaterno());
            usuario.setApellidoMaterno(usuarioDto.getApellidoMaterno());
            usuario.setFechaNacimiento(usuarioDto.getFechaNacimiento());
            usuario.setGenero(usuarioDto.getGenero());
            usuario.setTelefono(usuarioDto.getTelefono());
            usuario.setCorreo(usuarioDto.getCorreo());
            usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
            usuario.setStatus(true);
            usuario.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            usuario = usuarioRepository.save(usuario);

            // 2) Ciudad
            Ciudad ciudad = ciudadRepository.findById(empresaDto.getIdCiudad())
                    .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con ID: " + empresaDto.getIdCiudad()));

            // 3) Empresa (primero guardamos para obtener ID)
            Empresa empresa = new Empresa();
            empresa.setCiudad(ciudad);
            empresa.setNombre(empresaDto.getNombre());
            empresa.setNit(empresaDto.getNit());
            empresa.setDescripcion(empresaDto.getDescripcion());
            empresa.setFacebook(empresaDto.getFacebook());
            empresa.setInstagram(empresaDto.getInstagram());
            empresa.setEstado("PENDIENTE");
            empresa.setStatus(true);
            empresa.setLogoUrl(null);
            empresa = empresaRepository.save(empresa);

            // 4) Logo (1 archivo)
            if (logo != null && !logo.isEmpty()) {
                String folderLogo = "empresas/" + empresa.getIdEmpresa() + "/logo";
                String logoObjectName = minioBl.upload(logo, folderLogo);
                empresa.setLogoUrl(logoObjectName);
                empresa = empresaRepository.save(empresa); // actualizar logoUrl
            }

            // 5) Documentos (varios archivos)
            if (documentos != null && !documentos.isEmpty()) {
                String folderDocs = "empresas/" + empresa.getIdEmpresa() + "/docs";

                for (MultipartFile doc : documentos) {
                    if (doc == null || doc.isEmpty()) continue;

                    String docObjectName = minioBl.upload(doc, folderDocs);

                    DocumentoEmpresa de = new DocumentoEmpresa();
                    de.setEmpresa(empresa);
                    de.setDocumento(docObjectName);
                    de.setStatus(true);
                    documentoEmpresaRepository.save(de);
                }
            }

            // 6) Representante
            Representante representante = new Representante();
            representante.setUsuario(usuario);
            representante.setEmpresa(empresa);
            representante.setNumeroDocumento(representanteDto.getNumeroDocumento());
            representante.setExtension(representanteDto.getExtension());
            representante.setStatus(true);
            representanteRepository.save(representante);

            // 7) Respuesta
            EmpresaDto res = new EmpresaDto();
            res.setIdEmpresa(empresa.getIdEmpresa());
            res.setIdCiudad(ciudad.getIdCiudad());
            res.setNombre(empresa.getNombre());
            res.setNit(empresa.getNit());
            res.setDescripcion(empresa.getDescripcion());
            res.setFacebook(empresa.getFacebook());
            res.setInstagram(empresa.getInstagram());
            res.setLogoURL(empresa.getLogoUrl());
            res.setEstado(empresa.getEstado());
            res.setStatus(empresa.getStatus());

            emailSenderBl.sendEmail("proyectoturismoportugal@gmail.com", "registro exitoso", "Estimado usuario comentarle que el registro de la empresa fue existoso y esta a espera de aprobarse. ");
            
            return res;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al registrar la empresa: " + e.getMessage());
        }
    }

    @Transactional
    public void aprobarEmpresa(Integer idEmpresa) {
        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        empresa.setEstado("APROBADO");
        empresa.setStatus(true);
        empresaRepository.save(empresa);
    }

    @Transactional
    public void rechazarEmpresa(Integer idEmpresa) {
        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        empresa.setEstado("RECHAZADO");
        empresa.setStatus(false);
        empresaRepository.save(empresa);
    }
    @Transactional(readOnly = true)
    public EmpresaDetalleDto obtenerDetalleEmpresa(Integer idEmpresa) {

        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + idEmpresa));

        // EmpresaDto
        EmpresaDto empresaDto = new EmpresaDto();
        empresaDto.setIdEmpresa(empresa.getIdEmpresa());
        empresaDto.setIdCiudad(empresa.getCiudad().getIdCiudad());
        empresaDto.setNombre(empresa.getNombre());
        empresaDto.setNit(empresa.getNit());
        empresaDto.setDescripcion(empresa.getDescripcion());
        empresaDto.setFacebook(empresa.getFacebook());
        empresaDto.setInstagram(empresa.getInstagram());
        empresaDto.setLogoURL(empresa.getLogoUrl());
        empresaDto.setEstado(empresa.getEstado());
        empresaDto.setStatus(empresa.getStatus());

        // Representante
        Representante representante = representanteRepository.findByEmpresa_IdEmpresa(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Representante no encontrado para la empresa"));

        RepresentanteDto repDto = new RepresentanteDto();
        repDto.setIdRepresentante(representante.getIdRepresentante());
        repDto.setIdEmpresa(idEmpresa);
        repDto.setIdUsuario(representante.getUsuario().getIdUsuario());
        repDto.setNumeroDocumento(representante.getNumeroDocumento());
        repDto.setExtension(representante.getExtension());
        repDto.setStatus(representante.getStatus());

        Usuario usuario = representante.getUsuario();

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(representante.getUsuario().getIdUsuario());
        usuarioDto.setNombres(usuario.getNombres());
        usuarioDto.setApellidoPaterno(usuario.getApellidoPaterno());
        usuarioDto.setApellidoMaterno(usuario.getApellidoMaterno());
        usuarioDto.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioDto.setGenero(usuario.getGenero());
        usuarioDto.setTelefono(usuario.getTelefono());
        usuario.setCorreo(usuario.getCorreo());

        // Documentos desde DB -> URL MinIO
        List<DocumentoEmpresaViewDto> docs = documentoEmpresaRepository
                .findByEmpresa_IdEmpresa(idEmpresa)
                .stream()
                .filter(d -> Boolean.TRUE.equals(d.getStatus()))
                .map(d -> {
                    DocumentoEmpresaViewDto dto = new DocumentoEmpresaViewDto();
                    dto.setIdDocumentoEmpresa(d.getIdDocumentoEmpresa());
                    dto.setIdEmpresa(idEmpresa);
                    dto.setObjectName(d.getDocumento());
                    dto.setStatus(d.getStatus());

                    dto.setUrl(minioBl.presignedGetUrl(d.getDocumento(), 60));
                    return dto;
                })
                .toList();

        // Logo URL (si existe)
        String logoObjectName = empresa.getLogoUrl();
        String logoUrl = null;
        if (logoObjectName != null && !logoObjectName.isBlank()) {
            logoUrl = minioBl.presignedGetUrl(logoObjectName, 60 * 24);
        }

        // Respuesta final
        EmpresaDetalleDto res = new EmpresaDetalleDto();
        res.setEmpresa(empresaDto);
        res.setRepresentante(repDto);
        res.setUsuario(usuarioDto);
        res.setLogoObjectName(logoObjectName);
        res.setLogoUrl(logoUrl);
        res.setDocumentos(docs);

        return res;
    }
    @Transactional(readOnly = true)
    public List<EmpresaDto> listarEmpresasPendientes() {

        List<Empresa> empresas =
                empresaRepository.findByEstadoAndStatus("PENDIENTE", true);

        return empresas.stream().map(empresa -> {
            EmpresaDto dto = new EmpresaDto();
            dto.setIdEmpresa(empresa.getIdEmpresa());
            dto.setIdCiudad(empresa.getCiudad().getIdCiudad());
            dto.setNombre(empresa.getNombre());
            dto.setNit(empresa.getNit());
            dto.setDescripcion(empresa.getDescripcion());
            dto.setFacebook(empresa.getFacebook());
            dto.setInstagram(empresa.getInstagram());
            dto.setLogoURL(empresa.getLogoUrl());
            dto.setEstado(empresa.getEstado());
            dto.setStatus(empresa.getStatus());
            return dto;
        }).toList();
    }

}
