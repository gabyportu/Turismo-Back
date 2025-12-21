package com.example.ProyectoFinal.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "OFERTA_DESTINO")
public class OfertaDestino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OFERTA_DESTINO")
    private Integer idOfertaDestino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OFERTA", nullable = false)
    private Oferta oferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DESTINO", nullable = false)
    private Destino destino;

    public OfertaDestino() {}

    public OfertaDestino(Integer idOfertaDestino, Oferta oferta, Destino destino) {
        this.idOfertaDestino = idOfertaDestino;
        this.oferta = oferta;
        this.destino = destino;
    }

    public Integer getIdOfertaDestino() {
        return idOfertaDestino;
    }

    public void setIdOfertaDestino(Integer idOfertaDestino) {
        this.idOfertaDestino = idOfertaDestino;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "OfertaDestino{" +
                "idOfertaDestino=" + idOfertaDestino +
                ", oferta=" + oferta +
                ", destino=" + destino +
                '}';
    }
}
