package com.example.ProyectoFinal.Dto;

import java.util.List;

public class EditarOfertaRequestDto {
    private OfertaDto oferta;
    private List<Integer> destinos;
    private List<Integer> actividades;

    public OfertaDto getOferta() {
        return oferta;
    }

    public void setOferta(OfertaDto oferta) {
        this.oferta = oferta;
    }

    public List<Integer> getDestinos() {
        return destinos;
    }

    public void setDestinos(List<Integer> destinos) {
        this.destinos = destinos;
    }

    public List<Integer> getActividades() {
        return actividades;
    }

    public void setActividades(List<Integer> actividades) {
        this.actividades = actividades;
    }
}
