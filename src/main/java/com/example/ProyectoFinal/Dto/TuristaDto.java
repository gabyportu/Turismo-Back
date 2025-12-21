package com.example.ProyectoFinal.Dto;

public class TuristaDto {
    private Integer idTurista;
    private Integer idUsuario;
    private Boolean status;

    public TuristaDto() {}

    public TuristaDto(Integer idTurista, Integer idUsuario, Boolean status) {
        this.idTurista = idTurista;
        this.idUsuario = idUsuario;
        this.status = status;
    }

    public Integer getIdTurista() {
        return idTurista;
    }

    public void setIdTurista(Integer idTurista) {
        this.idTurista = idTurista;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TuristaDto{" +
                "idTurista=" + idTurista +
                ", idUsuario=" + idUsuario +
                ", status=" + status +
                '}';
    }
}
