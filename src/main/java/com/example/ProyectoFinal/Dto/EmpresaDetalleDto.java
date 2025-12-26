package com.example.ProyectoFinal.Dto;

import java.util.List;

public class EmpresaDetalleDto {
    private EmpresaDto empresa;
    private RepresentanteDto representante;
    private UsuarioDto usuario;
    private String logoObjectName;
    private String logoUrl;
    private List<DocumentoEmpresaViewDto> documentos;

    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public RepresentanteDto getRepresentante() {
        return representante;
    }

    public void setRepresentante(RepresentanteDto representante) {
        this.representante = representante;
    }

    public String getLogoObjectName() {
        return logoObjectName;
    }

    public void setLogoObjectName(String logoObjectName) {
        this.logoObjectName = logoObjectName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<DocumentoEmpresaViewDto> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoEmpresaViewDto> documentos) {
        this.documentos = documentos;
    }
}
