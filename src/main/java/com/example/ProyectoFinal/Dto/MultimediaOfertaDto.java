package com.example.ProyectoFinal.Dto;

public class MultimediaOfertaDto {
    private Integer idMultimediaOferta;
    private Integer idOferta;
    private String multimedia;
    private Boolean status;

    public MultimediaOfertaDto() {}

    public MultimediaOfertaDto(Integer idMultimediaOferta, Integer idOferta, String multimedia, Boolean status) {
        this.idMultimediaOferta = idMultimediaOferta;
        this.idOferta = idOferta;
        this.multimedia = multimedia;
        this.status = status;
    }

    public Integer getIdMultimediaOferta() {
        return idMultimediaOferta;
    }

    public void setIdMultimediaOferta(Integer idMultimediaOferta) {
        this.idMultimediaOferta = idMultimediaOferta;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MultimediaOfertaDto{" +
                "idMultimediaOferta=" + idMultimediaOferta +
                ", idOferta=" + idOferta +
                ", multimedia='" + multimedia + '\'' +
                ", status=" + status +
                '}';
    }
}
