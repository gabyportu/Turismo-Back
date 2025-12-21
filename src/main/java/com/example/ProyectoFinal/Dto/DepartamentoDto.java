package com.example.ProyectoFinal.Dto;

public class DepartamentoDto {
    private Integer idDepartamento;
    private String nombre;
    private Boolean status;

    public DepartamentoDto() {}

    public DepartamentoDto(Integer idDepartamento, String nombre, Boolean status) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.status = status;
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
        return "DepartamentoDto{" +
                "idDepartamento=" + idDepartamento +
                ", nombre='" + nombre + '\'' +
                ", status=" + status +
                '}';
    }
}
