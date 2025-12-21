package com.example.ProyectoFinal.Entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TIPO_ACTIVIDAD")
public class TipoActividad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_ACTIVIDAD", nullable = false)
    private Integer idTipoActividad;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    public TipoActividad() {}

    public TipoActividad(Integer idTipoActividad, String nombre, Boolean status) {
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
        return "TipoActividad{" +
                "idTipoActividad=" + idTipoActividad +
                ", nombre='" + nombre + '\'' +
                ", status=" + status +
                '}';
    }
}
