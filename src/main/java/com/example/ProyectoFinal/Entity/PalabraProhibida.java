package com.example.ProyectoFinal.Entity;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "PALBRA_PROHIBIDA")
public class PalabraProhibida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PALABRA")
    private Integer idPalabra;

    @Column(name = "PALABRA", nullable = false, unique = true)
    private String palabra;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    @Column(name = "FECHA_REGISTRO", nullable = false)
    private Timestamp fechaRegistro;

    public PalabraProhibida() {
    }

    public PalabraProhibida(Integer idPalabra, String palabra, Boolean status, Timestamp fechaRegistro) {
        this.idPalabra = idPalabra;
        this.palabra = palabra;
        this.status = status;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(Integer idPalabra) {
        this.idPalabra = idPalabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "PalabraProhibida{" +
                "idPalabra=" + idPalabra +
                ", palabra='" + palabra + '\'' +
                ", status=" + status +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
