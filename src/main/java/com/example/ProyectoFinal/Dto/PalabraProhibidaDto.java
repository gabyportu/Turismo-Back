package com.example.ProyectoFinal.Dto;

public class PalabraProhibidaDto {
    private Integer idPalabra;
    private String palabra;
    private Boolean status;

    public PalabraProhibidaDto() {}

    public PalabraProhibidaDto(Integer idPalabra, String palabra, Boolean status) {
        this.idPalabra = idPalabra;
        this.palabra = palabra;
        this.status = status;
    }

    public Integer getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(Integer idPalabra) {
        this.idPalabra = idPalabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PalabraProhibidaDto{" +
                "idPalabra=" + idPalabra +
                ", palabra='" + palabra + '\'' +
                ", status=" + status +
                '}';
    }
}

