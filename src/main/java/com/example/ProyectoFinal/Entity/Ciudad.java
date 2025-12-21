package com.example.ProyectoFinal.Entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "CIUDAD")
public class Ciudad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CIUDAD")
    private Integer idCiudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DEPARTAMENTO", nullable = false)
    private Departamento departamento;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    public Ciudad() {}

    public Ciudad(Integer idCiudad, String nombre, Departamento departamento, Boolean status) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.departamento = departamento;
        this.status = status;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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
        return "Ciudad{" +
                "idCiudad=" + idCiudad +
                ", departamento=" + departamento +
                ", nombre='" + nombre + '\'' +
                ", status=" + status +
                '}';
    }
}
