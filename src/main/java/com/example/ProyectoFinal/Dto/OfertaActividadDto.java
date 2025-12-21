package com.example.ProyectoFinal.Dto;

public class OfertaActividadDto {
    private Integer idOfertaActividad;
    private Integer idOferta;
    private Integer idTipoActividad;

    public OfertaActividadDto() {}

    public OfertaActividadDto(Integer idOfertaActividad, Integer idOferta, Integer idTipoActividad) {
        this.idOfertaActividad = idOfertaActividad;
        this.idOferta = idOferta;
        this.idTipoActividad = idTipoActividad;
    }

    public Integer getIdOfertaActividad() {
        return idOfertaActividad;
    }

    public void setIdOfertaActividad(Integer idOfertaActividad) {
        this.idOfertaActividad = idOfertaActividad;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public Integer getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(Integer idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    @Override
    public String toString() {
        return "OfertaActividadDto{" +
                "idOfertaActividad=" + idOfertaActividad +
                ", idOferta=" + idOferta +
                ", idTipoActividad=" + idTipoActividad +
                '}';
    }
}
