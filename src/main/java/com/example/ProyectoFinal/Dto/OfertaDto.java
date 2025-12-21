package com.example.ProyectoFinal.Dto;

import java.sql.Timestamp;
import java.util.Date;

public class OfertaDto {
    private Integer idOferta;
    private Integer idEmpresa;
    private String titulo;
    private String descripcion;
    private Double precio;
    private Date fechaInicio;
    private Date fechaFin;
    private String detalles;
    private String estado;
    private Timestamp fechaCreacion;
    private Boolean status;

    public OfertaDto() {}

    public OfertaDto(Integer idOferta, Integer idEmpresa, String titulo, String descripcion, Double precio, Date fechaInicio, Date fechaFin, String detalles, String estado, Timestamp fechaCreacion, Boolean status) {
        this.idOferta = idOferta;
        this.idEmpresa = idEmpresa;
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

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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
        return "OfertaDto{" +
                "idOferta=" + idOferta +
                ", idEmpresa=" + idEmpresa +
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
