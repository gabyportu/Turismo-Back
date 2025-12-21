package com.example.ProyectoFinal.Dto;

public class OfertaDestinoDto {
    private Integer idOfertaDestino;
    private Integer idOferta;
    private Integer idDestino;

    public OfertaDestinoDto() {}

    public OfertaDestinoDto(Integer idOfertaDestino, Integer idOferta, Integer idDestino) {
        this.idOfertaDestino = idOfertaDestino;
        this.idOferta = idOferta;
        this.idDestino = idDestino;
    }

    public Integer getIdOfertaDestino() {
        return idOfertaDestino;
    }

    public void setIdOfertaDestino(Integer idOfertaDestino) {
        this.idOfertaDestino = idOfertaDestino;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public Integer getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    @Override
    public String toString() {
        return "OfertaDestinoDto{" +
                "idOfertaDestino=" + idOfertaDestino +
                ", idOferta=" + idOferta +
                ", idDestino=" + idDestino +
                '}';
    }
}
