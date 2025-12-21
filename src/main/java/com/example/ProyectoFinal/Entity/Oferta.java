package com.example.ProyectoFinal.Entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "OFERTA")
public class Oferta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OFERTA")
    private Integer idOferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPRESA", nullable = false)
    private Empresa empresa;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "PRECIO", nullable = false)
    private Double precio;

    @Column(name = "FECHA_INICIO", nullable = true)
    private Date fechaInicio;

    @Column(name = "FECHA_FIN", nullable = true)
    private Date fechaFin;

    @Column(name = "DETALLES", nullable = true)
    private String detalles;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    public Oferta() {}

    public Oferta(Integer idOferta, Empresa empresa, String titulo, String descripcion, Double precio, Date fechaInicio, Date fechaFin, String detalles, String estado, Timestamp fechaCreacion, Boolean status) {
        this.idOferta = idOferta;
        this.empresa = empresa;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.detalles = detalles;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Oferta{" +
                "idOferta=" + idOferta +
                ", empresa=" + empresa +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", detalles='" + detalles + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", status=" + status +
                '}';
    }
}
