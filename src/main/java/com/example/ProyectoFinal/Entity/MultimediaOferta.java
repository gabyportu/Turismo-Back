package com.example.ProyectoFinal.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MULTIMEDIA_OFERTA")
public class MultimediaOferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MULTI_OFERTA")
    private Integer idMultimediaOferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OFERTA", nullable = false)
    private Oferta oferta;

    @Column(name = "MULTIMEDIA", nullable = true)
    private String multimedia;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    public MultimediaOferta() {}

    public MultimediaOferta(Integer idMultimediaOferta, Oferta oferta, String multimedia, Boolean status) {
        this.idMultimediaOferta = idMultimediaOferta;
        this.oferta = oferta;
        this.multimedia = multimedia;
        this.status = status;
    }

    public Integer getIdMultimediaOferta() {
        return idMultimediaOferta;
    }

    public void setIdMultimediaOferta(Integer idMultimediaOferta) {
        this.idMultimediaOferta = idMultimediaOferta;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MultimediaOferta{" +
                "idMultimediaOferta=" + idMultimediaOferta +
                ", oferta=" + oferta +
                ", multimedia='" + multimedia + '\'' +
                ", status=" + status +
                '}';
    }
}
