package com.example.ProyectoFinal.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "RESENA")
public class Resena implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESENA")
    private Integer idResena;

    @ManyToOne
    @JoinColumn(name = "ID_OFERTA", nullable = false)
    private Oferta oferta;

    @ManyToOne
    @JoinColumn(name = "ID_TURISTA", nullable = false)
    private Turista turista;

    @Column(name = "CALIFICACION", nullable = false)
    private Integer calificacion;

    @Column(name = "COMENTARIO", nullable = false)
    private String comentario;

    @Column(name = "FECHA", nullable = false)
    private Date fecha;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    public Resena() {}

    public Resena(Integer idResena, Oferta oferta, Turista turista, Integer calificacion, String comentario, Date fecha, Boolean status) {
        this.idResena = idResena;
        this.oferta = oferta;
        this.turista = turista;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.status = status;
    }

    public Integer getIdResena() {
        return idResena;
    }

    public void setIdResena(Integer idResena) {
        this.idResena = idResena;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public Turista getTurista() {
        return turista;
    }

    public void setTurista(Turista turista) {
        this.turista = turista;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Resena{" +
                "idResena=" + idResena +
                ", oferta=" + oferta +
                ", turista=" + turista +
                ", calificacion=" + calificacion +
                ", comentario='" + comentario + '\'' +
                ", fecha=" + fecha +
                ", status=" + status +
                '}';
    }
}
