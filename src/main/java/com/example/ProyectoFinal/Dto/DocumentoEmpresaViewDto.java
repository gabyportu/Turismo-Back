package com.example.ProyectoFinal.Dto;

public class DocumentoEmpresaViewDto {
    private Integer idDocumentoEmpresa;
    private Integer idEmpresa;
    private String objectName;   // lo que guardas en DB
    private String url;          // URL firmada para descargar/ver
    private Boolean status;

    public Integer getIdDocumentoEmpresa() { return idDocumentoEmpresa; }
    public void setIdDocumentoEmpresa(Integer idDocumentoEmpresa) { this.idDocumentoEmpresa = idDocumentoEmpresa; }

    public Integer getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Integer idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getObjectName() { return objectName; }
    public void setObjectName(String objectName) { this.objectName = objectName; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}
