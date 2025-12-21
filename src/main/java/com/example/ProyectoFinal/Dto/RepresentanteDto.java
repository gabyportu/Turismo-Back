package com.example.ProyectoFinal.Dto;

public class RepresentanteDto {
    private Integer idRepresentante;
    private Integer idUsuario;
    private Integer idEmpresa;
    private String numeroDocumento;
    private String extension;
    private Boolean status;

    public RepresentanteDto() {}

    public RepresentanteDto(Integer idRepresentante, Integer idUsuario, Integer idEmpresa, String numeroDocumento, String extension, Boolean status) {
        this.idRepresentante = idRepresentante;
        this.idUsuario = idUsuario;
        this.idEmpresa = idEmpresa;
        this.numeroDocumento = numeroDocumento;
        this.extension = extension;
        this.status = status;
    }

    public Integer getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(Integer idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RepresentanteDto{" +
                "idRepresentante=" + idRepresentante +
                ", idUsuario=" + idUsuario +
                ", idEmpresa=" + idEmpresa +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", extension='" + extension + '\'' +
                ", status=" + status +
                '}';
    }
}
