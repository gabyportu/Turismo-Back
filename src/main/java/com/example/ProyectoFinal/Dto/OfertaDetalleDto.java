package com.example.ProyectoFinal.Dto;

import java.util.List;

public class OfertaDetalleDto {
    private OfertaDto oferta;
    private List<ItemDto> destinos;          // IDs destinos
    private List<ItemDto> actividades;       // IDs tipo actividad
    private List<MultimediaItemDto> multimedia; // urls minio

    public OfertaDto getOferta() {
        return oferta;
    }

    public void setOferta(OfertaDto oferta) {
        this.oferta = oferta;
    }

    public List<ItemDto> getDestinos() {
        return destinos;
    }

    public void setDestinos(List<ItemDto> destinos) {
        this.destinos = destinos;
    }

    public List<ItemDto> getActividades() {
        return actividades;
    }

    public void setActividades(List<ItemDto> actividades) {
        this.actividades = actividades;
    }

    public List<MultimediaItemDto> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<MultimediaItemDto> multimedia) {
        this.multimedia = multimedia;
    }
}
