package com.example.ProyectoFinal.Entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DEPARTAMENTO")
    private Integer idDepartamento;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    public Departamento() {}

    public Departamento(Integer idDepartamento, String nombre, Boolean status) {
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
        return "Departamento{" +
                "idDepartamento=" + idDepartamento +
                ", nombre='" + nombre + '\'' +
                ", status=" + status +
                '}';
    }
}
