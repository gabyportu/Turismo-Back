package com.example.ProyectoFinal.Dto;

public class MultimediaItemDto {
    private Integer idMultimediaOferta;
    private String objectName; // lo que guardaste en BD (ruta en minio)
    private String url;        // presigned url
    private Boolean status;

    public Integer getIdMultimediaOferta() { return idMultimediaOferta; }
    public void setIdMultimediaOferta(Integer idMultimediaOferta) { this.idMultimediaOferta = idMultimediaOferta; }

    public String getObjectName() { return objectName; }
    public void setObjectName(String objectName) { this.objectName = objectName; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}
