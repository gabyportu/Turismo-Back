package com.example.ProyectoFinal.Dto;

public class EmpresaDto {
    private Integer idEmpresa;
    private Integer idCiudad;
    private String nombre;
    private String nit;
    private String descripcion;
    private String facebook;
    private String instagram;
    private String logoURL;
    private String estado;
    private Boolean status;

    public EmpresaDto() {}

    public EmpresaDto(Integer idEmpresa, Integer idCiudad, String nombre, String descripcion, String nit, String facebook, String instagram, String logoURL, String estado, Boolean status) {
        this.idEmpresa = idEmpresa;
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nit = nit;
        this.facebook = facebook;
        this.instagram = instagram;
        this.logoURL = logoURL;
        this.estado = estado;
        this.status = status;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
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
        return "EmpresaDto{" +
                "idEmpresa=" + idEmpresa +
                ", idCiudad=" + idCiudad +
                ", nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", facebook='" + facebook + '\'' +
                ", instagram='" + instagram + '\'' +
                ", logoURL='" + logoURL + '\'' +
                ", estado='" + estado + '\'' +
                ", status=" + status +
                '}';
    }
}
