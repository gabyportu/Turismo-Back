package com.example.ProyectoFinal.Entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "EMPRESA")
public class Empresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESA")
    private Integer idEmpresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CIUDAD", nullable = false)
    private Ciudad Ciudad;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "NIT", nullable = false)
    private String nit;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "FACEBOOK", nullable = true)
    private String facebook;

    @Column(name = "INSTAGRAM", nullable = true)
    private String instagram;

    @Column(name = "LOGO_URL", nullable = true)
    private String logoUrl;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    public Empresa() {}

    public Empresa(Integer idEmpresa, com.example.ProyectoFinal.Entity.Ciudad ciudad, String nombre, String nit, String descripcion, String facebook, String instagram, String logoUrl, String estado, Boolean status) {
        this.idEmpresa = idEmpresa;
        Ciudad = ciudad;
        this.nombre = nombre;
        this.nit = nit;
        this.descripcion = descripcion;
        this.facebook = facebook;
        this.instagram = instagram;
        this.logoUrl = logoUrl;
        this.estado = estado;
        this.status = status;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public com.example.ProyectoFinal.Entity.Ciudad getCiudad() {
        return Ciudad;
    }

    public void setCiudad(com.example.ProyectoFinal.Entity.Ciudad ciudad) {
        Ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa=" + idEmpresa +
                ", Ciudad=" + Ciudad +
                ", nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", facebook='" + facebook + '\'' +
                ", instagram='" + instagram + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", estado='" + estado + '\'' +
                ", status=" + status +
                '}';
    }
}
