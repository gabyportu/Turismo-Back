package com.example.ProyectoFinal.Dto;

public class LoginResponseDto {
    private String token;
    private String role;
    private Integer idUsuario;
    private Integer idTurista;
    private Integer idEmpresa;
    private Integer idRepresentante;

    public LoginResponseDto() {}

    public LoginResponseDto(String token, String role, Integer idUsuario, Integer idTurista, Integer idEmpresa, Integer idRepresentante) {
        this.token = token;
        this.role = role;
        this.idUsuario = idUsuario;
        this.idTurista = idTurista;
        this.idEmpresa = idEmpresa;
        this.idRepresentante = idRepresentante;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdTurista() {
        return idTurista;
    }

    public void setIdTurista(Integer idTurista) {
        this.idTurista = idTurista;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(Integer idRepresentante) {
        this.idRepresentante = idRepresentante;
    }
}
