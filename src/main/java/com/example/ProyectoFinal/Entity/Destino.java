package com.example.ProyectoFinal.Entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "DESTINO")
public class Destino implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DESTINO")
    private Integer idDestino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CIUDAD", nullable = false)
    private Ciudad ciudad;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    public Destino() {}

    public Destino(Integer idDestino, Ciudad ciudad, String nombre, String descripcion, Boolean status) {
        this.idDestino = idDestino;
        this.ciudad = ciudad;
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
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
        return "Destino{" +
                "idDestino=" + idDestino +
                ", ciudad=" + ciudad +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", status=" + status +
                '}';
    }
}
