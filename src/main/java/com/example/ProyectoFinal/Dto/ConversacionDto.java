package com.example.ProyectoFinal.Dto;

import java.sql.Timestamp;

public class ConversacionDto {
    private Integer idConversacion;
    private Integer idTurista;
    private String nombreTurista;// opcional
    private String apellidosTurista;
    private Integer idEmpresa;
    private String nombreEmpresa;  // opcional
    private String mensaje;
    private Timestamp fechaEnvio;
    private String rolEmisor; // "TURISTA" o "EMPRESA"
    private Integer idEmisor;

    private Boolean esMio;

    // opcional (recomendado para UI)
    private String emisorNombre;

    public Integer getIdConversacion() { return idConversacion; }
    public void setIdConversacion(Integer idConversacion) { this.idConversacion = idConversacion; }

    public Integer getIdTurista() { return idTurista; }
    public void setIdTurista(Integer idTurista) { this.idTurista = idTurista; }

    public String getNombreTurista() { return nombreTurista; }
    public void setNombreTurista(String nombreTurista) { this.nombreTurista = nombreTurista; }

    public Integer getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Integer idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Timestamp getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Timestamp fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public String getRolEmisor() {
        return rolEmisor;
    }

    public void setRolEmisor(String rolEmisor) {
        this.rolEmisor = rolEmisor;
    }

    public Integer getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(Integer idEmisor) {
        this.idEmisor = idEmisor;
    }

    public Boolean getEsMio() {
        return esMio;
    }

    public void setEsMio(Boolean esMio) {
        this.esMio = esMio;
    }

    public String getEmisorNombre() {
        return emisorNombre;
    }

    public void setEmisorNombre(String emisorNombre) {
        this.emisorNombre = emisorNombre;
    }

    public String getApellidosTurista() {
        return apellidosTurista;
    }

    public void setApellidosTurista(String apellidosTurista) {
        this.apellidosTurista = apellidosTurista;
    }
}
