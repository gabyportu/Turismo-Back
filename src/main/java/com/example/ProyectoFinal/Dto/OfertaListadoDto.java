package com.example.ProyectoFinal.Dto;

public class OfertaListadoDto {
    private Integer idOferta;
    private String titulo;
    private String descripcion;
    private Double precio;
    private String estado;
    private MultimediaItemDto imagenPrincipal;

    public OfertaListadoDto() {}

    public OfertaListadoDto(Integer idOferta, String titulo, String descripcion, Double precio, String estado, MultimediaItemDto imagenPrincipal) {
        this.idOferta = idOferta;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.imagenPrincipal = imagenPrincipal;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public MultimediaItemDto getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(MultimediaItemDto imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }
}
