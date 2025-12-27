package com.example.ProyectoFinal.Dto;

public class EditarEmpresaRequestDto {
    private EmpresaDto empresa;
    private UsuarioDto usuario;
    private RepresentanteDto representante;

    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public RepresentanteDto getRepresentante() {
        return representante;
    }

    public void setRepresentante(RepresentanteDto representante) {
        this.representante = representante;
    }
}
