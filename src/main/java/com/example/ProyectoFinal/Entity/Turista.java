package com.example.ProyectoFinal.Entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TURISTA")
public class Turista implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TURISTA")
    private Integer idTurista;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    public Turista() {}

    public Turista(Integer idTurista, Usuario usuario, Boolean status) {
        this.idTurista = idTurista;
        this.usuario = usuario;
        this.status = status;
    }

    public Integer getIdTurista() {
        return idTurista;
    }

    public void setIdTurista(Integer idTurista) {
        this.idTurista = idTurista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Turista{" +
                "idTurista=" + idTurista +
                ", usuario=" + usuario +
                ", status=" + status +
                '}';
    }
}
