package com.example.ProyectoFinal.Dto;

public class CiudadDto {
    private Integer idCiudad;
    private Integer idDepartamento;
    private String nombre;
    private Boolean status;

    public CiudadDto() {}

    public CiudadDto(Integer idCiudad, Integer idDepartamento, String nombre, Boolean status) {
        this.idCiudad = idCiudad;
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.status = status;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
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
        return "CiudadDto{" +
                "idCiudad=" + idCiudad +
                ", idDepartamento=" + idDepartamento +
                ", nombre='" + nombre + '\'' +
                ", status=" + status +
                '}';
    }
}
