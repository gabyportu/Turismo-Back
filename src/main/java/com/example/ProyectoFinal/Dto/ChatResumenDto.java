package com.example.ProyectoFinal.Dto;

import java.sql.Timestamp;

public class ChatResumenDto {
    private Integer idTurista;
    private String nombreTurista;

    private Integer idEmpresa;
    private String nombreEmpresa;

    private String ultimoMensaje;
    private Timestamp fechaUltimoMensaje;

    public ChatResumenDto() {}

    public ChatResumenDto(Integer idTurista, String nombreTurista, Integer idEmpresa, String nombreEmpresa, String ultimoMensaje, Timestamp fechaUltimoMensaje) {
        this.idTurista = idTurista;
        this.nombreTurista = nombreTurista;
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.ultimoMensaje = ultimoMensaje;
        this.fechaUltimoMensaje = fechaUltimoMensaje;
    }

    public Integer getIdTurista() {
        return idTurista;
    }

    public void setIdTurista(Integer idTurista) {
        this.idTurista = idTurista;
    }

    public String getNombreTurista() {
        return nombreTurista;
    }

    public void setNombreTurista(String nombreTurista) {
        this.nombreTurista = nombreTurista;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

    public void setUltimoMensaje(String ultimoMensaje) {
        this.ultimoMensaje = ultimoMensaje;
    }

    public Timestamp getFechaUltimoMensaje() {
        return fechaUltimoMensaje;
    }

    public void setFechaUltimoMensaje(Timestamp fechaUltimoMensaje) {
        this.fechaUltimoMensaje = fechaUltimoMensaje;
    }
}
