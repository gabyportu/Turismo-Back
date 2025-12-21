package com.example.ProyectoFinal.Dto;

import java.util.List;

public class OfertaDetalleDto {
    private OfertaDto oferta;
    private List<Integer> destinos;          // IDs destinos
    private List<Integer> actividades;       // IDs tipo actividad
    private List<MultimediaItemDto> multimedia; // urls minio

    public OfertaDto getOferta() { return oferta; }
    public void setOferta(OfertaDto oferta) { this.oferta = oferta; }

    public List<Integer> getDestinos() { return destinos; }
    public void setDestinos(List<Integer> destinos) { this.destinos = destinos; }

    public List<Integer> getActividades() { return actividades; }
    public void setActividades(List<Integer> actividades) { this.actividades = actividades; }

    public List<MultimediaItemDto> getMultimedia() { return multimedia; }
    public void setMultimedia(List<MultimediaItemDto> multimedia) { this.multimedia = multimedia; }
}
