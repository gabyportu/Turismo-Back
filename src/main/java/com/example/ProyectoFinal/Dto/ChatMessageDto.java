package com.example.ProyectoFinal.Dto;

public class ChatMessageDto {

    private Integer idTurista;
    private Integer idEmpresa;
    private String mensaje;
    private String emisor; // TURISTA | EMPRESA

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

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }
}
