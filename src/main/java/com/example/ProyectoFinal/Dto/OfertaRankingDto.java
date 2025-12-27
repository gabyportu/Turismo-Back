package com.example.ProyectoFinal.Dto;

public class OfertaRankingDto {
    private Integer idOferta;
    private String titulo;
    private Double precio;
    private Double promedio;
    private Long totalResenas;
    private MultimediaItemDto imagenPrincipal;

    public OfertaRankingDto() {}

    public OfertaRankingDto(Integer idOferta, String titulo, Double precio, Double promedio, Long totalResenas, MultimediaItemDto imagenPrincipal) {
        this.idOferta = idOferta;
        this.titulo = titulo;
        this.precio = precio;
        this.promedio = promedio;
        this.totalResenas = totalResenas;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public Long getTotalResenas() {
        return totalResenas;
    }

    public void setTotalResenas(Long totalResenas) {
        this.totalResenas = totalResenas;
    }

    public MultimediaItemDto getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(MultimediaItemDto imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }
}
