package com.example.ProyectoFinal.Dto;

public class DocumentoEmpresaDto {
    private Integer idDocumentoEmpresa;
    private Integer idEmpresa;
    private String documento;
    private Boolean status;

    public DocumentoEmpresaDto() {}

    public DocumentoEmpresaDto(Integer idDocumentoEmpresa, Integer idEmpresa, String documento, Boolean status) {
        this.idDocumentoEmpresa = idDocumentoEmpresa;
        this.idEmpresa = idEmpresa;
        this.documento = documento;
        this.status = status;
    }

    public Integer getIdDocumentoEmpresa() {
        return idDocumentoEmpresa;
    }

    public void setIdDocumentoEmpresa(Integer idDocumentoEmpresa) {
        this.idDocumentoEmpresa = idDocumentoEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DocumentoEmpresaDto{" +
                "idDocumentoEmpresa=" + idDocumentoEmpresa +
                ", idEmpresa=" + idEmpresa +
                ", documento='" + documento + '\'' +
                ", status=" + status +
                '}';
    }
}
