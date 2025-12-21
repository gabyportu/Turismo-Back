package com.example.ProyectoFinal.Dto;

public class DestinoDto {
    private Integer idDestino;
    private Integer idCiudad;
    private String nombre;
    private String descripcion;
    private Boolean status;

    public DestinoDto() {}

    public DestinoDto(Integer idDestino, Integer idCiudad, String nombre, String descripcion, Boolean status) {
        this.idDestino = idDestino;
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.status = status;
    }

    public Integer getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DestinoDto{" +
                "idDestino=" + idDestino +
                ", idCiudad=" + idCiudad +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", status=" + status +
                '}';
    }
}
