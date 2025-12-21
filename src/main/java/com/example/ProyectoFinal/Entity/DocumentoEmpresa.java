package com.example.ProyectoFinal.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "DOCUMENTO_EMPRESA")
public class DocumentoEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOCUMENTO_EMPRESA")
    private Integer idDocumentoEmpresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPRESA", nullable = false)
    private Empresa empresa;

    @Column(name = "DOCUMENTO", nullable = false)
    private String documento;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    public DocumentoEmpresa() {}

    public DocumentoEmpresa(Integer idDocumentoEmpresa, Empresa empresa, String documento, Boolean status) {
        this.idDocumentoEmpresa = idDocumentoEmpresa;
        this.empresa = empresa;
        this.documento = documento;
        this.status = status;
    }

    public Integer getIdDocumentoEmpresa() {
        return idDocumentoEmpresa;
    }

    public void setIdDocumentoEmpresa(Integer idDocumentoEmpresa) {
        this.idDocumentoEmpresa = idDocumentoEmpresa;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DocumentoEmpresa{" +
                "idDocumentoEmpresa=" + idDocumentoEmpresa +
                ", empresa=" + empresa +
                ", documento='" + documento + '\'' +
                ", status=" + status +
                '}';
    }
}
