package com.example.ProyectoFinal.Dto;

import java.sql.Timestamp;

public class ConversacionDto {
    private Integer idConversacion;
    private Integer idTurista;
    private Integer idEmpresa;
    private String mensaje;
    private Timestamp fechaEnvio;
    private Boolean status;

    public ConversacionDto() {}

    public ConversacionDto(Integer idConversacion, Integer idTurista, Integer idEmpresa, String mensaje, Timestamp fechaEnvio, Boolean status) {
        this.idConversacion = idConversacion;
        this.idTurista = idTurista;
        this.idEmpresa = idEmpresa;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.status = status;
    }

    public Integer getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }

    public Integer getIdTurista() {
        return idTurista;
    }

    public void setIdTurista(Integer idTurista) {
        this.idTurista = idTurista;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Timestamp fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConversacionDto{" +
                "idConversacion=" + idConversacion +
                ", idTurista=" + idTurista +
                ", idEmpresa=" + idEmpresa +
                ", mensaje='" + mensaje + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", status=" + status +
                '}';
    }
}
