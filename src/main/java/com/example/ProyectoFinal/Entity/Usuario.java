package com.example.ProyectoFinal.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID_USUARIO", nullable = false)
    private Integer idUsuario;

    @Column(name = "NOMBRES", nullable = false)
    private String nombres;

    @Column(name = "APELLIDO_PATERNO", nullable = false)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", nullable = false)
    private String apellidoMaterno;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "GENERO", nullable = false)
    private String genero;

    @Column(name = "TELEFONO", nullable = false)
    private String telefono;

    @Column(name = "CORREO", nullable = false)
    private String correo;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    @Column(name = "FECHA_REGISTRO", nullable = false)
    @Transient
    private Timestamp fechaRegistro;

    public Usuario() {}

    public Usuario(Integer idUsuario, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String genero, String telefono, String correo, String password, Boolean status, Timestamp fechaRegistro) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
        this.status = status;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombres='" + nombres + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", genero='" + genero + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
