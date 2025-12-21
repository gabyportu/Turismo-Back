package com.example.ProyectoFinal.Entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "REPRESENTANTE_EMPRESA")
public class Representante implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REPRESENTANTE_EMPRESA")
    private Integer idRepresentante;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPRESA", nullable = false)
    private Empresa empresa;

    @Column(name = "NUMERO_DOCUMENTO", nullable = false)
    private String numeroDocumento;

    @Column(name = "EXTENSION", nullable = false)
    private String extension;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    public Representante() {}

    public Representante(Integer idRepresentante, Usuario usuario, Empresa empresa, String numeroDocumento, Boolean status, String extension) {
        this.idRepresentante = idRepresentante;
        this.usuario = usuario;
        this.empresa = empresa;
        this.numeroDocumento = numeroDocumento;
        this.status = status;
        this.extension = extension;
    }

    public Integer getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(Integer idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Representante{" +
                "idRepresentante=" + idRepresentante +
                ", usuario=" + usuario +
                ", empresa=" + empresa +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", extension='" + extension + '\'' +
                ", status=" + status +
                '}';
    }
}
