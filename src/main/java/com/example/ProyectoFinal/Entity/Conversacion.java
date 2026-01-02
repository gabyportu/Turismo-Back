package com.example.ProyectoFinal.Entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "CONVERSACION")
public class Conversacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONVERSACION")
    private Integer idConversacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TURISTA", nullable = false)
    private Turista turista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPRESA", nullable = false)
    private Empresa empresa;

    @Column(name = "MENSAJE", nullable = false)
    private String mensaje;

    @Column(name = "FECHA_ENVIADO", nullable = false)
    private Timestamp fechaEnvio;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    @Column(name = "ROL_EMISOR", nullable = false, length = 20)
    private String rolEmisor;   // "TURISTA" o "EMPRESA"

    @Column(name = "ID_EMISOR", nullable = false)
    private Integer idEmisor;

    public Conversacion() {}

    public Conversacion(Integer idConversacion, Turista turista, Empresa empresa, String mensaje, Timestamp fechaEnvio, Boolean status, String rolEmisor, Integer idEmisor) {
        this.idConversacion = idConversacion;
        this.turista = turista;
        this.empresa = empresa;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.status = status;
        this.rolEmisor = rolEmisor;
        this.idEmisor = idEmisor;
    }

    public Integer getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }

    public Turista getTurista() {
        return turista;
    }

    public void setTurista(Turista turista) {
        this.turista = turista;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    @Override
    public String toString() {
        return "Conversacion{" +
                "idConversacion=" + idConversacion +
                ", turista=" + turista +
                ", empresa=" + empresa +
                ", mensaje='" + mensaje + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", status=" + status +
                ", rolEmisor='" + rolEmisor + '\'' +
                ", idEmisor=" + idEmisor +
                '}';
    }
}
