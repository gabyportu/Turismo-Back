package com.example.ProyectoFinal.Dto;

public class TipoActividadDto {
    private Integer idTipoActividad;
    private String nombre;
    private Boolean status;

    public TipoActividadDto() {}

    public TipoActividadDto(Integer idTipoActividad, String nombre, Boolean status) {
        this.idTipoActividad = idTipoActividad;
        this.nombre = nombre;
        this.status = status;
    }

    public Integer getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(Integer idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TipoActividadDto{" +
                "idTipoActividad=" + idTipoActividad +
                ", nombre='" + nombre + '\'' +
                ", status=" + status +
                '}';
    }
}
