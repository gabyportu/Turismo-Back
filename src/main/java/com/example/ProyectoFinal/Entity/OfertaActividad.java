package com.example.ProyectoFinal.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "OFERTA_ACTIVIDAD")
public class OfertaActividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OFERTA_ACTIVIDAD")
    private Integer idOfertaActividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OFERTA")
    private Oferta oferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_ACTIVIDAD")
    private TipoActividad tipoActividad;

    public OfertaActividad() {}

    public OfertaActividad(Integer idOfertaActividad, Oferta oferta, TipoActividad tipoActividad) {
        this.idOfertaActividad = idOfertaActividad;
        this.oferta = oferta;
        this.tipoActividad = tipoActividad;
    }

    public Integer getIdOfertaActividad() {
        return idOfertaActividad;
    }

    public void setIdOfertaActividad(Integer idOfertaActividad) {
        this.idOfertaActividad = idOfertaActividad;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    @Override
    public String toString() {
        return "OfertaActividad{" +
                "idOfertaActividad=" + idOfertaActividad +
                ", oferta=" + oferta +
                ", tipoActividad=" + tipoActividad +
                '}';
    }
}
