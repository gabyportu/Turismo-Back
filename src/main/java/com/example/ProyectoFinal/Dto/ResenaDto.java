package com.example.ProyectoFinal.Dto;

import java.util.Date;

public class ResenaDto {
    private Integer idResena;
    private Integer idOferta;
    private Integer idTurista;
    private Integer calificacion;
    private String comentario;
    private Date fecha;
    private Boolean status;

    public ResenaDto() {}

    public ResenaDto(Integer idResena, Integer idOferta, Integer idTurista, Integer calificacion, Date fecha, String comentario, Boolean status) {
        this.idResena = idResena;
        this.idOferta = idOferta;
        this.idTurista = idTurista;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.comentario = comentario;
        this.status = status;
    }

    public Integer getIdResena() {
        return idResena;
    }

    public void setIdResena(Integer idResena) {
        this.idResena = idResena;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public Integer getIdTurista() {
        return idTurista;
    }

    public void setIdTurista(Integer idTurista) {
        this.idTurista = idTurista;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResenaDto{" +
                "idResena=" + idResena +
                ", idOferta=" + idOferta +
                ", idTurista=" + idTurista +
                ", calificacion=" + calificacion +
                ", comentario='" + comentario + '\'' +
                ", fecha=" + fecha +
                ", status=" + status +
                '}';
    }
}
