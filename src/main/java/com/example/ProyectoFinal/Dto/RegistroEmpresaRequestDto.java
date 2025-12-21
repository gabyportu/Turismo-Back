package com.example.ProyectoFinal.Dto;

public class RegistroEmpresaRequestDto {
    private UsuarioDto usuario;
    private EmpresaDto empresa;
    private RepresentanteDto representante;

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }

    public RepresentanteDto getRepresentante() {
        return representante;
    }

    public void setRepresentante(RepresentanteDto representante) {
        this.representante = representante;
    }
}
